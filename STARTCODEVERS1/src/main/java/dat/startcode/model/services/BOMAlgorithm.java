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

    List<ProductDTO> allProducts;
    private String description;
    private double totalBomPrice;

    private double spærHeight;
    private int spærFullAmount;
    private double sternHeight;
    private ConnectionPool connectionPool;
    private ProductDTO remField;


    public BOMAlgorithm() throws DatabaseException {
        this.connectionPool = ApplicationStart.getConnectionPool();
        this.allProducts = loadAllProducts();

    }

    public BOMAlgorithm(ConnectionPool connectionPool) throws DatabaseException {
        this.connectionPool = connectionPool;

    }

    public List<ProductLine> generateBOM (CarportChoices carportChoice) {
        List<ProductLine> fullbom = generateBOMProductLines(carportChoice);

        this.totalBomPrice = calculateTotalBomPrice(fullbom);
        this.description = createDescription(carportChoice);


        return fullbom;
    }

    private double calculateTotalBomPrice (List<ProductLine> fullBom) {
        double totalPrice = 0;

        for(ProductLine p: fullBom) {
            totalPrice += p.getTotalproductprice();
        }

        return totalPrice;
    }

    private String createDescription (CarportChoices carportChoice) {
        // HARDCODED -> Should be coded correctly

        String description = "";

        double carportWidth = carportChoice.getWidth();
        double carportLength = carportChoice.getLength();

        description += "Carport: " + carportWidth + " x " + carportLength + " m\n";

        description += "\nSpærtype: Fladt";

        description += "\nRemtype: Spærtræ 45x195 mm";

        description += "\nTagmateriale: Plastmo Ecolite blåtonet";


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
        double carportWidth = carportChoice.getWidth() * 100 ;
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

        int stolpeLength = (int) carportHeight - (int) spærHeight - (int) sternHeight;
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
        double totalStolpePrice = calculateTotalProductPrice(stolpeId, antalStolperOVERALL, minimumValue);
        ProductLine stolpeTid = new ProductLine(stolpeId, antalStolperOVERALL, lengthID, totalStolpePrice);
        returnList.add(stolpeTid);
        return returnList;
    }

    private List<ProductLine> calculateRemProductLines(double carportLength) {
        double carportLengthRem = carportLength;
        int maxLengthRem=0;
        int minLengthRem=0;
        int maxLengthRemId=0;
        int minLengthRemId=0;
        int remMultiplier=2;
        List<ProductDTO> remProducts = new ArrayList<>();
        List<ProductLine> returnList = new ArrayList<>();
        HashMap<Integer, Integer> lengths = loadAllLengths();
        for (int i=0 ; i<allProducts.size(); i++){
            if(allProducts.get(i).getProducttype().equals("rem")){
                remProducts.add(allProducts.get(i));
            }
        }
        ProductDTO rem = remProducts.get(0);
        this.remField = rem;
        for (Integer i : lengths.keySet()){
            if (lengths.get(i)>maxLengthRem) {
                maxLengthRem = lengths.get(i);
                maxLengthRemId = i;
            }
        }

        while(carportLengthRem>=maxLengthRem){
            ProductLine productLineRem = new ProductLine(rem.getIdproduct(),remMultiplier, maxLengthRemId, calculateTotalProductPrice(rem.getIdproduct(), remMultiplier, maxLengthRem));
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
            ProductLine productLineRem = new ProductLine(rem.getIdproduct(),remMultiplier, minLengthRemId, calculateTotalProductPrice(rem.getIdproduct(), remMultiplier, minLengthRem));
            returnList.add(productLineRem);
            carportLengthRem=carportLengthRem-minLengthRem;
        }
        return returnList;
    }


    private List<ProductLine> calculateSpærProductLines(double carportWidth, double carportLength) {
        double carportWidthSpær = carportWidth;
        double carportLengthSpær = carportLength;
        double carportLengthSpærCalc = 0;
        int maxLengthSpær=0;
        int minLengthSpær=0;
        int maxLengthSpærId=0;
        int minLengthSpærId=0;
        int spærAmount=0;
        int beslagLængdeId=0;
        int beslagAmount=0;
        double beslagSkruerMultiplier=9;
        double beslagSkruerAmountCalc=0;
        int beslagSkruerAmount=0;
        int beslagSkruerLængdeId=0;
        double spærWidth=0;
        List<ProductDTO> spærProducts = new ArrayList<>();
        List<ProductDTO> beslagProducts = new ArrayList<>();
        List<ProductDTO> beslagSkruerProducts = new ArrayList<>();
        List<ProductLine> returnList = new ArrayList<>();

        HashMap<Integer, Integer> lengths = loadAllLengths();

        for (int i=0 ; i<allProducts.size(); i++){
            if(allProducts.get(i).getProducttype().equals("rem")){
                spærProducts.add(allProducts.get(i));
            } if(allProducts.get(i).getProducttype().equals("beslag")){
                beslagProducts.add(allProducts.get(i));
            }
            if(allProducts.get(i).getName().equals("beslagskruer")){
                beslagSkruerProducts.add(allProducts.get(i));
            }
        }
        ProductDTO spær = spærProducts.get(0);
        spærHeight = spær.getHeight();
        ProductDTO beslagHøjre = beslagProducts.get(0);
        ProductDTO beslagVenstre = beslagProducts.get(1);
        ProductDTO beslagSkruer = beslagSkruerProducts.get(0);

        spærWidth = spær.getWidth();
        spærWidth=spærWidth/10;
        carportLengthSpærCalc=carportLengthSpær-spærWidth;
        carportLengthSpærCalc=carportLengthSpærCalc/(60+spærWidth)+1;
        spærAmount = (int) Math.ceil(carportLengthSpærCalc);
        spærFullAmount = spærAmount;

        for (Integer i : lengths.keySet()){
            if (lengths.get(i)>maxLengthSpær) {
                maxLengthSpær = lengths.get(i);
                maxLengthSpærId = i;
            }
        }

        while(carportWidthSpær>=maxLengthSpær){
            ProductLine productLineSpær = new ProductLine(spær.getIdproduct(),spærAmount, maxLengthSpærId, calculateTotalProductPrice(spær.getIdproduct(), spærAmount, maxLengthSpær));
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
            ProductLine productLineSpær = new ProductLine(spær.getIdproduct(),spærAmount, minLengthSpærId, calculateTotalProductPrice(spær.getIdproduct(), spærAmount, minLengthSpær));
            returnList.add(productLineSpær);
            carportWidthSpær=carportWidthSpær-minLengthSpær;
        }
        for (ProductLine productLine : returnList) {
            beslagAmount+=productLine.getAmount();
        }

        beslagSkruerAmountCalc = (int) Math.ceil(beslagAmount*beslagSkruerMultiplier);
        beslagSkruerAmountCalc = (int) Math.ceil(beslagSkruerAmountCalc/beslagSkruer.getAmount());
        beslagSkruerAmount =(int) beslagSkruerAmountCalc;


        ProductLine productLineBeslagHøjre = new ProductLine(beslagHøjre.getIdproduct(),beslagAmount, beslagLængdeId, calculateTotalProductPrice(beslagHøjre.getIdproduct(), beslagAmount, beslagLængdeId));
        returnList.add(productLineBeslagHøjre);

        ProductLine productLineBeslagVenstre = new ProductLine(beslagVenstre.getIdproduct(),beslagAmount, beslagLængdeId, calculateTotalProductPrice(beslagVenstre.getIdproduct(), beslagAmount, beslagLængdeId));
        returnList.add(productLineBeslagVenstre);

        ProductLine productLineBeslagSkruer = new ProductLine(beslagSkruer.getIdproduct(),beslagSkruerAmount, beslagSkruerLængdeId, calculateTotalProductPrice(beslagSkruer.getIdproduct(), beslagSkruerAmount, beslagSkruerLængdeId));
        returnList.add(productLineBeslagSkruer);

        return returnList;
    }

    private List<ProductLine> calculateHulbåndProductLines(double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();
        List<ProductDTO> allHulbånd = getAllNeededProducts("hulbånd");
        List<ProductDTO> allSkruer = getAllNeededProducts("skrue");
        int beslagsskrueId = 0;
        int hulbåndId = 0;
        int antalBeslagsSkruer = spærFullAmount * 4;
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

        double totalHulbåndPrice = calculateTotalProductPrice(hulbåndId, maengdeHulbaand, 0);
        double totalBeslagsskruerPrice = calculateTotalProductPrice(beslagsskrueId, antalBeslagsSkruer, 0);

        System.out.println("MængdeHulbånd: " + maengdeHulbaand);
        ProductLine antalHulbaand = new ProductLine(hulbåndId, maengdeHulbaand, 0, totalHulbåndPrice);
        returnList.add(antalHulbaand);
        ProductLine beslagsskruer = new ProductLine(beslagsskrueId, antalBeslagsSkruer, 0, totalBeslagsskruerPrice);
        returnList.add(beslagsskruer);
        return returnList;
    }


    private List<ProductLine> calculateTagProductLines(double carportWidth, double carportLength) {
        List<ProductLine> returnList = new ArrayList<>();

        List<ProductDTO> neededItemsOnly = getAllNeededProducts("tag");

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

                totalproductprice = calculateTotalProductPrice(productID, amount,biggestValue);

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

                totalproductprice = calculateTotalProductPrice(productID, amount,minimumValue);
                System.out.println("TT: " + totalproductprice);
                ProductLine pr = new ProductLine(productID, amount, lengthID, totalproductprice);

                returnList.add(pr);

                hasNotCalculatedEverything = false;
            }

        }


        // REGN SKRUER UD

        List<ProductDTO> neededScrews = getAllNeededProducts("skrue");

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

        totalproductprice = calculateTotalProductPrice(productID, amount, 0);

        ProductLine pr = new ProductLine(productID, amount, lengthID, totalproductprice);

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
        double price = calculateTotalProductPrice(theUpperStern.getIdproduct(), arr[1], arr[0] );


        ProductLine productLine = new ProductLine(theUpperStern.getIdproduct(), arr[1]*2, arr[2], price);
        returnList.add(productLine);


        arr = getLengthsNeeded(carportWidth);
        price = calculateTotalProductPrice(theUpperStern.getIdproduct(), arr[1], arr[0] );
        productLine = new ProductLine(theUpperStern.getIdproduct(), arr[1]*2, arr[2], price);

        returnList.add(productLine);

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

    public String getDescription() {
        return description;
    }

    public double getTotalBomPrice() {
        return totalBomPrice;
    }
}
