package dat.startcode.model.entities;

public class ProductLine {
    private int productID;
    private int amount;
    private int lengthID;
    private double totalproductprice;


    public ProductLine(int productID, int amount, int lengthID, double totalproductprice) {
        this.productID = productID;
        this.amount = amount;
        this.lengthID = lengthID;
        this.totalproductprice = totalproductprice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLengthID() {
        return lengthID;
    }

    public void setLengthID(int lengthID) {
        this.lengthID = lengthID;
    }

    public double getTotalproductprice() {
        return totalproductprice;
    }

    public void setTotalproductprice(double totalproductprice) {
        this.totalproductprice = totalproductprice;
    }

    @Override
    public String toString() {
        return "ProductLine{" +
                "productID=" + productID +
                ", amount=" + amount +
                ", lengthID=" + lengthID +
                ", totalproductprice=" + totalproductprice +
                '}';
    }
}
