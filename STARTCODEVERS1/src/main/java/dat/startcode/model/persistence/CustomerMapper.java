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

public class CustomerMapper implements ICustomerMapper {
    ConnectionPool connectionPool;

    public CustomerMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Customer> getAllNonAcceptedRequests() throws DatabaseException {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT idcustomer, name, zipcode, phonenumber, email, c.city, o.isAccepted, o.idorder, o.finalprice FROM `customer` INNER JOIN city c USING(zipcode) INNER JOIN `order` o USING(idCustomer) WHERE isAccepted = '0'";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("idCustomer");
                    String name = rs.getString("name");
                    int zipCode = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int idOrder = rs.getInt("idorder");
                    double finalPrice = rs.getDouble("finalprice");

                    Customer customer = new Customer(customerID, name, zipCode, city, phoneNumber, email, idOrder, finalPrice);
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

        String sql = "SELECT idcustomer, name, zipcode, phonenumber, email, c.city, o.isAccepted, o.isPaid, o.idorder, o.finalprice FROM `customer` INNER JOIN city c USING(zipcode) INNER JOIN `order` o USING(idCustomer) WHERE isAccepted='1' AND isPaid='0'";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("idcustomer");
                    String name = rs.getString("name");
                    int zipCode = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int idOrder = rs.getInt("idorder");
                    double finalPrice = rs.getDouble("finalprice");

                    Customer customer = new Customer(customerID, name, zipCode, city, phoneNumber, email, idOrder, finalPrice);
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

        String sql = "SELECT idcustomer, name, zipcode, phonenumber, email, c.city, o.isPaid, o.idorder, o.finalprice FROM `customer` INNER JOIN city c USING(zipcode) INNER JOIN `order` o USING(idCustomer) WHERE isPaid='1'";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int customerId = rs.getInt("idcustomer");
                    String name = rs.getString("name");
                    int zipCode = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int idOrder = rs.getInt("idorder");
                    double finalPrice = rs.getDouble("finalprice");

                    Customer customer = new Customer(customerId, name, zipCode, city, phoneNumber, email, idOrder, finalPrice);
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
                isDeleted = true;
            } catch (Exception E) {
                System.out.println(E);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Elementet blev ikke fjernet3 ");
        }
        return isDeleted;
    }

    public boolean deleteAccount(String username, String password) throws DatabaseException {
        boolean isDeleted = false;

        try (Connection connection = connectionPool.getConnection()) {

            String sql = "delete from `user` where username = ? AND password = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();
                isDeleted = true;
            } catch (Exception E) {
                System.out.println(E);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Adminen blev ikke fjernet3 ");
        }
        return isDeleted;
    }

    public int checkDeletedId(String username, String password) throws DatabaseException {
        String sql = "SELECT iduser, username, password FROM fogarchive.user WHERE username = ? AND password = ?";
        int idUser = 0;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    idUser = rs.getInt("iduser");
                }

            } catch (Exception E) {
                System.out.println(E);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Adminen blev ikke fjernet3 ");
        }
        return idUser;
    }

    public boolean acceptRequest(int orderId) throws DatabaseException {
        boolean isAccepted = false;
        try (Connection connection = connectionPool.getConnection()) {
            String sql = "UPDATE `fogarchive`.`order` SET `isAccepted` = '1' WHERE `idorder` = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return isAccepted;
    }

    public boolean unAcceptRequest(int orderId) throws DatabaseException {
        boolean isPaid = false;
        try (Connection connection = connectionPool.getConnection()) {
            String sql = "UPDATE `fogarchive`.`order` SET `isAccepted` = '0' WHERE `idorder` = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return isPaid;
    }

    public boolean payForRequest(int orderId) throws DatabaseException {
        boolean isPaid = false;
        try (Connection connection = connectionPool.getConnection()) {
            String sql = "UPDATE `fogarchive`.`order` SET `isPaid` = '1' WHERE `idorder` = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return isPaid;
    }

}
