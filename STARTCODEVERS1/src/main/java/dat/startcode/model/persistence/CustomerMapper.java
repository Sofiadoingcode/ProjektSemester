package dat.startcode.model.persistence;

import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerMapper implements ICustomerMapper{
    ConnectionPool connectionPool;

    public CustomerMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Customer> getAllNonAcceptedRequests() throws DatabaseException {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT idcustomer, name, zipcode, phonenumber, email, c.city, o.isAccepted, o.idorder FROM `customer` INNER JOIN city c USING(zipcode) INNER JOIN `order` o USING(idCustomer) WHERE isAccepted = '0'";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("idCustomer");
                    String name = rs.getString("name");
                    int zipcode = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int idorder = rs.getInt("idorder");

                    Customer customer = new Customer(customerID, name, zipcode, city, phoneNumber, email, idorder);
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

        String sql = "SELECT idcustomer, name, zipcode, phonenumber, email, c.city, o.isAccepted, o.isPaid, o.idorder FROM `customer` INNER JOIN city c USING(zipcode) INNER JOIN `order` o USING(idCustomer) WHERE isAccepted='1' AND isPaid='0'";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("idcustomer");
                    String name = rs.getString("name");
                    int zipcode = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int idorder = rs.getInt("idorder");

                    Customer customer = new Customer(customerID, name, zipcode, city, phoneNumber, email, idorder);
                    customerList.add(customer);

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }


        return customerList;

    }

    @Override
    public List<Customer> getAllPaidRequests() throws DatabaseException {

        List<Customer> customerList = new ArrayList<>();

        String sql = "SELECT idcustomer, name, zipcode, phonenumber, email, c.city, o.isPaid, o.idorder FROM `customer` INNER JOIN city c USING(zipcode) INNER JOIN `order` o USING(idCustomer) WHERE isPaid='1'";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("idcustomer");
                    String name = rs.getString("name");
                    int zipcode = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int idorder = rs.getInt("idorder");

                    Customer customer = new Customer(customerID, name, zipcode, city, phoneNumber, email, idorder);
                    customerList.add(customer);

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }


        return customerList;

    }

    public boolean deleteOrder(int orderId) throws DatabaseException {
        boolean isDeleted = false;

        try (Connection connection = connectionPool.getConnection()) {

            String sql = "delete from `order` where idOrder = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.executeUpdate();
            } catch (Exception E) {
                System.out.println(E);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Elementet blev ikke fjernet3 ");
        }
        return isDeleted;
    }
}
