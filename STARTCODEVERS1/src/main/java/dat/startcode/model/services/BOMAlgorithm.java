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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BOMAlgorithm {
    SVG svg;
    List<ProductDTO> allProducts;
    private String description;
    private double totalBomPrice;
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
        List<ProductLine> fullbom = generateBOMProductLines(carportChoice);

        this.totalBomPrice = calculateTotalBomPrice(fullbom);
        this.description = createDescription(carportChoice);
        
        this.svg = generateSvgDrawing(carportChoice.getLength(), carportChoice.getWidth(), stolpeAmount);

        return fullbom;
    }

    private double calculateTotalBomPrice(List<ProductLine> fullBom) {
        double totalPrice = 0;

        for (ProductLine p : fullBom) {
            totalPrice += p.getTotalproductprice();
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


        List<ProductLine> fullBom = new ArrayList<>();


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
                fullBom.add(pl);

            }

        }


        return fullBom;
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

    private List<ProductLine> generateItemProductlines(String neededitem, CarportChoices carportChoice) {
        List<ProductLine> onlyThisItemProductionlines = new ArrayList<>();

        double carportHeight = carportChoice.getHeight() * 100;
        double carportWidth = carportChoice.getWidth() * 100;
        double carportLength = carportChoice.getLength() * 100;

        switch (neededitem) {
            case "stolpe":
                onlyThisItemProductionlines = calculateStolpeProductLines(carportHeight, carportWidth, carportLength);
                break;
            case "rem":
                onlyThisItemProductionlines = calculateRemProductLines(carportLength);
                break;
            case "spær":
                onlyThisItemProductionlines = calculateSpærProductLines(carportWidth, carportLength);
                break;
            case "hulbånd":
                onlyThisItemProductionlines = calculateHulbåndProductLines(carportWidth, carportLength);
                break;
            case "stern":
                onlyThisItemProductionlines = calculateSternProductLines(carportWidth, carportLength);
                break;
            case "tag":
                onlyThisItemProductionlines = calculateTagProductLines(carportWidth, carportLength);
                break;
            case "vandbræt":
                onlyThisItemProductionlines = calculateVandbrætProductLines(carportWidth, carportLength);
                break;
        }


        return onlyThisItemProductionlines;
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
        int StolperCornerAmount = 4;
        int StolperSideAmount = 0;
        int StolperBackAmount = 0;

        while (true) {
            lengthSide += 300;
            if (lengthSide < carportLengthWithoutExtra) {
                StolperSideAmount++;
            } else {
                break;
            }
        }
        StolperSideAmount *= 2;

        while (true) {
            lengthBack += 300;
            if (lengthBack < carportWidth - 70) { // 70 CM fra kanten
                StolperBackAmount++;
            } else {
                break;
            }
        }
        stolpeAmount = StolperCornerAmount + StolperSideAmount + StolperBackAmount;

        for (int i = 0; i < allStolper.size(); i++) {

            if (allStolper.get(i).getName().equals("trykimp. Stolpe")) {
                stolpeId = allStolper.get(i).getIdproduct();
                break;
            }
        }
//        int productId = getAllNeededProducts();
        double totalStolpePrice = calculateTotalProductPrice(stolpeId, stolpeAmount, minimumValue);
        ProductLine stolpeTid = new ProductLine(stolpeId, stolpeAmount, lengthID, totalStolpePrice);
        returnList.add(stolpeTid);
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
            if (allProducts.get(i).getProducttype().equals("rem")) {
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
            ProductLine productLineRem = new ProductLine(rem.getIdproduct(), remMultiplier, maxLengthRemId, calculateTotalProductPrice(rem.getIdproduct(), remMultiplier, maxLengthRem));
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
            ProductLine productLineRem = new ProductLine(rem.getIdproduct(), remMultiplier, minLengthRemId, calculateTotalProductPrice(rem.getIdproduct(), remMultiplier, minLengthRem));
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
        double beslagSkruerMultiplier = 9;
        double beslagSkruerAmountCalc = 0;
        int beslagSkruerAmount = 0;
        Integer beslagSkruerLængdeId = null;
        double spærHeight = 0;
        List<ProductDTO> spærProducts = new ArrayList<>();
        List<ProductDTO> beslagProducts = new ArrayList<>();
        List<ProductDTO> beslagSkruerProducts = new ArrayList<>();
        List<ProductLine> returnList = new ArrayList<>();

        HashMap<Integer, Integer> lengths = loadAllLengths();

        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProducttype().equals("rem")) {
                spærProducts.add(allProducts.get(i));
            }
            if (allProducts.get(i).getProducttype().equals("beslag")) {
                beslagProducts.add(allProducts.get(i));
            }
            if (allProducts.get(i).getName().equals("beslagskruer")) {
                beslagSkruerProducts.add(allProducts.get(i));
            }
        }
        ProductDTO spær = spærProducts.get(0);
        ProductDTO beslagRight = beslagProducts.get(0);
        ProductDTO beslagLeft = beslagProducts.get(1);
        ProductDTO beslagSkruer = beslagSkruerProducts.get(0);

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
            ProductLine productLineSpær = new ProductLine(spær.getIdproduct(), spærAmount, maxLengthSpærId, calculateTotalProductPrice(spær.getIdproduct(), spærAmount, maxLengthSpær));
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
            ProductLine productLineSpær = new ProductLine(spær.getIdproduct(), spærAmount, minLengthSpærId, calculateTotalProductPrice(spær.getIdproduct(), spærAmount, minLengthSpær));
            returnList.add(productLineSpær);
            carportWidthSpær = carportWidthSpær - minLengthSpær;
        }
        for (ProductLine productLine : returnList) {
            beslagAmount += productLine.getAmount();
        }

        beslagSkruerAmountCalc = (int) Math.ceil(beslagAmount * beslagSkruerMultiplier);
        beslagSkruerAmountCalc = (int) Math.ceil(beslagSkruerAmountCalc / beslagSkruer.getAmount());
        beslagSkruerAmount = (int) beslagSkruerAmountCalc;


        ProductLine productLineBeslagRight = new ProductLine(beslagRight.getIdproduct(), beslagAmount, beslagLængdeId, calculateTotalProductPrice(beslagRight.getIdproduct(), beslagAmount, 0));
        returnList.add(productLineBeslagRight);

        ProductLine productLineBeslagLeft = new ProductLine(beslagLeft.getIdproduct(), beslagAmount, beslagLængdeId, calculateTotalProductPrice(beslagLeft.getIdproduct(), beslagAmount, 0));
        returnList.add(productLineBeslagLeft);

        ProductLine productLineBeslagSkruer = new ProductLine(beslagSkruer.getIdproduct(), beslagSkruerAmount, beslagSkruerLængdeId, calculateTotalProductPrice(beslagSkruer.getIdproduct(), beslagSkruerAmount, 0));
        returnList.add(productLineBeslagSkruer);

        return returnList;
    }


    private List<ProductLine> calculateHulbåndProductLines(double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();
        List<ProductDTO> allHulbaand = getAllNeededProducts("hulbånd");
        List<ProductDTO> allSkruer = getAllNeededProducts("skrue");
        int beslagsskrueId = 0;
        int hulbaandId = 0;
        int BeslagsSkruerAmount = spærFullAmount * 4;
        int HulbaandAmount = 0;
        double carportDiagonalSquared = (double) (carportLength * carportLength) + (double) (carportWidth * carportWidth);
        int carportDiagonal = (int) Math.sqrt(carportDiagonalSquared);
        if (carportDiagonal <= 500) {
            HulbaandAmount = 1;
        } else if (carportDiagonal <= 1000) {
            HulbaandAmount = 2;
        }

        for (int i = 0; i < allHulbaand.size(); i++) {

            if (allHulbaand.get(i).getName().equals("hulbånd 1x20 mm. 10 mtr.")) {
                hulbaandId = allHulbaand.get(i).getIdproduct();
                break;
            }
        }


        for (int i = 0; i < allSkruer.size(); i++) {

            if (allSkruer.get(i).getName().equals("beslagskruer")) {
                beslagsskrueId = allSkruer.get(i).getIdproduct();
                break;
            }
        }

        BeslagsSkruerAmount = (int) Math.ceil((double) BeslagsSkruerAmount / 200);

        double totalHulbaandPrice = calculateTotalProductPrice(hulbaandId, HulbaandAmount, 0);
        double totalBeslagsskruerPrice = calculateTotalProductPrice(beslagsskrueId, BeslagsSkruerAmount, 0);


        ProductLine HulbaandLine = new ProductLine(hulbaandId, HulbaandAmount, null, totalHulbaandPrice);
        returnList.add(HulbaandLine);
        ProductLine beslagsskruer = new ProductLine(beslagsskrueId, BeslagsSkruerAmount, null, totalBeslagsskruerPrice);
        returnList.add(beslagsskruer);
        return returnList;
    }


    private List<ProductLine> calculateTagProductLines(double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();

        List<ProductDTO> neededItemsOnly = getAllNeededProducts("tag");

        double carportLengthLeft = carportLength + 5;

        int productID = 0;
        int amount = 0;
        Integer lengthID = 0;
        double totalProductPrice = 0;

        double overlap = 20;

        for (ProductDTO p : neededItemsOnly) {
            if (p.getIdproduct() == 8) {
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

                lengthID = biggestValueKey;
                carportLengthLeft = carportLengthLeft - biggestValue + overlap;

                totalProductPrice = calculateTotalProductPrice(productID, amount, biggestValue);

                ProductLine pr = new ProductLine(productID, amount, lengthID, totalProductPrice);

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


                lengthID = minimumValueKey;

                totalProductPrice = calculateTotalProductPrice(productID, amount, minimumValue);
                ProductLine pr = new ProductLine(productID, amount, lengthID, totalProductPrice);

                returnList.add(pr);

                hasNotCalculatedEverything = false;
            }

        }


        // REGN SKRUER UD

        List<ProductDTO> neededScrews = getAllNeededProducts("skrue");

        double productAmount = 0;

        for (ProductDTO p : neededScrews) {
            if (p.getIdproduct() == 9) {
                productID = p.getIdproduct();
                productAmount = p.getAmount();
            }
        }

        double fullCarportAreaM = fullCarportWidth / 100 * carportLength / 100;
        int neededScrewsOverAll = (int) Math.ceil(fullCarportAreaM * 12);

        amount = (int) Math.ceil(neededScrewsOverAll / productAmount);

        lengthID = null;

        totalProductPrice = calculateTotalProductPrice(productID, amount, 0);

        ProductLine pr = new ProductLine(productID, amount, lengthID, totalProductPrice);

        returnList.add(pr);


        return returnList;
    }


    private List<ProductLine> calculateSternProductLines(double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();
        HashMap<Integer, Integer> lengths = loadAllLengths();

        List<ProductDTO> sterns = getAllNeededProducts("stern");
        ProductDTO rem = remField;
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
        sternHeight = theUpperStern.getWidth();
        int[] arr = getLengthsNeeded(carportLength);
        double price = calculateTotalProductPrice(theUpperStern.getIdproduct(), arr[1], arr[0]);


        ProductLine productLine = new ProductLine(theUpperStern.getIdproduct(), arr[1] * 2, arr[2], price);
        returnList.add(productLine);


        arr = getLengthsNeeded(carportWidth);
        price = calculateTotalProductPrice(theUpperStern.getIdproduct(), arr[1], arr[0]);
        productLine = new ProductLine(theUpperStern.getIdproduct(), arr[1] * 2, arr[2], price);

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

        ProductLine screw = new ProductLine(productDTO.getIdproduct(), amount, null, amount * productDTO.getPricemeasurment());

        returnList.add(screw);

        return returnList;
    }


    private List<ProductLine> calculateVandbrætProductLines(double carportWidth, double carportLength) {

        List<ProductLine> returnList = new ArrayList<>();
        HashMap<Integer, Integer> lengths = loadAllLengths();
        List<ProductDTO> allUssableProducts = getAllNeededProducts("brædt");

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

        int[] vandBradSide = getLengthsNeeded(carportLength);
        int[] vandBradFront = getLengthsNeeded(carportWidth);

        int vandBradSideAmount = vandBradSide[1] * 2;
        int vandBradFrontAmount = vandBradFront[1];


        ProductLine vandBradSideProductLine = new ProductLine(theVandBrad.getIdproduct(), vandBradSide[1] * 2, vandBradSide[2], vandBradSideAmount * theVandBrad.getPricemeasurment() * vandBradSide[0] / 100);
        ProductLine vandBradFrontProductLine = new ProductLine(theVandBrad.getIdproduct(), vandBradFront[1], vandBradFront[2], vandBradFrontAmount * theVandBrad.getPricemeasurment() * vandBradFront[0] / 100);

        returnList.add(vandBradFrontProductLine);
        returnList.add(vandBradSideProductLine);

        return returnList;
    }


    private List<ProductDTO> getAllNeededProducts(String type) {
        List<ProductDTO> returnList = new ArrayList<>();

        for (ProductDTO p : allProducts) {
            if (p.getProducttype().equals(type)) {
                returnList.add(p);

            }

        }

        return returnList;
    }

    private double calculateTotalProductPrice(int productID, int amount, int length) {
        double totalProductPrice;

        double priceMeasurment = 0;
        for (ProductDTO p : allProducts) {
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


    private int[] getLengthsNeeded(double length) {
        //calculates the number of equally long planks

        HashMap<Integer, Integer> lengths = loadAllLengths();
        int max_lengths = lengths.get(1);
        for (int i = 1; i <= lengths.size(); i++) {
            int templength = lengths.get(i);

            if (templength > max_lengths) {
                max_lengths = templength;
            }
        }


        double numberNeeded = 1;
        numberNeeded = Math.ceil(length / max_lengths);
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

        }
        return new int[]{theLength, (int) numberNeeded, k};

    }

    public String getDescription() {
        return description;
    }

    public double getTotalBomPrice() {
        return totalBomPrice;
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
        int distanceBewteenStolpeWidth = 0;
        int distanceBewteenStolpeLength = 0;
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

        distanceBewteenStolpeWidth = carportWidthWithDisplacement / (stolpeAmountWidth + 1);
        distanceBewteenStolpeLength = carportLengthWithDisplacement / (stolpeAmountLength + 1);


        innerSvg.addRectStolpe(stolpeDisplacementLengthFront, stolpeDisplacementWidth, 12, 12);
        innerSvg.addRectStolpe(stolpeDisplacementLengthFront, carportWidthCM - stolpeDisplacementWidth, 12, 12);
        innerSvg.addRectStolpe(carportLengthCM, stolpeDisplacementWidth, 12, 12);
        innerSvg.addRectStolpe(carportLengthCM, carportWidthCM - stolpeDisplacementWidth, 12, 12);

        for (int i = 1; i <= stolpeAmountWidth; i++) {
            innerSvg.addRectStolpe(carportLengthCM, stolpeDisplacementWidth + distanceBewteenStolpeWidth * i, 12, 12); //Change 900 to carportLength
        }

        for (int i = 1; i <= stolpeAmountLength; i++) {
            innerSvg.addRectStolpe(stolpeDisplacementLengthFront + distanceBewteenStolpeLength * i, stolpeDisplacementWidth, 12, 12); //Change 900 to carportLength
        }

        for (int i = 1; i <= stolpeAmountLength; i++) {
            innerSvg.addRectStolpe(stolpeDisplacementLengthFront + distanceBewteenStolpeLength * i, carportWidthCM - stolpeDisplacementWidth, 12, 12); //Change 900 to carportLength
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
