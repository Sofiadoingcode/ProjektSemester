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
    private int spærHeight = 0;
    private int spærAntal = 14;
    private int tagHeight = 0;
    private ConnectionPool connectionPool;

    public BOMAlgorithm() {
        this.connectionPool = ApplicationStart.getConnectionPool();


    }

    public BOMAlgorithm(ConnectionPool connectionPool) throws DatabaseException {
        this.connectionPool = connectionPool;


    }

    public List<ProductLine> generateBOM(CarportChoices carportChoice) {

        List<ProductLine> fullbom = new ArrayList<>();

        List<ProductDTO> allproducts = loadAllProducts();

        List<String> carportNeededItems = returnNeededListCarport();


        List<String> shedNeededItems = returnNeededListShed();

        for (int i = 0; i < carportNeededItems.size() + shedNeededItems.size() - 1; i++) {

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


    public List<ProductDTO> loadAllProducts() {
        List<ProductDTO> allproducts = new ArrayList<>();

        BOMMapper bomMapper = new BOMMapper(connectionPool);

        try {
            allproducts = bomMapper.getAllProductDTOs();

        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return allproducts;

    }

    private HashMap<Integer, Integer> loadAllLengths() {
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

    private List<ProductLine> generateItemProductlines(String neededitem, List<ProductDTO> allproducts, CarportChoices carportChoice) {
        List<ProductLine> onlyThisItemProductionlines = new ArrayList<>();

        double carportHeight = carportChoice.getHeight() * 100;
        double carportWidth = carportChoice.getWidth() * 100 ;
        double carportLength = carportChoice.getLength() * 100;

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
                onlyThisItemProductionlines = calculateTagProductLines(allproducts, carportWidth, carportLength);
                break;
            case "vandbræt":
                onlyThisItemProductionlines = calculateVandbrætProductLines(allproducts, carportHeight, carportWidth, carportLength);
                break;
        }


        return onlyThisItemProductionlines;
    }

    public List<ProductLine> calculateStolpeProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();
        List<ProductDTO> allStolper = getAllNeededProducts(allproducts, "stolpe");
        System.out.println("Alle stolper: " + allStolper.size());
        System.out.println("Stolpetype: " + allStolper.get(0));
        HashMap<Integer, Integer> lengths = loadAllLengths();
        HashMap<Integer, Integer> allLargerLengths = new HashMap<>();

        double carportLengthWithoutExtra = carportLength - 1.1;

        int stolpeLength = (int) carportHeight - spærHeight - tagHeight;
        int stolpeId = 0;

        for (Integer key : lengths.keySet()) {
            int value = lengths.get(key);
            if (value >= stolpeLength) {
                allLargerLengths.put(key, value);
            }

        }

        int minimumValue = Integer.MAX_VALUE;
        int minimumValueKey = 0;
        for (Integer key : allLargerLengths.keySet()) {
            int value = lengths.get(key);

            if (value < minimumValue) {
                minimumValue = value;
                minimumValueKey = key;
            }

        }

        int lengthID = minimumValueKey;
        double lengthSide = 0;
        double lengthBack = 0;
        int antalStolperCorner = 4;
        int antalStolperSide = 0;
        int antalStolperBack = 0;
        int antalStolperOVERALL = 0;

        while (true) {
            lengthSide += 300;
            if (lengthSide < carportLengthWithoutExtra) {
                antalStolperSide++;
            } else {
                break;
            }
        }
        antalStolperSide *= 2;

        while (true) {
            lengthBack += 300;
            if (lengthBack < carportWidth) {
                antalStolperBack++;
            } else {
                break;
            }
        }
        antalStolperOVERALL = antalStolperCorner + antalStolperSide + antalStolperBack;

        for (int i = 0; i < allStolper.size(); i++) {

            if (allStolper.get(i).getName().equals("trykimp. Stolpe")) {
                stolpeId = allStolper.get(i).getIdproduct();
                break;
            }
        }
//        int productId = getAllNeededProducts();
        double totalStolpePrice = calculateTotalProductPrice(allproducts, stolpeId, antalStolperOVERALL, minimumValue);
        ProductLine stolpeTid = new ProductLine(stolpeId, antalStolperOVERALL, lengthID, totalStolpePrice);
        returnList.add(stolpeTid);
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

    public List<ProductLine> calculateHulbåndProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();
        List<ProductDTO> allHulbånd = getAllNeededProducts(allproducts, "hulbånd");
        List<ProductDTO> allSkruer = getAllNeededProducts(allproducts, "skrue");
        int beslagsskrueId = 0;
        int hulbåndId = 0;
        int antalBeslagsSkruer = spærAntal * 4;
        int maengdeHulbaand = 0;
        double carportDiagonalIAnden = (double) (carportLength * carportLength) + (double) (carportWidth * carportWidth);
        int carportDiagonal = (int)Math.sqrt(carportDiagonalIAnden); 
        if (carportDiagonal <= 500) {
            maengdeHulbaand = 1;
        } else if (carportDiagonal <= 1000) {
            maengdeHulbaand = 2;
        }

        for (int i = 0; i < allHulbånd.size(); i++) {

            if (allHulbånd.get(i).getName().equals("hulbånd 1x20 mm. 10 mtr.")) {
                hulbåndId = allHulbånd.get(i).getIdproduct();
                break;
            }
        }


        for (int i = 0; i < allSkruer.size(); i++) {

            if (allSkruer.get(i).getName().equals("beslagskruer")) {
                beslagsskrueId = allSkruer.get(i).getIdproduct();
                break;
            }
        }

        antalBeslagsSkruer = (int)Math.ceil((double)antalBeslagsSkruer/200);

        double totalHulbåndPrice = calculateTotalProductPrice(allproducts, hulbåndId, maengdeHulbaand, 0);
        double totalBeslagsskruerPrice = calculateTotalProductPrice(allproducts, beslagsskrueId, antalBeslagsSkruer, 0);

        System.out.println("MængdeHulbånd: " + maengdeHulbaand);
        ProductLine antalHulbaand = new ProductLine(hulbåndId, maengdeHulbaand, 0, totalHulbåndPrice);
        returnList.add(antalHulbaand);
        ProductLine beslagsskruer = new ProductLine(beslagsskrueId, antalBeslagsSkruer, 0, totalBeslagsskruerPrice);
        returnList.add(beslagsskruer);
        return returnList;
    }

    private List<ProductLine> calculateSternProductLines(List<ProductDTO> allproducts, double carportHeight,
                                                         double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


        return returnList;
    }

    private List<ProductLine> calculateTagProductLines(List<ProductDTO> allproducts, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();

        List<ProductDTO> neededItemsOnly = getAllNeededProducts(allproducts, "tag");

        double carportLengthLeft = carportLength + 5;

        int productID = 0;
        int amount = 0;
        int lengthID = 0;
        double totalproductprice = 0;

        double overlap = 20;

        for(ProductDTO p: neededItemsOnly) {
            if(p.getIdproduct() == 8) {
                productID = p.getIdproduct();
            }
        }

        // FIRST FIND OUT HOW MANY THERE CAN BE WIDTH

        double onePlateWidth = 109;

        double fullCarportWidth = carportWidth;

        double amountDouble = (fullCarportWidth - onePlateWidth) / (onePlateWidth - overlap);

        amount = (int) Math.ceil(amountDouble);

        // FIGURE OUT LENGTHS
        HashMap<Integer, Integer> lengths = loadAllLengths();


        boolean hasNotCalculatedEverything = true;

        while(hasNotCalculatedEverything) {
            HashMap<Integer, Integer> allLargerLengths = new HashMap<>();

            int biggestValue = 0;
            int biggestValueKey = 0;
            for (Integer key : lengths.keySet()) {
                int value = lengths.get(key);
                if (value >= carportLengthLeft) {
                    allLargerLengths.put(key, value);
                }

                if (value > biggestValue) {
                    biggestValue = value;
                    biggestValueKey = key;
                }

            }


            if (allLargerLengths.isEmpty()) {

                lengthID = biggestValueKey;
                carportLengthLeft = carportLengthLeft - biggestValue + overlap;

                totalproductprice = calculateTotalProductPrice(allproducts,productID, amount,biggestValue);

                ProductLine pr = new ProductLine(productID, amount, lengthID, totalproductprice);

                returnList.add(pr);


            } else if(!allLargerLengths.isEmpty()) {

                int minimumValue = biggestValue;
                int minimumValueKey = 0;
                for (Integer key : allLargerLengths.keySet()) {
                    int value = lengths.get(key);

                    if (value < minimumValue) {
                        minimumValue = value;
                        minimumValueKey = key;
                    }

                }


                lengthID = minimumValueKey;

                totalproductprice = calculateTotalProductPrice(allproducts,productID, amount,minimumValue);
                System.out.println("TT: " + totalproductprice);
                ProductLine pr = new ProductLine(productID, amount, lengthID, totalproductprice);

                returnList.add(pr);

                hasNotCalculatedEverything = false;
            }

        }


        // REGN SKRUER UD

        List<ProductDTO> neededScrews = getAllNeededProducts(allproducts, "skrue");

        double productAmount = 0;

        for(ProductDTO p: neededScrews) {
            if(p.getIdproduct() == 9) {
                productID = p.getIdproduct();
                productAmount = p.getAmount();
            }
        }

        double fullCarportAreaM = fullCarportWidth/100 * carportLength/100;
        int neededScrewsOverAll =  (int) Math.ceil(fullCarportAreaM * 12);

        amount = (int) Math.ceil(neededScrewsOverAll/productAmount);

        lengthID = 0;

        totalproductprice = calculateTotalProductPrice(allproducts, productID, amount, 0);

        ProductLine pr = new ProductLine(productID, amount, lengthID, totalproductprice);

        returnList.add(pr);



        return returnList;
    }

    private List<ProductLine> calculateVandbrætProductLines(List<ProductDTO> allproducts, double carportHeight,
                                                            double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();


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

    private double calculateTotalProductPrice(List<ProductDTO> allproducts, int productID, int amount, int length) {
        double totalProductPrice = 0;

        double priceMeasurment = 0;
        for (ProductDTO p : allproducts) {
            if (p.getIdproduct() == productID) {
                priceMeasurment = p.getPricemeasurment();
            }

        }

        double lengthDouble = (double) length;
        double amountDouble = (double) amount;
        if (length == 0) {
            totalProductPrice = priceMeasurment * amountDouble;
        } else {
            totalProductPrice = priceMeasurment * (lengthDouble / 100) * amountDouble;
        }

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
