package dat.startcode.model.persistence;

import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements ICustomerMapper{
    ConnectionPool connectionPool;

    public CustomerMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Customer> getAllNonAcceptedRequests() throws DatabaseException {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT idcustomer, name, zipcode, phonenumber, email, c.city, o.isAccepted FROM `customer` \n" +
                "INNER JOIN city c USING(zipcode) \n" +
                "INNER JOIN `order` o USING(idCustomer)\n" +
                "WHERE isAccepted = '0'";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("idCustomer");
                    String name = rs.getString("name");
                    int zipcode = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String phonenumber = rs.getString("phonenumber");
                    String email = rs.getString("email");
                    Customer customer = new Customer(customerID, name, zipcode, city, phonenumber, email);
                    customerList.add(customer);

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }

        return customerList;
    }

    @Override
    public List<Customer> getAllAcceptedRequests() throws DatabaseException {

        List<Customer> customerList = new ArrayList<>();

        String sql = "SELECT idcustomer, name, zipcode, phonenumber, email, c.city, o.isAccepted FROM `customer` INNER JOIN city c USING(zipcode) INNER JOIN `order` o USING(idCustomer) WHERE isAccepted='1'";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("idcustomer");
                    String name = rs.getString("name");
                    int zipcode = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String phonenumber = rs.getString("phonenumber");
                    String email = rs.getString("email");

                    Customer customer = new Customer(customerID, name, zipcode, city, phonenumber, email);
                    customerList.add(customer);



                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }


        return customerList;

    }

    @Override
    public Customer getAllPaidRequests() throws DatabaseException {
        return null;
    }
}
