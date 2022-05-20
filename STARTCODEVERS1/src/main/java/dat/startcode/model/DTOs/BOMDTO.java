package dat.startcode.model.DTOs;

public class BOMDTO {

    int idbom;
    double totalprice;
    String description;
    String svgDrawing;
    int orderid;


    public BOMDTO(int idbom, double totalprice, String description, int orderid, String svgDrawing) {
        this.idbom = idbom;
        this.totalprice = totalprice;
        this.description = description;
        this.svgDrawing = svgDrawing;
        this.orderid = orderid;

    }


    


    public int getIdbom() {
        return idbom;
    }

    public void setIdbom(int idbom) {
        this.idbom = idbom;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
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

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
}
