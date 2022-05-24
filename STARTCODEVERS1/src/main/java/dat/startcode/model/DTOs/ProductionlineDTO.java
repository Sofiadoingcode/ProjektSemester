package dat.startcode.model.DTOs;

public class ProductionLineDTO {

    int idProductionLine;
    String name;
    int amount;
    int length;
    int category;
    String unit;
    double totalProductPrice;

    public ProductionLineDTO(int idProductionLine, String name, int amount, int length, int category, String unit, double totalProductPrice) {
        this.idProductionLine = idProductionLine;
        this.name = name;
        this.amount = amount;
        this.length = length;
        this.category = category;
        this.unit = unit;
        this.totalProductPrice = totalProductPrice;

    }

    public int getIdProductionLine() {
        return idProductionLine;
    }

    public void setIdProductionLine(int idProductionLine) {
        this.idProductionLine = idProductionLine;
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

    public double getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
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
