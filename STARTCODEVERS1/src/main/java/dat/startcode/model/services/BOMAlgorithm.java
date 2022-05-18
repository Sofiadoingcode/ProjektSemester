package dat.startcode.model.services;

import dat.startcode.model.DTOs.ProductDTO;
import dat.startcode.model.DTOs.ProductionlineDTO;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.CarportChoices;
import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BOMAlgorithm {
    private ConnectionPool connectionPool;

    public BOMAlgorithm() throws DatabaseException {
        this.connectionPool = ApplicationStart.getConnectionPool();

        ProductMapper productMapper = new ProductMapper(connectionPool);
        lengths = productMapper.getLengths();

    }

    public BOMAlgorithm(ConnectionPool connectionPool) throws DatabaseException {
        this.connectionPool = connectionPool;
        ProductMapper productMapper = new ProductMapper(connectionPool);
        lengths = productMapper.getLengths();

    }

    HashMap<Integer, Integer> lengths;


    public List<ProductLine> generateBOM(CarportChoices carportChoice) {

        List<ProductLine> fullbom = new ArrayList<>();

        List<ProductDTO> allproducts = loadAllProducts();

        List<String> carportNeededItems = returnNeededListCarport();

        List<String> shedNeededItems = returnNeededListShed();

        for (int i = 0; i < carportNeededItems.size() + shedNeededItems.size(); i++) {

            String neededItem = carportNeededItems.get(i);

            if (i > carportNeededItems.size() - 1) {
                // MAKE SHED
                neededItem = shedNeededItems.get(i);
            }

            List<ProductLine> itemProductLines = generateItemProductlines(neededItem, allproducts, carportChoice);

            for (ProductLine pl : itemProductLines) {
                fullbom.add(pl);

            }

        }


        return fullbom;
    }


    private List<ProductDTO> loadAllProducts() {
        List<ProductDTO> allproducts = new ArrayList<>();

        BOMMapper bomMapper = new BOMMapper(connectionPool);

        try {
            allproducts = bomMapper.getAllProductDTOs();

        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return allproducts;

    }

    private List<String> returnNeededListCarport() {
        List<String> neededElements = new ArrayList<>();
        neededElements.add("stolpe");
        neededElements.add("rem");
        neededElements.add("beslag");
        neededElements.add("spær");
        neededElements.add("hulbånd");
        neededElements.add("stern");
        neededElements.add("tag");
        neededElements.add("vandbræt");

        return neededElements;
    }

    private List<String> returnNeededListShed() {
        List<String> neededItems = new ArrayList<>();

        return neededItems;
    }

    private List<ProductLine> generateItemProductlines(String neededitem, List<ProductDTO> allproducts, CarportChoices carportChoice) {
        List<ProductLine> onlyThisItemProductionlines = new ArrayList<>();

        double carportHeight = carportChoice.getHeight();
        double carportWidth = carportChoice.getWidth();
        double carportLength = carportChoice.getLength();

        switch (neededitem) {
            case "stolpe":
                onlyThisItemProductionlines = calculateStolpeProductLines(allproducts, carportHeight, carportWidth, carportLength);
                break;
            case "rem":
                onlyThisItemProductionlines = calculateRemProductLines(allproducts, carportHeight, carportWidth, carportLength);
                break;
            case "beslag":
                onlyThisItemProductionlines = calculateBeslagProductLines(allproducts, carportHeight, carportWidth, carportLength);
                break;
            case "spær":
                onlyThisItemProductionlines = calculateSpærProductLines(allproducts, carportHeight, carportWidth, carportLength);
                break;
            case "hulbånd":
                onlyThisItemProductionlines = calculateHulbåndProductLines(allproducts, carportHeight, carportWidth, carportLength);
                break;
            case "stern":
                onlyThisItemProductionlines = calculateSternProductLines(allproducts, carportHeight, carportWidth, carportLength);
                break;
            case "tag":
                onlyThisItemProductionlines = calculateTagProductLines(allproducts, carportHeight, carportWidth, carportLength);
                break;
            case "vandbræt":
                onlyThisItemProductionlines = calculateVandbrætProductLines(allproducts, carportHeight, carportWidth, carportLength);
                break;
        }


        return onlyThisItemProductionlines;
    }

    private List<ProductLine> calculateStolpeProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    private List<ProductLine> calculateRemProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    private List<ProductLine> calculateBeslagProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    private List<ProductLine> calculateSpærProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    private List<ProductLine> calculateHulbåndProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    public List<ProductLine> calculateSternProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();

        List<ProductDTO> sterns = getAllNeededProducts(allproducts, "stern");
        //todo:replace rem
        ProductDTO rem = new ProductDTO(1, "lol", "stk", 0, 0, 0, 0, 0, "String");
        List<ProductDTO> upperStern = new ArrayList<>();
        ProductDTO theUpperStern = null;
        for (ProductDTO product : sterns) {

            if (product.getWidth() >= rem.getWidth()) {
                upperStern.add(product);
                try {
                    if (theUpperStern.getPricemeasurment() < product.getPricemeasurment())
                        theUpperStern = product;

                } catch (NullPointerException e) {
                    theUpperStern = product;
                }
            }

        }

        int[] arr = getLengthsNeeded(carportLength);
        double price = arr[1]*2*theUpperStern.getPricemeasurment()*arr[0]/100;

        ProductLine productLine = new ProductLine(theUpperStern.getIdproduct(), arr[1]*2, arr[2], price);
        returnList.add(productLine);

        arr = getLengthsNeeded(carportWidth);
        productLine = new ProductLine(theUpperStern.getIdproduct(), arr[1]*2, arr[2]*2, price);

        returnList.add(productLine);

        return returnList;
    }


    private List<ProductLine> calculateTagProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    public List<ProductLine> calculateVandbrætProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {

        List<ProductLine> returnList = new ArrayList<>();

        List<ProductDTO> allUssableProducts = getAllNeededProducts(allproducts, "brædt");

        for (int i = 0; i < allUssableProducts.size(); i++) {
            ProductDTO temp = allUssableProducts.get(i);


            if (temp.getWidth() <= 25) {
                allUssableProducts.remove(i);
                i--;
            }
        }
        ProductDTO theVandBrad = null;

        for (ProductDTO product : allUssableProducts) {
            try {
                if (product.getPricemeasurment() < theVandBrad.getPricemeasurment())
                    theVandBrad = product;

            } catch (NullPointerException e) {
                theVandBrad = product;
            }
        }
        System.out.println("carport length " + carportLength);
        int[] vandBradSide = getLengthsNeeded( carportLength);
        int[] vandBradFront = getLengthsNeeded( carportWidth);

        int vandBradSideAmount = vandBradSide[1]*2;
        int vandBradFrontAmount = vandBradFront[1];



        ProductLine vandBradSideProductLine = new ProductLine(theVandBrad.getIdproduct(), vandBradSide[1]*2,vandBradSide[2],vandBradSideAmount*theVandBrad.getPricemeasurment()*vandBradSide[0]/100);
        ProductLine vandBradFrontProductLine = new ProductLine(theVandBrad.getIdproduct(), vandBradFront[1], vandBradFront[2], vandBradFrontAmount*theVandBrad.getPricemeasurment()*vandBradFront[0]/100 );

        returnList.add(vandBradFrontProductLine);
        returnList.add(vandBradSideProductLine);

        return returnList;
    }

    private List<ProductDTO> getAllNeededProducts(List<ProductDTO> allproducts, String type) {
        List<ProductDTO> returnList = new ArrayList<>();

        for (ProductDTO p : allproducts) {
            if (p.getProducttype().equals(type)) {
                returnList.add(p);

            }

        }

        return returnList;
    }


//    private int getNeededProductID(List<ProductDTO> allproducts, String neededitem) {
//
//        if(neededitem.equals("stolpe")){
//
//
//
//        }
//
//        List<ProductDTO> onlyNeededItems = new ArrayList<>();
//        for(ProductDTO p: allproducts) {
//            if(p.getProducttype().equals())
//
//
//        }
//
//
//        return 0;
//    }


    private int[] getLengthsNeeded(double length) {
        //calculates the number of equally long planks
        System.out.println("length" + length);
        int max_lengths = lengths.get(1);
        for (int i = 1; i <= lengths.size(); i++) {
            int templength = lengths.get(i);

            if (templength > max_lengths) {
                max_lengths = templength;
            }
        }

        System.out.println(max_lengths);
        double numberNeeded = 1;
        numberNeeded =  Math.ceil(length / max_lengths);
        int delta_l = max_lengths;
        int theLength = lengths.get(1);
        int k = 0;
        for (int i = 1; i <= lengths.size(); i++) {
            int templength = (int) (lengths.get(i) * numberNeeded);
            double tempDelta_l = lengths.get(i) * numberNeeded - length;

            if (delta_l > tempDelta_l && tempDelta_l >= 0) {
                delta_l = (int) tempDelta_l;
                theLength = lengths.get(i);
                k = i;
            }
            System.out.println(numberNeeded);

        }
        return new int[]{theLength,(int) numberNeeded, k};

    }


}
