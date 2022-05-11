package dat.startcode.model.DTOs;

public class BOMDTO {

    int idbom;
    double totalprice;
    String description;

    public BOMDTO(int idbom, double totalprice, String description) {
        this.idbom = idbom;
        this.totalprice = totalprice;
        this.description = description;

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
}
