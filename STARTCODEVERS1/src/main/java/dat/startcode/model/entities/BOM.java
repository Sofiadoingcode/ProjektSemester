package dat.startcode.model.entities;

import java.util.ArrayList;
import java.util.List;

public class BOM {

    private double height;
    private double width;
    private double length;
    private boolean wantsShed;
    private double roofAngle;




    public BOM (double height, double width, double length, boolean wantsShed, double roofAngle) {
        this.height = height;
        this.width = width;
        this.length = length;
        this.wantsShed = wantsShed;

    }





    public List<ProductLine> generateFullBom () {

        List<ProductLine> fullbom = new ArrayList<>();

        while() {
            // CHECK IF ALL CRITERIA FOR CARPORT ARE MET//


            //METHOD TO FIND THE RIGHT PRODUCT

            String name = generateProductName();

            int length = calculateProductLength();

            int amount = calculateProductAmount();


            double price = calculateFullPrice(amount);




            ProductLine newProductline = new ProductLine(name, amount, price, length);
            fullbom.add(newProductline);
        }


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
    private double calculateFullPrice(int amount){

        return 0;
    }







}
