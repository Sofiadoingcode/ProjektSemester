package dat.startcode.model.entities;

public class Product {
    private int productId, amount;
    private String name, category, unit, productType;
    private double height,width, price;


    public Product(int id, String name, String category, String unit, double price) {
        this.productId = id;
        this.name = name;
        this.category = category;
        this.unit = unit;
        this.price = price;
        this.amount = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getProductId() {
        return productId;
    }



    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", unit='" + unit + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
