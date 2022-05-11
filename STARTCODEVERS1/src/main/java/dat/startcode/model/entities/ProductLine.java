package dat.startcode.model.entities;

public class ProductLine {
    private String name;
    private Product product;
    private int amount;
    private double price, length;


    public ProductLine(String name, Product product, int amount, double price, double length) {
        this.name = name;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public void setLength(double length) {
        this.length = length;
    }
}
