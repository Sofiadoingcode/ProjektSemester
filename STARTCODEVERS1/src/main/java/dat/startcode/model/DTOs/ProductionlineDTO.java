package dat.startcode.model.DTOs;

public class ProductionlineDTO {

    int idproductionline;
    String name;
    int amount;
    int length;
    int category;
    String unit;
    double totalproductprice;

    public ProductionlineDTO (int idproductionline, String name, int amount, int length, int category, String unit, double totalproductprice) {
        this.idproductionline = idproductionline;
        this.name = name;
        this.amount = amount;
        this. length = length;
        this.category = category;
        this.unit = unit;
        this. totalproductprice = totalproductprice;

    }

    public int getIdproductionline() {
        return idproductionline;
    }

    public void setIdproductionline(int idproductionline) {
        this.idproductionline = idproductionline;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getTotalproductprice() {
        return totalproductprice;
    }

    public void setTotalproductprice(double totalproductprice) {
        this.totalproductprice = totalproductprice;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
