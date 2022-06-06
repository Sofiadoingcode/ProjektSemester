package dat.startcode.model.entities;

import java.util.Objects;

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

    public void setProductId(int productId) {
        this.productId = productId;
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
    public int hashCode() {
        return Objects.hash(getAmount(), getName(), getCategory(), getUnit(), getProductType(), getHeight(), getWidth(), getPrice());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getAmount() == product.getAmount() && Double.compare(product.getHeight(), getHeight()) == 0 && Double.compare(product.getWidth(), getWidth()) == 0 && Double.compare(product.getPrice(), getPrice()) == 0 && getName().equals(product.getName()) && getCategory().equals(product.getCategory()) && getUnit().equals(product.getUnit()) && getProductType().equals(product.getProductType());
    }

}
