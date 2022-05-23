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
    SVG svg;
    List<ProductDTO> allProducts;
    private String description;
    private double totalBOMPrice;
    private int spærFullAmount;
    private double sternHeight;
    private int stolpeAmount;
    private ConnectionPool connectionPool;
    private ProductDTO remField;
    private int spærDistance;

    public BOMAlgorithm() throws DatabaseException {
        this.connectionPool = ApplicationStart.getConnectionPool();
        this.allProducts = loadAllProducts();

    }

    public BOMAlgorithm(ConnectionPool connectionPool) throws DatabaseException {
        this.connectionPool = connectionPool;

    }

    public List<ProductLine> generateBOM(CarportChoices carportChoice) {
        List<ProductLine> fullBOM = generateBOMProductLines(carportChoice);

        this.totalBOMPrice = calculateTotalBomPrice(fullBOM);
        this.description = createDescription(carportChoice);
        
        this.svg = generateSvgDrawing(carportChoice.getLength(), carportChoice.getWidth(), stolpeAmount);

        return fullBOM;
    }

    private double calculateTotalBomPrice(List<ProductLine> fullBOM) {
        double totalPrice = 0;

        for (ProductLine p : fullBOM) {
            totalPrice += p.getTotalProductPrice();
        }

        return totalPrice;
    }

    private String createDescription(CarportChoices carportChoice) {
        // HARDCODED -> Should be coded correctly

        String description = "";

        double carportWidth = carportChoice.getWidth();
        double carportLength = carportChoice.getLength();

        description += "<strong>Carport: " + carportWidth + " x " + carportLength + " m</strong>";
        description += "<br>";
        description += "<br>";
        description += "<strong>Spærtype:</strong> Fladt";
        description += "<br>";
        description += "<strong>Remtype: </strong>Spærtræ 45x195 mm";
        description += "<br>";
        description += "<strong>Tagmateriale: </strong>Plastmo Ecolite blåtonet";


        return description;
    }


    private List<ProductLine> generateBOMProductLines(CarportChoices carportChoice) {


        List<ProductLine> fullBOM = new ArrayList<>();


        List<String> carportNeededItems = returnNeededListCarport();

        List<String> shedNeededItems = returnNeededListShed();

        for (int i = 0; i < carportNeededItems.size() + shedNeededItems.size(); i++) {

            String neededItem = carportNeededItems.get(i);

            if (i > carportNeededItems.size() - 1) {
                // MAKE SHED
                neededItem = shedNeededItems.get(i);
            }

            List<ProductLine> itemProductLines = generateItemProductlines(neededItem, carportChoice);

            for (ProductLine pl : itemProductLines) {
                fullBOM.add(pl);

            }

        }


        return fullBOM;
    }

    private List<ProductDTO> loadAllProducts() {
        List<ProductDTO> allProducts = new ArrayList<>();

        BOMMapper bomMapper = new BOMMapper(connectionPool);

        try {
            allProducts = bomMapper.getAllProductDTOs();

        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return allProducts;

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
        neededElements.add("rem");
        neededElements.add("spær");
        neededElements.add("stern");
        neededElements.add("vandbræt");
        neededElements.add("hulbånd");
        neededElements.add("tag");
        neededElements.add("stolpe");

        return neededElements;
    }

    private List<String> returnNeededListShed() {
        List<String> neededItems = new ArrayList<>();

        return neededItems;
    }

    private List<ProductLine> generateItemProductlines(String neededItem, CarportChoices carportChoice) {
        List<ProductLine> onlyThisItemProductionLines = new ArrayList<>();

        double carportHeight = carportChoice.getHeight() * 100;
        double carportWidth = carportChoice.getWidth() * 100;
        double carportLength = carportChoice.getLength() * 100;

        switch (neededItem) {
            case "stolpe":
                onlyThisItemProductionLines = calculateStolpeProductLines(carportHeight, carportWidth, carportLength);
                break;
            case "rem":
                onlyThisItemProductionLines = calculateRemProductLines(carportLength);
                break;
            case "spær":
                onlyThisItemProductionLines = calculateSpærProductLines(carportWidth, carportLength);
                break;
            case "hulbånd":
                onlyThisItemProductionLines = calculateHulbåndProductLines(carportWidth, carportLength);
                break;
            case "stern":
                onlyThisItemProductionLines = calculateSternProductLines(carportWidth, carportLength);
                break;
            case "tag":
                onlyThisItemProductionLines = calculateTagProductLines(carportWidth, carportLength);
                break;
            case "vandbræt":
                onlyThisItemProductionLines = calculateVandbrætProductLines(carportWidth, carportLength);
                break;
        }


        return onlyThisItemProductionLines;
    }

    private List<ProductLine> calculateStolpeProductLines(double carportHeight, double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();
        List<ProductDTO> allStolper = getAllNeededProducts("stolpe");

        HashMap<Integer, Integer> lengths = loadAllLengths();
        HashMap<Integer, Integer> allLargerLengths = new HashMap<>();

        double carportLengthWithoutExtra = carportLength - 1.1;

        int stolpeLength = (int) carportHeight - (int) sternHeight + 1; //1 = 1 meter under jorden
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

        Integer lengthID = minimumValueKey;
        double lengthSide = 0;
        double lengthBack = 0;
        int stolpeCornerAmount = 4;
        int stolpeSideAmount = 0;
        int stolpeBackAmount = 0;

        while (true) {
            lengthSide += 300;
            if (lengthSide < carportLengthWithoutExtra) {
                stolpeSideAmount++;
            } else {
                break;
            }
        }
        stolpeSideAmount *= 2;

        while (true) {
            lengthBack += 300;
            if (lengthBack < carportWidth - 70) { // 70 CM fra kanten
                stolpeBackAmount++;
            } else {
                break;
            }
        }
        stolpeAmount = stolpeCornerAmount + stolpeSideAmount + stolpeBackAmount;

        for (int i = 0; i < allStolper.size(); i++) {

            if (allStolper.get(i).getName().equals("trykimp. Stolpe")) {
                stolpeId = allStolper.get(i).getIdProduct();
                break;
            }
        }
//        int productId = getAllNeededProducts();
        double totalStolpePrice = calculateTotalProductPrice(stolpeId, stolpeAmount, minimumValue);
        ProductLine stolpeProductLine = new ProductLine(stolpeId, stolpeAmount, lengthID, totalStolpePrice);
        returnList.add(stolpeProductLine);
        return returnList;
    }

    private List<ProductLine> calculateRemProductLines(double carportLength) {
        double carportLengthRem = carportLength;
        int maxLengthRem = 0;
        int minLengthRem = 0;
        Integer maxLengthRemId = 0;
        Integer minLengthRemId = 0;
        int remMultiplier = 2;
        List<ProductDTO> remProducts = new ArrayList<>();
        List<ProductLine> returnList = new ArrayList<>();
        HashMap<Integer, Integer> lengths = loadAllLengths();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductType().equals("rem")) {
                remProducts.add(allProducts.get(i));
            }
        }
        ProductDTO rem = remProducts.get(0);
        this.remField = rem;
        for (Integer i : lengths.keySet()) {
            if (lengths.get(i) > maxLengthRem) {
                maxLengthRem = lengths.get(i);
                maxLengthRemId = i;
            }
        }

        while (carportLengthRem >= maxLengthRem) {
            ProductLine productLineRem = new ProductLine(rem.getIdProduct(), remMultiplier, maxLengthRemId, calculateTotalProductPrice(rem.getIdProduct(), remMultiplier, maxLengthRem));
            returnList.add(productLineRem);
            carportLengthRem = carportLengthRem - maxLengthRem;
        }

        while (carportLengthRem > 0) {
            minLengthRem = maxLengthRem;
            for (Integer i : lengths.keySet()) {
                if (lengths.get(i) <= minLengthRem && lengths.get(i) >= carportLengthRem) {
                    minLengthRem = lengths.get(i);
                    minLengthRemId = i;
                }

            }
            ProductLine productLineRem = new ProductLine(rem.getIdProduct(), remMultiplier, minLengthRemId, calculateTotalProductPrice(rem.getIdProduct(), remMultiplier, minLengthRem));
            returnList.add(productLineRem);
            carportLengthRem = carportLengthRem - minLengthRem;
        }
        return returnList;
    }


    private List<ProductLine> calculateSpærProductLines(double carportWidth, double carportLength) {
        double carportWidthSpær = carportWidth;
        double carportLengthSpær = carportLength;
        double carportLengthSpærCalc = 0;
        int maxLengthSpær = 0;
        int minLengthSpær = 0;
        Integer maxLengthSpærId = 0;
        Integer minLengthSpærId = 0;
        int spærAmount = 0;
        Integer beslagLængdeId = null;
        int beslagAmount = 0;
        double beslagsSkruerMultiplier = 9;
        double beslagsSkruerAmountCalc = 0;
        int beslagsSkruerAmount = 0;
        Integer beslagsSkruerLængdeId = null;
        double spærHeight = 0;
        List<ProductDTO> spærProducts = new ArrayList<>();
        List<ProductDTO> beslagProducts = new ArrayList<>();
        List<ProductDTO> beslagsSkruerProducts = new ArrayList<>();
        List<ProductLine> returnList = new ArrayList<>();

        HashMap<Integer, Integer> lengths = loadAllLengths();

        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductType().equals("rem")) {
                spærProducts.add(allProducts.get(i));
            }
            if (allProducts.get(i).getProductType().equals("beslag")) {
                beslagProducts.add(allProducts.get(i));
            }
            if (allProducts.get(i).getName().equals("beslagskruer")) {
                beslagsSkruerProducts.add(allProducts.get(i));
            }
        }

        ProductDTO spær = spærProducts.get(0);
        ProductDTO beslagRight = beslagProducts.get(0);
        ProductDTO beslagLeft = beslagProducts.get(1);
        ProductDTO beslagsSkruer = beslagsSkruerProducts.get(0);

        spærHeight = spær.getHeight();
        spærHeight = spærHeight / 10;
        carportLengthSpærCalc = carportLengthSpær - spærHeight;
        spærAmount = (int) Math.ceil(carportLengthSpærCalc / (58 + spærHeight) + 1);
        spærDistance=(int) carportLengthSpærCalc/(spærAmount-1);
        
        spærFullAmount = spærAmount;

        for (Integer i : lengths.keySet()) {
            if (lengths.get(i) > maxLengthSpær) {
                maxLengthSpær = lengths.get(i);
                maxLengthSpærId = i;
            }
        }

        while (carportWidthSpær >= maxLengthSpær) {
            ProductLine productLineSpær = new ProductLine(spær.getIdProduct(), spærAmount, maxLengthSpærId, calculateTotalProductPrice(spær.getIdProduct(), spærAmount, maxLengthSpær));
            returnList.add(productLineSpær);
            carportWidthSpær = carportWidthSpær - maxLengthSpær;
        }

        while (carportWidthSpær > 0) {
            minLengthSpær = maxLengthSpær;
            for (Integer i : lengths.keySet()) {
                if (lengths.get(i) <= minLengthSpær && lengths.get(i) >= carportWidthSpær) {
                    minLengthSpær = lengths.get(i);
                    minLengthSpærId = i;
                }

            }
            ProductLine productLineSpær = new ProductLine(spær.getIdProduct(), spærAmount, minLengthSpærId, calculateTotalProductPrice(spær.getIdProduct(), spærAmount, minLengthSpær));
            returnList.add(productLineSpær);
            carportWidthSpær = carportWidthSpær - minLengthSpær;
        }
        for (ProductLine productLine : returnList) {
            beslagAmount += productLine.getAmount();
        }

        beslagsSkruerAmountCalc = (int) Math.ceil(beslagAmount * beslagsSkruerMultiplier);
        beslagsSkruerAmountCalc = (int) Math.ceil(beslagsSkruerAmountCalc / beslagsSkruer.getAmount());
        beslagsSkruerAmount = (int) beslagsSkruerAmountCalc;


        ProductLine productLineBeslagRight = new ProductLine(beslagRight.getIdProduct(), beslagAmount, beslagLængdeId, calculateTotalProductPrice(beslagRight.getIdProduct(), beslagAmount, 0));
        returnList.add(productLineBeslagRight);

        ProductLine productLineBeslagLeft = new ProductLine(beslagLeft.getIdProduct(), beslagAmount, beslagLængdeId, calculateTotalProductPrice(beslagLeft.getIdProduct(), beslagAmount, 0));
        returnList.add(productLineBeslagLeft);

        ProductLine productLineBeslagsSkruer = new ProductLine(beslagsSkruer.getIdProduct(), beslagsSkruerAmount, beslagsSkruerLængdeId, calculateTotalProductPrice(beslagsSkruer.getIdProduct(), beslagsSkruerAmount, 0));
        returnList.add(productLineBeslagsSkruer);

        return returnList;
    }


    private List<ProductLine> calculateHulbåndProductLines(double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();
        List<ProductDTO> allHulbaand = getAllNeededProducts("hulbånd");
        List<ProductDTO> allSkruer = getAllNeededProducts("skrue");
        int beslagsSkrueId = 0;
        int hulbaandId = 0;
        int beslagsSkruerAmount = spærFullAmount * 4;
        int hulbaandAmount = 0;
        double carportDiagonalSquared = carportLength * carportLength + carportWidth * carportWidth;
        int carportDiagonal = (int) Math.sqrt(carportDiagonalSquared);
        if (carportDiagonal <= 500) {
            hulbaandAmount = 1;
        } else if (carportDiagonal <= 1000) {
            hulbaandAmount = 2;
        }

        for (int i = 0; i < allHulbaand.size(); i++) {

            if (allHulbaand.get(i).getName().equals("hulbånd 1x20 mm. 10 mtr.")) {
                hulbaandId = allHulbaand.get(i).getIdProduct();
                break;
            }
        }


        for (int i = 0; i < allSkruer.size(); i++) {

            if (allSkruer.get(i).getName().equals("beslagskruer")) {
                beslagsSkrueId = allSkruer.get(i).getIdProduct();
                break;
            }
        }

        beslagsSkruerAmount = (int) Math.ceil((double) beslagsSkruerAmount / 200);

        double totalHulbaandPrice = calculateTotalProductPrice(hulbaandId, hulbaandAmount, 0);
        double totalBeslagsSkruerPrice = calculateTotalProductPrice(beslagsSkrueId, beslagsSkruerAmount, 0);


        ProductLine HulbaandLine = new ProductLine(hulbaandId, hulbaandAmount, null, totalHulbaandPrice);
        returnList.add(HulbaandLine);
        ProductLine beslagsSkruer = new ProductLine(beslagsSkrueId, beslagsSkruerAmount, null, totalBeslagsSkruerPrice);
        returnList.add(beslagsSkruer);
        return returnList;
    }


    private List<ProductLine> calculateTagProductLines(double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();

        List<ProductDTO> neededItemsOnly = getAllNeededProducts("tag");

        double carportLengthLeft = carportLength + 5;

        int productId = 0;
        int amount = 0;
        Integer lengthId = 0;
        double totalProductPrice = 0;

        double overlap = 20;

        for (ProductDTO p : neededItemsOnly) {
            if (p.getIdProduct() == 8) {
                productId = p.getIdProduct();
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

        while (hasNotCalculatedEverything) {
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

                lengthId = biggestValueKey;
                carportLengthLeft = carportLengthLeft - biggestValue + overlap;

                totalProductPrice = calculateTotalProductPrice(productId, amount, biggestValue);

                ProductLine pr = new ProductLine(productId, amount, lengthId, totalProductPrice);

                returnList.add(pr);


            } else if (!allLargerLengths.isEmpty()) {

                int minimumValue = biggestValue;
                int minimumValueKey = 0;
                for (Integer key : allLargerLengths.keySet()) {
                    int value = lengths.get(key);

                    if (value < minimumValue) {
                        minimumValue = value;
                        minimumValueKey = key;
                    }

                }


                lengthId = minimumValueKey;

                totalProductPrice = calculateTotalProductPrice(productId, amount, minimumValue);
                ProductLine pr = new ProductLine(productId, amount, lengthId, totalProductPrice);

                returnList.add(pr);

                hasNotCalculatedEverything = false;
            }

        }


        // REGN SKRUER UD

        List<ProductDTO> neededScrews = getAllNeededProducts("skrue");

        double productAmount = 0;

        for (ProductDTO p : neededScrews) {
            if (p.getIdProduct() == 9) {
                productId = p.getIdProduct();
                productAmount = p.getAmount();
            }
        }

        double fullCarportAreaM = fullCarportWidth / 100 * carportLength / 100;
        int neededScrewsOverall = (int) Math.ceil(fullCarportAreaM * 12);

        amount = (int) Math.ceil(neededScrewsOverall / productAmount);

        lengthId = null;

        totalProductPrice = calculateTotalProductPrice(productId, amount, 0);

        ProductLine pr = new ProductLine(productId, amount, lengthId, totalProductPrice);

        returnList.add(pr);


        return returnList;
    }


    private List<ProductLine> calculateSternProductLines(double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();

        List<ProductDTO> sterns = getAllNeededProducts("stern");
        ProductDTO rem = remField;
        List<ProductDTO> upperStern = new ArrayList<>();
        ProductDTO theUpperStern = null;
        for (ProductDTO product : sterns) {

            if (product.getWidth() >= rem.getWidth()) {
                upperStern.add(product);
                try {
                    if (theUpperStern.getPriceMeasurement() < product.getPriceMeasurement())
                        theUpperStern = product;

                } catch (NullPointerException e) {
                    theUpperStern = product;
                }
            }

        }
        sternHeight = theUpperStern.getWidth();
        int[] arr = getLengthsNeeded(carportLength);
        double price = calculateTotalProductPrice(theUpperStern.getIdProduct(), arr[1], arr[0]);


        ProductLine productLine = new ProductLine(theUpperStern.getIdProduct(), arr[1] * 2, arr[2], price);
        returnList.add(productLine);


        arr = getLengthsNeeded(carportWidth);
        price = calculateTotalProductPrice(theUpperStern.getIdProduct(), arr[1], arr[0]);
        productLine = new ProductLine(theUpperStern.getIdProduct(), arr[1] * 2, arr[2], price);

        returnList.add(productLine);


        List<ProductDTO> neededScrews = getAllNeededProducts("skrue");
        ProductDTO productDTO = null;


        for (ProductDTO product : neededScrews) {
            if (product.getHeight() == 4.5 && product.getWidth() == 60)
                productDTO = product;

        }
        if (productDTO == null) {
            productDTO = neededScrews.get(0);

        }

        int amount = 1;

        ProductLine screw = new ProductLine(productDTO.getIdProduct(), amount, null, amount * productDTO.getPriceMeasurement());

        returnList.add(screw);

        return returnList;
    }


    private List<ProductLine> calculateVandbrætProductLines(double carportWidth, double carportLength) {

        List<ProductLine> returnList = new ArrayList<>();
        List<ProductDTO> allUsableProducts = getAllNeededProducts("brædt");

        for (int i = 0; i < allUsableProducts.size(); i++) {
            ProductDTO temp = allUsableProducts.get(i);


            if (temp.getWidth() <= 25) {
                allUsableProducts.remove(i);
                i--;
            }
        }
        ProductDTO vandbræt = null;

        for (ProductDTO product : allUsableProducts) {
            try {
                if (product.getPriceMeasurement() < vandbræt.getPriceMeasurement())
                    vandbræt = product;

            } catch (NullPointerException e) {
                vandbræt = product;
            }
        }

        int[] vandbrætSide = getLengthsNeeded(carportLength);
        int[] vandbrætFront = getLengthsNeeded(carportWidth);

        int vandbrætSideAmount = vandbrætSide[1] * 2;
        int vandbrætFrontAmount = vandbrætFront[1];


        ProductLine vandbrætSideProductLine = new ProductLine(vandbræt.getIdProduct(), vandbrætSide[1] * 2, vandbrætSide[2], vandbrætSideAmount * vandbræt.getPriceMeasurement() * vandbrætSide[0] / 100);
        ProductLine vandbrætFrontProductLine = new ProductLine(vandbræt.getIdProduct(), vandbrætFront[1], vandbrætFront[2], vandbrætFrontAmount * vandbræt.getPriceMeasurement() * vandbrætFront[0] / 100);

        returnList.add(vandbrætFrontProductLine);
        returnList.add(vandbrætSideProductLine);

        return returnList;
    }


    private List<ProductDTO> getAllNeededProducts(String type) {
        List<ProductDTO> returnList = new ArrayList<>();

        for (ProductDTO p : allProducts) {
            if (p.getProductType().equals(type)) {
                returnList.add(p);

            }

        }

        return returnList;
    }

    private double calculateTotalProductPrice(int productId, int amount, int length) {
        double totalProductPrice;

        double priceMeasurement = 0;
        for (ProductDTO p : allProducts) {
            if (p.getIdProduct() == productId) {
                priceMeasurement = p.getPriceMeasurement();
            }

        }

        double lengthDouble = (double) length;
        double amountDouble = (double) amount;
        if (length == 0) {
            totalProductPrice = priceMeasurement * amountDouble;
        } else {
            totalProductPrice = priceMeasurement * (lengthDouble / 100) * amountDouble;
        }

        return totalProductPrice;
    }


    private int[] getLengthsNeeded(double length) {
        //calculates the number of equally long planks

        HashMap<Integer, Integer> lengths = loadAllLengths();
        int maxLengths = lengths.get(1);
        for (int i = 1; i <= lengths.size(); i++) {
            int tempLength = lengths.get(i);

            if (tempLength > maxLengths) {
                maxLengths = tempLength;
            }
        }


        double numberNeeded = 1;
        numberNeeded = Math.ceil(length / maxLengths);
        int delta_l = maxLengths;
        int theLength = lengths.get(1);
        int k = 0;
        for (int i = 1; i <= lengths.size(); i++) {
            //int templength = (int) (lengths.get(i) * numberNeeded);
            double tempDelta_l = lengths.get(i) * numberNeeded - length;

            if (delta_l > tempDelta_l && tempDelta_l >= 0) {
                delta_l = (int) tempDelta_l;
                theLength = lengths.get(i);
                k = i;
            }

        }
        return new int[]{theLength, (int) numberNeeded, k};

    }

    public String getDescription() {
        return description;
    }

    public double getTotalBOMPrice() {
        return totalBOMPrice;
    }

    private SVG generateSvgDrawing(double carportLength, double carportWidth, int stolpeAmount) {

        int stolpeDisplacementLengthFront = 110;
        int stolpeDisplacementWidth = 35;
        int stolpeCounter = 0;
        int carportWidthCM=(int)(carportWidth*100);
        int carportLengthCM=(int)(carportLength*100);
        int carportWidthWithDisplacement =  carportWidthCM - (stolpeDisplacementWidth * 2); //Change 600 to carportWidth
        int carportLengthWithDisplacement = carportLengthCM - (stolpeDisplacementLengthFront); //Change 800 to carportLength
        int stolpeAmountWidth = 0;
        int stolpeAmountLength = 0;
        int distanceBetweenStolpeWidth = 0;
        int distanceBetweenStolpeLength = 0;
        int svgDisplacementLength = carportLengthCM/10;
        int svgDisplacementWidth = carportWidthCM/10;

        SVG svg = new SVG(0, 0, "0 0 " + carportLengthCM + " " + carportWidthCM, 100, 100);

        SVG innerSvg = new SVG(svgDisplacementLength, svgDisplacementWidth, "0 0 " + carportLengthCM + " " + carportWidthCM, 89, 80);


        svg.addRect(0, 0, carportWidthCM, carportLengthCM);

        svg.addLine((int) (svgDisplacementLength*0.75), svgDisplacementWidth, (int) (svgDisplacementLength*0.75), carportWidthCM-svgDisplacementWidth, "url(#beginArrow);", "none");
        svg.addLine(svgDisplacementLength, (int) (carportWidthCM-(svgDisplacementWidth*0.75)), carportLengthCM-svgDisplacementLength, (int) (carportWidthCM-(svgDisplacementWidth*0.75)), "none;", "url(#endArrow)");

        svg.addText(1.375, svgDisplacementLength/2, carportWidthCM/2, -90, carportWidthCM/100 +" m");

        svg.addText(1.375, carportLengthCM/2, carportWidthCM-(svgDisplacementWidth/2), 0, carportLengthCM/100 + " m");

        for (int x = 0; x < spærFullAmount-1; x++) {
            svg.addText(0.875, (int) (svgDisplacementLength+(spærDistance/2)+((0.80*spærDistance)*x)-4), (int) (svgDisplacementWidth*0.75), 0, spærDistance + " cm");
        }

        /*Square*/
        
        innerSvg.addRect(0, 0, carportWidthCM, carportLengthCM);

        /*Rem*/

        innerSvg.addRect(0, stolpeDisplacementWidth, 4, carportLengthCM);
        innerSvg.addRect(0, carportWidthCM - stolpeDisplacementWidth, 4, carportLengthCM);

        /*Spær*/

        for (int x = 0; x < spærFullAmount; x++) {
            innerSvg.addRect(spærDistance * x, 0, carportWidthCM, 4);
        }

        /*Stolper*/

        while (true) {
            stolpeCounter += 300;
            if (stolpeCounter < carportWidthWithDisplacement) {
                stolpeAmountWidth++;
            } else {
                break;
            }
        }

        stolpeAmountLength = (stolpeAmount - stolpeAmountWidth - 4) / 2;

        distanceBetweenStolpeWidth = carportWidthWithDisplacement / (stolpeAmountWidth + 1);
        distanceBetweenStolpeLength = carportLengthWithDisplacement / (stolpeAmountLength + 1);


        innerSvg.addRectStolpe(stolpeDisplacementLengthFront, stolpeDisplacementWidth, 12, 12);
        innerSvg.addRectStolpe(stolpeDisplacementLengthFront, carportWidthCM - stolpeDisplacementWidth, 12, 12);
        innerSvg.addRectStolpe(carportLengthCM, stolpeDisplacementWidth, 12, 12);
        innerSvg.addRectStolpe(carportLengthCM, carportWidthCM - stolpeDisplacementWidth, 12, 12);

        for (int i = 1; i <= stolpeAmountWidth; i++) {
            innerSvg.addRectStolpe(carportLengthCM, stolpeDisplacementWidth + distanceBetweenStolpeWidth * i, 12, 12); //Change 900 to carportLength
        }

        for (int i = 1; i <= stolpeAmountLength; i++) {
            innerSvg.addRectStolpe(stolpeDisplacementLengthFront + distanceBetweenStolpeLength * i, stolpeDisplacementWidth, 12, 12); //Change 900 to carportLength
        }

        for (int i = 1; i <= stolpeAmountLength; i++) {
            innerSvg.addRectStolpe(stolpeDisplacementLengthFront + distanceBetweenStolpeLength * i, carportWidthCM - stolpeDisplacementWidth, 12, 12); //Change 900 to carportLength
        }
        
        /*Hulbånd*/


        innerSvg.addDottedLine(0, stolpeDisplacementWidth, carportLengthCM, carportWidthCM - stolpeDisplacementWidth, 3);
        innerSvg.addDottedLine(0, carportWidthCM - stolpeDisplacementWidth, carportLengthCM, stolpeDisplacementWidth, 3);

        svg.addSvg(innerSvg);


        return svg;
    }
    public SVG getSvg(){
        return svg;
    }
}       
