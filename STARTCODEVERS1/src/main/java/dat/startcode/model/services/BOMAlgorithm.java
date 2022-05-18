package dat.startcode.model.services;

import dat.startcode.model.DTOs.ProductDTO;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.CarportChoices;
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
    private double carportLength;

    public BOMAlgorithm() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public BOMAlgorithm(ConnectionPool connectionPool) throws DatabaseException {
        this.connectionPool = connectionPool;

    }
    public List<ProductLine> generateBOM (CarportChoices carportChoice) {

        List<ProductLine> fullbom = new ArrayList<>();

        List<ProductDTO> allproducts = loadAllProducts();

        List<String> carportNeededItems = returnNeededListCarport();


        List<String> shedNeededItems = returnNeededListShed();

        for(int i = 0; i < carportNeededItems.size() + shedNeededItems.size() - 1; i++) {

            String neededItem = carportNeededItems.get(i);

            if(i > carportNeededItems.size() - 1 ) {
                // MAKE SHED
                neededItem = shedNeededItems.get(i);
            }

            List<ProductLine> itemProductLines = generateItemProductlines(neededItem, allproducts, carportChoice);

            for(ProductLine pl: itemProductLines) {
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

    public HashMap<Integer, Integer> loadAllLengths() {
        HashMap<Integer, Integer> lengths = new HashMap<>();

        ProductMapper p = new ProductMapper(connectionPool);
        try {
            lengths = p.getLengths();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return lengths;
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

    private List<ProductLine> generateItemProductlines (String neededitem, List<ProductDTO> allproducts, CarportChoices carportChoice) {
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

    public List<ProductLine> calculateRemProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        double carportLengthRem = carportLength;
        int maxLengthRem=0;
        int minLengthRem=0;
        int maxLengthRemId=0;
        int minLengthRemId=0;
        int remMultiplier=2;
        List<ProductDTO> remProducts = new ArrayList<>();
        List<ProductLine> returnList = new ArrayList<>();
        HashMap<Integer, Integer> lengths = loadAllLengths();
        for (int i=0 ; i<allproducts.size(); i++){
            if(allproducts.get(i).getProducttype().equals("rem")){
                remProducts.add(allproducts.get(i));
            }
        }
        ProductDTO rem = remProducts.get(0);

        for (Integer i : lengths.keySet()){
            if (lengths.get(i)>maxLengthRem) {
                maxLengthRem = lengths.get(i);
                maxLengthRemId = i;
            }
        }

        while(carportLengthRem>=maxLengthRem){
            ProductLine productLineRem = new ProductLine(rem.getIdproduct(),remMultiplier, maxLengthRemId, calculateTotalProductPrice(allproducts, rem.getIdproduct(), remMultiplier, maxLengthRem));
            returnList.add(productLineRem);
            carportLengthRem=carportLengthRem-maxLengthRem;
        }

        while(carportLengthRem>0){
            minLengthRem = maxLengthRem;
            for (Integer i : lengths.keySet()){
                if (lengths.get(i)<=minLengthRem && lengths.get(i)>=carportLengthRem){
                    minLengthRem = lengths.get(i);
                    minLengthRemId = i;
                }

            }
            ProductLine productLineRem = new ProductLine(rem.getIdproduct(),remMultiplier, minLengthRemId, calculateTotalProductPrice(allproducts, rem.getIdproduct(), remMultiplier, minLengthRem));
            returnList.add(productLineRem);
            carportLengthRem=carportLengthRem-minLengthRem;
        }
        return returnList;
    }

    private List<ProductLine> calculateBeslagProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    public List<ProductLine> calculateSpærProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        double carportWidthSpær = carportWidth;
        double carportLengthSpær = carportLength;
        double carportLengthSpærCalc = 0;
        int maxLengthSpær=0;
        int minLengthSpær=0;
        int maxLengthSpærId=0;
        int minLengthSpærId=0;
        int spærMultiplier=0;
        double spærWidth=0;
        List<ProductDTO> spærProducts = new ArrayList<>();
        List<ProductDTO> beslagProducts = new ArrayList<>();
        List<ProductLine> returnList = new ArrayList<>();

        HashMap<Integer, Integer> lengths = loadAllLengths();

        for (int i=0 ; i<allproducts.size(); i++){
            if(allproducts.get(i).getProducttype().equals("rem")){
                spærProducts.add(allproducts.get(i));
            } if(allproducts.get(i).getProducttype().equals("beslag")){
                beslagProducts.add(allproducts.get(i));
            }
        }
        ProductDTO spær = spærProducts.get(0);
        ProductDTO beslagHøjre = beslagProducts.get(0);
        ProductDTO beslagVenstre = beslagProducts.get(1);

        spærWidth = spær.getWidth();
        spærWidth=spærWidth/10;
        carportLengthSpærCalc=carportLengthSpær-spærWidth;
        carportLengthSpærCalc=carportLengthSpærCalc/(60+spærWidth)+1;
        spærMultiplier = (int) Math.ceil(carportLengthSpærCalc);

        for (Integer i : lengths.keySet()){
            if (lengths.get(i)>maxLengthSpær) {
                maxLengthSpær = lengths.get(i);
                maxLengthSpærId = i;
            }
        }

        while(carportWidthSpær>=maxLengthSpær){
            ProductLine productLineSpær = new ProductLine(spær.getIdproduct(),spærMultiplier, maxLengthSpærId, calculateTotalProductPrice(allproducts, spær.getIdproduct(), spærMultiplier, maxLengthSpær));
            returnList.add(productLineSpær);
            carportWidthSpær=carportWidthSpær-maxLengthSpær;
        }

        while(carportWidthSpær>0){
            minLengthSpær = maxLengthSpær;
            for (Integer i : lengths.keySet()){
                    if (lengths.get(i)<=minLengthSpær && lengths.get(i)>=carportWidthSpær){
                        minLengthSpær = lengths.get(i);
                        minLengthSpærId = i;
                    }

            }
            ProductLine productLineSpær = new ProductLine(spær.getIdproduct(),spærMultiplier, minLengthSpærId, calculateTotalProductPrice(allproducts, spær.getIdproduct(), spærMultiplier, minLengthSpær));
            returnList.add(productLineSpær);
            carportWidthSpær=carportWidthSpær-minLengthSpær;
        }

        return returnList;
    }

    private List<ProductLine> calculateHulbåndProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    private List<ProductLine> calculateSternProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    private List<ProductLine> calculateTagProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    private List<ProductLine> calculateVandbrætProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    private List<ProductDTO> getAllNeededProducts (List<ProductDTO> allproducts, String type) {
        List<ProductDTO> returnList = new ArrayList<>();

        for(ProductDTO p: allproducts) {
            if(p.getProducttype().equals(type)){
                returnList.add(p);

            }

        }

        return returnList;
    }

    public double calculateTotalProductPrice (List<ProductDTO> allproducts, int productID, int amount, int length) {
        double totalProductPrice = 0;

        double priceMeasurment = 0;
        for(ProductDTO p: allproducts) {
            if(p.getIdproduct() == productID) {
                priceMeasurment = p.getPricemeasurment();
            }

        }

        double lengthDouble = (double) length;
        double amountDouble = (double) amount;
        totalProductPrice = priceMeasurment*(lengthDouble/100)*amountDouble;

        return totalProductPrice;
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




}
