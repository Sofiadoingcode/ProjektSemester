package dat.startcode.model.entities;

public class Customer {
    private int id;
    private String name;
    private int zipcode;
    private String city;
    private String phonenumber;
    private String email;
    private int idorder;
    private double finalprice;

    public Customer(int id, String name, int zipcode, String city, String phonenumber, String email, int idorder, double finalprice) {
        this.id = id;
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.phonenumber = phonenumber;
        this.email = email;
        this.idorder = idorder;
        this.finalprice = finalprice;
    }

    public int getId() {
        return id;
    }

    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
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

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(double finalprice) {
        this.finalprice = finalprice;
    }
}
