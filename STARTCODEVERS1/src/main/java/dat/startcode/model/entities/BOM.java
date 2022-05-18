package dat.startcode.model.entities;

import java.util.ArrayList;
import java.util.List;

public class BOM {

    int BOMID;
    double totalprice;
    String description;



    public BOM (int BOMID, double totalprice, String description) {
        this.BOMID = BOMID;
        this.totalprice = totalprice;
        this.description = description;


    }





//    public List<ProductLine> generateFullBom () {
//
//        List<ProductLine> fullbom = new ArrayList<>();
//
//        while(true) {
//            // CHECK IF ALL CRITERIA FOR CARPORT ARE MET//
//
//
//            //METHOD TO FIND THE RIGHT PRODUCT
//
//            String name = generateProductName();
//
//            int length = calculateProductLength();
//
//            int amount = calculateProductAmount();
//
//
//            double price = calculateFullPrice(amount);
//
//
//
//
//            ProductLine newProductline = new ProductLine(name, amount, price, length);
//            fullbom.add(newProductline);
//        }
//
//
//        return ;
//    }



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
    private double calculateFullPrice(int amount){

        return 0;
    }







}
