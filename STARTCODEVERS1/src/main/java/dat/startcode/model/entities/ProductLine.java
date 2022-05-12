package dat.startcode.model.entities;

public class ProductLine {
    private String name;
    private int amount;
    private double price;
    private int length;


    public ProductLine(String name, int amount, double price, int length) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
