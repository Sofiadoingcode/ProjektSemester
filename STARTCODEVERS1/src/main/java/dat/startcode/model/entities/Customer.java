package dat.startcode.model.entities;

public class Customer {
    private int id;
    private String name;
    private int zipcode;
    private String city;
    private String phonenumber;
    private String email;

    public Customer(int id, String name, int zipcode, String city, String phonenumber, String email) {
        this.id = id;
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public int getId() {
        return id;
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
}
