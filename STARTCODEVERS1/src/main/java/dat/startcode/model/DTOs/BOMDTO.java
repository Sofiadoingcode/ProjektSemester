package dat.startcode.model.DTOs;

public class BOMDTO {

    int idBOM;
    double totalPrice;
    String description;
    String svgDrawing;
    int orderId;


    public BOMDTO(int idBOM, double totalPrice, String description, int orderId, String svgDrawing) {
        this.idBOM = idBOM;
        this.totalPrice = totalPrice;
        this.description = description;
        this.svgDrawing = svgDrawing;
        this.orderId = orderId;

    }


    


    public int getIdBOM() {
        return idBOM;
    }

    public void setIdBOM(int idBOM) {
        this.idBOM = idBOM;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSvgDrawing() {
        return svgDrawing;
    }

    public void setSvgDrawing(String svgDrawing) {
        this.svgDrawing = svgDrawing;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
