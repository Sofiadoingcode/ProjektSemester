package dat.startcode.model.entities;

public class Customer {
    private int id;
    private String name;
    private int zipCode;
    private String city;
    private String phoneNumber;
    private String email;
    private int idOrder;
    private double finalPrice;

    public Customer(int id, String name, int zipCode, String city, String phoneNumber, String email, int idOrder, double finalPrice) {
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idOrder = idOrder;
        this.finalPrice = finalPrice;
    }

    public int getId() {
        return id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
