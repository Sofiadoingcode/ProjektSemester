package dat.startcode.model.entities;

public class Product {
    private int productID;
    private String name, category, unit, productType;
    private int height,width, price, amount;


    public Product(int id, String name, String category, String unit, int price) {
        this.productID = id;
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

    public int getProductID() {
        return productID;
    }



    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product product = (Product) obj;

        return (product.getProductType().equals(productType) && product.getWidth()== getWidth() && product.getHeight()==getHeight() && product.getPrice()==getPrice());

    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
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
