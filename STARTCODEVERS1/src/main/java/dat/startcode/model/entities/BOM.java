package dat.startcode.model.entities;

import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.persistence.ProductMapper;

import java.util.ArrayList;
import java.util.List;

public class BOM {

    private double height;
    private double width, shed_width;
    private double length, shed_length;
    private boolean wantsShed;
    private double roofAngle;
    private final double roofOverHangDistance = 0.3;

    private ConnectionPool connectionPool;
    ProductMapper productMapper;
    List<Product> products;
    List<ProductLine> fullbom;


    public BOM(double height, double width, double length, boolean wantsShed, double roofAngle) throws DatabaseException {
        this.height = height;
        this.width = width;
        this.length = length;
        this.wantsShed = wantsShed;

        connectionPool = new ConnectionPool();
        productMapper = new ProductMapper(connectionPool);


        products = productMapper.getAllProducts();
        printProductsList();

    }

    public BOM(double height, double width, double length, boolean wantsShed, double shed_length, double shed_width, double roofAngle, ConnectionPool connectionPool) throws DatabaseException {
        this.height = height;
        this.width = width;
        this.length = length;
        this.wantsShed = wantsShed;
        this.shed_width = shed_width;
        this.shed_length = shed_length;



        productMapper = new ProductMapper(connectionPool);


        products = productMapper.getAllProducts();
        printProductsList();

    }


    public List<ProductLine> generateFullBom() {

        fullbom = new ArrayList<>();


        if (wantsShed) {


            //rem
            addTreeStuff("spærtræ ubh.", 2, length - shed_length);
            addTreeStuff("spærtræ ubh.", 1, shed_length * 2);


            //todo: fix length and amounts
            //løsholter til skur
            //addTreeStuff("regular ub.", 4, shed_length);
            // addTreeStuff("regular ub.", 12, shed_width / 2);

            //todo: think about length
            //door
            addTreeStuff("lægte ubh.", 1, 420);

            addScrewsNStuff("t hængsel 390 mm", 2);
            addScrewsNStuff("stalddørsgreb 50x75", 1);

            int numberOfDecoBoards = calculateNumberOfDecoBoards(10);
            addTreeStuff("trykimp. Brædt", numberOfDecoBoards, height, 19, 100);


            int screwLno = calculateNumberRequired("Skruer", numberOfDecoBoards * 4, 7, 5);
            addScrewsNStuff("Skruer", screwLno, 7, 5);
            int screwSno = calculateNumberRequired("Skruer", numberOfDecoBoards * 3, 5, 5);
            addScrewsNStuff("Skruer", screwSno, 5, 5);
        } else {


            //rem
            addTreeStuff("spærtræ ubh.", 2, length);

        }
        addTreeStuff("trykimp. Brædt", 2, length, 25, 200);



        //todo:fix length and amount
        addTreeStuff("trykimp. Brædt", 2, length, 25, 200);
        addTreeStuff("trykimp. Brædt", 2, width, 25, 200);

        addTreeStuff("trykimp. Brædt", 2, length, 19, 200);
        addTreeStuff("trykimp. Brædt", 1, width, 19, 200);


        int amountOfSpears = calculateSpearAmount(length);
        addTreeStuff("spærtræ ubh.", amountOfSpears, width);
        addScrewsNStuff("universal 190 mm højre", amountOfSpears);
        addScrewsNStuff("universal 190 mm venstre", amountOfSpears);


        //Todo:think about length
        addTreeStuff("Plastmo Ecolite blåtonet",(int)Math.ceil(width/100),length);



        int polNumber = calculateNumberOfColomns();
        addTreeStuff("trykimp. Stolpe", polNumber, height + 1);


       /* while (true) {
            // CHECK IF ALL CRITERIA FOR CARPORT ARE MET//


            //METHOD TO FIND THE RIGHT PRODUCT

            String name = generateProductName();

            int length = calculateProductLength();

            int amount = calculateProductAmount();

            double price = calculateFullPrice(amount);

            ProductLine newProductline = new ProductLine(name, amount, price, length);
            fullbom.add(newProductline);
            break;
        }
*/

        addScrewsNStuff("hulbånd 1x20 mm. 10 mtr.", 2);

        addScrewsNStuff("Skruer", 1, 60, 4.5);


        addScrewsNStuff("Beslag & Skruer", 3, 50, 4.5);


        //todo: think about the amounts for these 2
        addScrewsNStuff("bræddebolt 10 x 120 mm.", 18);
        addScrewsNStuff("firkantskiver 40x40x11mm", 12);
        //int numberOfDecoScrews = calculateNumberRequired("");


        return fullbom;
    }


    // PRODUCT NAME //
    private String generateProductName() {

        return "";
    }


    // LENGTH //

    private int calculateProductLength() {

        return 0;
    }


    // AMOUNT //
    private int calculateProductAmount() {

        return 0;
    }


    // PRICE //
    private double calculateFullPrice(int amount) {

        return 0;
    }


    private Product searchProduct(String name) {
        Product product = null;

        for (Product tempproduct : products) {
            if (tempproduct.getName().equals(name))
                product = tempproduct;

        }

        return product;
    }


    private Product searchProduct(String name, int width, int height) {
        Product product = null;

        for (Product tempproduct : products) {
            if (tempproduct.getName().equals(name) && tempproduct.getHeight() == height && tempproduct.getWidth() == width)
                product = tempproduct;

        }

        return product;
    }

    private ProductLine addTreeStuff(String name, int amount, double length) {

        Product product = searchProduct(name);


        if (product.getHeight()!=0 || product.getWidth()!=0){
            name = product.getHeight() + " x " + product.getWidth() + " mm "+  name;

        }


        ProductLine productLine = new ProductLine(name, amount, product.getPrice() * length, (int) length);

        fullbom.add(productLine);

        return productLine;

    }

    private ProductLine addTreeStuff(String name, int amount, double length, double width, double height) {

        Product product = searchProduct(name, (int) width, (int) height);

        if (product.getHeight()!=0 || product.getWidth()!=0){
            name = product.getHeight() + " x " + product.getWidth() + " mm "+  name;

        }



        ProductLine productLine = new ProductLine(name, amount, product.getPrice() * length, (int) length);

        fullbom.add(productLine);

        return productLine;

    }

    private ProductLine addScrewsNStuff(String name, int amount) {

        Product product = searchProduct(name);

        ProductLine productLine = new ProductLine(name, amount, product.getPrice(), 0);

        fullbom.add(productLine);

        return productLine;

    }

    private ProductLine addScrewsNStuff(String name, int amount, double width, double height) {

        Product product = searchProduct(name, (int) width, (int) height);

        ProductLine productLine = new ProductLine(name, amount, product.getPrice(), 0);

        fullbom.add(productLine);

        return productLine;

    }

    private int calculateNumberOfColomns() {
        //Only 2 people knew how this code works, a mad man and god. Now only god knows. Please avoid writing in this code and let this be a warning for anyone that attempts to understand this code.

        int polNumber = 4;
        int extraPolesSides = 0, extraPolesFront = 0;
        int roofOverHang = 1;

        double minPolDist = 4; //Maximum distance between poles;
        if (wantsShed) {
            int shedPoles = 3;

            if (shed_width < width) {
                shedPoles += 2;
            }

            extraPolesSides += Math.ceil(shed_length / minPolDist) - 1;
            extraPolesFront += Math.ceil(shed_width / minPolDist) - 1;


            //might as well add holter now

            addTreeStuff("regular ub", extraPolesSides * 4, shed_length);
            addTreeStuff("regular ub", extraPolesFront * 6, shed_width);
            addScrewsNStuff("vinkelbeslag 34", extraPolesSides * 4 + extraPolesFront * 6);


            polNumber += shedPoles;
        }

        extraPolesFront += Math.ceil((width - shed_width - roofOverHang * 2) / minPolDist);
        polNumber += extraPolesSides + extraPolesFront;

        return polNumber;
    }

    private int calculateSpearAmount(double length) {

        final double a = 0.55;
        return (int) (Math.ceil(length / 0.55));

    }

    private int calculateNumberRequired(String name, int number) {
        Product product = searchProduct(name);
        return (int) Math.ceil((float) number / product.getAmount());
    }

    private int calculateNumberRequired(String name, int number, int width, int height) {
        Product product = searchProduct(name, width, height);
        return (int) Math.ceil((float) number / product.getAmount());
    }

    private int calculateNumberOfDecoBoards(int width) {
        Product product = searchProduct("trykimp. Brædt", width * 100, 19);


        double perimeter = this.width * 2 + this.length * 2;

        return (int) Math.ceil(perimeter / width);
    }


    public void printBom(){
        for (ProductLine productLine: fullbom)
            System.out.println(productLine);
    }

    public void printProductsList(){
        for (Product product: products)
            System.out.println(product);
    }
}

