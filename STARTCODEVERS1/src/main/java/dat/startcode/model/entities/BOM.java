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

    }


    public List<ProductLine> generateFullBom() {

        fullbom = new ArrayList<>();


        if (wantsShed) {


            //rem
            addTreeStuff("spærtræ ubh.", 2, length - shed_length);
            addTreeStuff("spærtræ ubh.", 1, shed_length * 2);


            //todo: fix length and amounts
            //løsholter til skur
            addTreeStuff("regular ub.", 4, shed_length);
            addTreeStuff("regular ub.", 12, length - roofOverHangDistance * 2);

            //todo: think about length
            //door
            addTreeStuff("lægte ubh.", 1, 420);


        } else {


            //rem
            addTreeStuff("spærtræ ubh.", 2, length);

        }

        //todo:fix length and amount
        addTreeStuff("trykimp. Brædt", 2, length, 25, 200);
        addTreeStuff("trykimp. Brædt", 2, width, 25, 200);

        addTreeStuff("trykimp. Brædt", 2, length, 19, 200);
        addTreeStuff("trykimp. Brædt", 1, width, 19, 200);



        int amountOfSpears = calculateSpearAmount(length);
        addTreeStuff("spærtræ ubh.", amountOfSpears, width);


        int polNumber = calculateNumberOfColomns();
        addTreeStuff("trykimp. Stolpe",polNumber,height+1);





        while (true) {
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


        addScrewsNStuff("hulbånd 1x20 mm. 10 mtr.", 2);
        addScrewsNStuff("universal 190 mm højre", amountOfSpears);
        addScrewsNStuff("universal 190 mm venstre", amountOfSpears);

        int numberOfDecoBoards = 0;





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

        ProductLine productLine = new ProductLine(name, amount, product.getPrice() * length, (int) length);

        fullbom.add(productLine);

        return productLine;

    }

    private ProductLine addTreeStuff(String name, int amount, double length, double width, double height) {

        Product product = searchProduct(name, (int) width, (int) height);

        ProductLine productLine = new ProductLine(name, amount, product.getPrice() * length, (int) length);

        fullbom.add(productLine);

        return productLine;

    }

    private ProductLine addScrewsNStuff(String name, int amount) {

        Product product = searchProduct(name, (int) width, (int) height);

        ProductLine productLine = new ProductLine(name, amount, product.getPrice(),0);

        fullbom.add(productLine);

        return productLine;

    }


    private int calculateNumberOfColomns(){
        int polNumber = 4;
        int extraPolesX=0, extraPolesY=0;
        int roofOverHang =1;

        double minPolDist = 4; //Maximum distance between poles;
        if ( wantsShed){
            int shedPoles = 3;

                if (shed_width < width){
                    shedPoles+=2;
                }

            extraPolesX += Math.ceil(shed_length/minPolDist) -1;
            extraPolesY += Math.ceil(shed_length/minPolDist) -1;

            polNumber+=shedPoles;
        }

        extraPolesY += Math.ceil(length-shed_length-roofOverHang);
        polNumber += extraPolesX + extraPolesY;

        return polNumber;
    }

    private int calculateSpearAmount(double length) {

        final double a = 0.55;
        return (int) (Math.ceil(length / 0.55));

    }

    private int calculateNumberRequired(String name, int number){

        Product product = searchProduct(name);

        return (int)Math.ceil(number/product.getAmount());
    }



}

