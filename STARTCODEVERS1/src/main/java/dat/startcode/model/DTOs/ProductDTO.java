package dat.startcode.model.DTOs;

public class ProductDTO {

    int idProduct;
    String name;
    String unit;
    int idCategory;
    double priceMeasurement;
    double height;
    double width;
    int amount;
    String productType;

    public ProductDTO(int idProduct, String name, String unit, int idCategory, double priceMeasurement, double height, double width, int amount, String productType) {
        this.idProduct = idProduct;
        this.name = name;
        this.unit = unit;
        this.idCategory = idCategory;
        this.priceMeasurement = priceMeasurement;
        this.height = height;
        this.width = width;
        this.amount = amount;
        this.productType = productType;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public double getPriceMeasurement() {
        return priceMeasurement;
    }

    public void setPriceMeasurement(double priceMeasurement) {
        this.priceMeasurement = priceMeasurement;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "idproduct=" + idProduct +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", idcategory=" + idCategory +
                ", pricemeasurment=" + priceMeasurement +
                ", height=" + height +
                ", width=" + width +
                ", amount=" + amount +
                ", producttype='" + productType + '\'' +
                '}';
    }
}
