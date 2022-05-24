package dat.startcode.model.entities;

public class Request {
    private int idOrder;
    private int idCustomer;
    private int idBOM;
    private boolean isAccepted;
    private boolean isPaid;
    private double finalPrice;
    private int idUser;
    private int idCarportChoices;

    public Request(int idOrder, int idCustomer, int idBOM, boolean isAccepted, boolean isPaid, double finalPrice, int idUser, int idCarportChoices) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.idBOM = idBOM;
        this.isAccepted = isAccepted;
        this.isPaid = isPaid;
        this.finalPrice = finalPrice;
        this.idUser = idUser;
        this.idCarportChoices = idCarportChoices;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdBOM() {
        return idBOM;
    }

    public void setIdBOM(int idBOM) {
        this.idBOM = idBOM;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCarportChoices() {
        return idCarportChoices;
    }

    public void setIdCarportChoices(int idCarportChoices) {
        this.idCarportChoices = idCarportChoices;
    }

    @Override
    public String toString() {
        return "Request{" +
                "idorder=" + idOrder +
                ", idcustomer=" + idCustomer +
                ", idbom=" + idBOM +
                ", isAccepted=" + isAccepted +
                ", isPaid=" + isPaid +
                ", finalPrice=" + finalPrice +
                ", iduser=" + idUser +
                ", idcarportchoices=" + idCarportChoices +
                '}';
    }
}
