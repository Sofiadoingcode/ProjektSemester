package dat.startcode.model.entities;

public class Request {
    private int idorder;
    private int idcustomer;
    private int idbom;
    private boolean isAccepted;
    private boolean isPaid;
    private double finalPrice;
    private int iduser;
    private int idcarportchoices;

    public Request(int idorder, int idcustomer, int idbom, boolean isAccepted, boolean isPaid, double finalPrice, int iduser, int idcarportchoices) {
        this.idorder = idorder;
        this.idcustomer = idcustomer;
        this.idbom = idbom;
        this.isAccepted = isAccepted;
        this.isPaid = isPaid;
        this.finalPrice = finalPrice;
        this.iduser = iduser;
        this.idcarportchoices = idcarportchoices;
    }

    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    public int getIdbom() {
        return idbom;
    }

    public void setIdbom(int idbom) {
        this.idbom = idbom;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdcarportchoices() {
        return idcarportchoices;
    }

    public void setIdcarportchoices(int idcarportchoices) {
        this.idcarportchoices = idcarportchoices;
    }

    @Override
    public String toString() {
        return "Request{" +
                "idorder=" + idorder +
                ", idcustomer=" + idcustomer +
                ", idbom=" + idbom +
                ", isAccepted=" + isAccepted +
                ", isPaid=" + isPaid +
                ", finalPrice=" + finalPrice +
                ", iduser=" + iduser +
                ", idcarportchoices=" + idcarportchoices +
                '}';
    }
}
