package dat.startcode.model.DTOs;

public class ProductDTO {

    int idproduct;
    String name;
    String unit;
    int idcategory;
    double pricemeasurment;
    double height;
    double width;
    int amount;
    String producttype;

    public ProductDTO(int idproduct, String name, String unit, int idcategory, double pricemeasurment, double height, double width, int amount, String producttype) {
        this.idproduct = idproduct;
        this.name = name;
        this.unit = unit;
        this.idcategory = idcategory;
        this.pricemeasurment = pricemeasurment;
        this.height = height;
        this.width = width;
        this.amount = amount;
        this.producttype = producttype;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public double getPricemeasurment() {
        return pricemeasurment;
    }

    public void setPricemeasurment(double pricemeasurment) {
        this.pricemeasurment = pricemeasurment;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "idproduct=" + idproduct +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", idcategory=" + idcategory +
                ", pricemeasurment=" + pricemeasurment +
                ", height=" + height +
                ", width=" + width +
                ", amount=" + amount +
                ", producttype='" + producttype + '\'' +
                '}';
    }
}
