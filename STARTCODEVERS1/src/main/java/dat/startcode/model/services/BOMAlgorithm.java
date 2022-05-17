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
    private int spærAntal = 0;
    private int tagHeight = 0;
    private int antalStolper = 0;
    private double length = 0;
    private ConnectionPool connectionPool;

    public BOMAlgorithm() {
        this.connectionPool = ApplicationStart.getConnectionPool();
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
        List<ProductDTO> allStolper = getAllNeededProducts(allproducts, "stolpe");

        double carportLengthWithoutExtra = carportLength - 1.1;
        int stolpeLength = (int)carportHeight - spærHeight - tagHeight;

        while (true) {
            length += 3;
            if (length < carportLengthWithoutExtra) {
                antalStolper++;
            } else {
                break;
            }
        }
//        int productId = getAllNeededProducts();
        ProductLine stolpeTid = new ProductLine(, antalStolper, stolpeLength, null);
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

    private List<ProductLine> calculateHulbåndProductLines(List<ProductDTO> allproducts, double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();
        List<ProductDTO> allStolper = getAllNeededProducts(allproducts, "hulbånd");
        double maengdeHulbaand = 0;
        int carportDiagonal = (int)(carportLength*carportHeight)*(int)(carportWidth*carportWidth);
        if(carportDiagonal<5){
            maengdeHulbaand = 1;
        } else if (5 < carportDiagonal && carportDiagonal < 10){
            maengdeHulbaand = 2;
        }

        ProductLine antalHulbaand = new ProductLine(, maengdeHulbaand, 0, );
        returnList.add(antalHulbaand);
        int antalBeslagsSkruer = spærAntal*4;
        ProductLine beslagsskruer = new ProductLine(, antalBeslagsSkruer, 0, );
        returnList.add(beslagsskruer);
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


}
