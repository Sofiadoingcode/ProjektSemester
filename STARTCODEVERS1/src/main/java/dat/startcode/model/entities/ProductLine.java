package dat.startcode.model.entities;

import java.util.Objects;

public class ProductLine {
    private int productId;
    private int amount;
    private Integer lengthId;
    private double totalProductPrice;


    public ProductLine(int productId, int amount, Integer lengthId, double totalProductPrice) {
        this.productId = productId;
        this.amount = amount;
        this.lengthId = lengthId;
        this.totalProductPrice = totalProductPrice;
    }

    public int getProductId() {
        return productId;
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

    public Integer getLengthId() {
        return lengthId;
    }

    public void setLengthId(int lengthId) {
        this.lengthId = lengthId;
    }

    public double getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductLine)) return false;
        ProductLine that = (ProductLine) o;
        return getProductId() == that.getProductId() && getAmount() == that.getAmount() && Double.compare(that.getTotalProductPrice(), getTotalProductPrice()) == 0 && Objects.equals(getLengthId(), that.getLengthId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getAmount(), getLengthId(), getTotalProductPrice());
    }

    @Override
    public String toString() {
        return "ProductLine{" +
                "productID=" + productId +
                ", amount=" + amount +
                ", lengthID=" + lengthId +
                ", totalproductprice=" + totalProductPrice +
                '}';
    }
}

