package dat.startcode.model.persistence;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestMapper {
    private int idShed = 0;
    private int idCarport = 0;
    private int idCustomer = 0;
    private int bomPrice = 0;

    ConnectionPool connectionPool;

    public RequestMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void insertShedChoices(int shedWidth, int shedLength, String floorMaterial) throws DatabaseException {


        Logger.getLogger("web").log(Level.INFO, "");


        try (Connection connection = connectionPool.getConnection()) {

            String sql = "INSERT INTO `fogarchive`.`shed choices` (width, length, floormateriel) VALUES (?, ?,?) ";
            try (PreparedStatement ps1 = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps1.setInt(1, shedWidth);
                ps1.setInt(2, shedLength);
                ps1.setString(3, floorMaterial);
                ps1.executeUpdate();

                //return the generated primary key from sql

                ResultSet generatedKeys = ps1.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idShed = generatedKeys.getInt(1);
                }

            }
        } catch (SQLException e) {
            throw new DatabaseException("Something went wrong with inserting request into database");
        }
    }

    public int insertCarportChoicesShed(int carportHeight, int carportLength, int carportWidth, String roofMaterial, String roofShape, int roofAngle) throws DatabaseException {


        Logger.getLogger("web").log(Level.INFO, "");


        try (Connection connection = connectionPool.getConnection()) {

            System.out.println(" ASdasdasfkaksbfak ");

            String sql = "INSERT INTO `fogarchive`.`carport choices` (height, length, width, roofmateriel, roofshape, roofangle, idshed) VALUES (?, ?, ?, ?, ?, ?, ?) ";
            try (PreparedStatement ps1 = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setInt(1, carportHeight);
                ps1.setInt(2, carportLength);
                ps1.setInt(3, carportWidth);
                ps1.setString(4, roofMaterial);
                ps1.setString(5, roofShape);
                ps1.setInt(6, roofAngle);
                ps1.setInt(7, idShed);
                ps1.executeUpdate();

                //return the generated primary key from sql
                ResultSet generatedKeys = ps1.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idCarport = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Something went wrong with inserting request into database");
        }
        return idCarport;
    }

    public void insertCarportChoices(int carportHeight, int carportLength, int carportWidth, String roofMaterial, String roofShape, int roofAngle) throws DatabaseException {


        Logger.getLogger("web").log(Level.INFO, "");


        try (Connection connection = connectionPool.getConnection()) {

            String sql = "INSERT INTO `fogarchive`.`carport choices` (height, length, width, roofmateriel, roofshape, roofangle) VALUES (?, ?, ?, ?, ?, ?) ";
            try (PreparedStatement ps1 = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setInt(1, carportHeight);
                ps1.setInt(2, carportLength);
                ps1.setInt(3, carportWidth);
                ps1.setString(4, roofMaterial);
                ps1.setString(5, roofShape);
                ps1.setInt(6, roofAngle);
                ps1.executeUpdate();

                //return the generated primary key from sql
                ResultSet generatedKeys = ps1.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idCarport = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Something went wrong with inserting request into database");
        }
    }

    public void insertCustomer(String name, int zipCode, int phoneNumber, String email) throws DatabaseException {


        Logger.getLogger("web").log(Level.INFO, "");


        try (Connection connection = connectionPool.getConnection()) {

            String sql = "INSERT INTO `fogarchive`.`customer` (name, zipcode, phonenumber, email) VALUES (?, ?, ?, ?) ";
            try (PreparedStatement ps1 = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, name);
                ps1.setInt(2, zipCode);
                ps1.setInt(3, phoneNumber);
                ps1.setString(4, email);
                ps1.executeUpdate();

                //return the generated primary key from sql
                ResultSet generatedKeys = ps1.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idCustomer = generatedKeys.getInt(1);
                }

            }
        } catch (SQLException e) {
            throw new DatabaseException("Something went wrong with inserting request into database");
        }
    }

    public void insertRequest(int idUser, int idBom) throws DatabaseException {


        Logger.getLogger("web").log(Level.INFO, "");
        System.out.println("id customer:" + idCustomer + " idbom: " + idBom + " bomPrice: " + bomPrice + " idUser: " + idUser + " idCardport: " + idCarport );

        try (Connection connection = connectionPool.getConnection()) {

            String sql = "INSERT INTO `fogarchive`.`order` (idcustomer, idbom, isAccepted, isPaid, finalPrice, iduser, idcarportchoices) VALUES (?, ?, ?, ?, ?, ?, ?) ";
            try (PreparedStatement ps1 = connection.prepareStatement(sql)) {
                ps1.setInt(1, idCustomer);
                ps1.setInt(2, idBom);
                ps1.setInt(3, 0);
                ps1.setInt(4, 0);
                ps1.setInt(5, bomPrice);
                ps1.setInt(6, idUser);
                ps1.setInt(7, idCarport);
                ps1.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Something went wrong with inserting request into database");
        }
    }

    public void insertFullRequestShed(int shedWidth, int shedLength, String floorMaterial, int carportHeight, int carportLength, int carportWidth, String roofMaterial, String roofShape, int roofAngle, String name, int zipCode, int phoneNumber, String email, int idUser, int idBom) {
        try {
            insertShedChoices(shedWidth, shedLength, floorMaterial);
            insertCarportChoicesShed(carportHeight, carportLength, carportWidth, roofMaterial, roofShape, roofAngle);
            insertCustomer(name, zipCode, phoneNumber, email);
            insertRequest(idUser, idBom);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public void insertFullRequest(int carportHeight, int carportLength, int carportWidth, String roofMaterial, String roofShape, int roofAngle, String name, int zipCode, int phoneNumber, String email, int idUser, int idBom) {
        try {
            insertCarportChoices(carportHeight, carportLength, carportWidth, roofMaterial, roofShape, roofAngle);
            insertCustomer(name, zipCode, phoneNumber, email);
            insertRequest(idUser, idBom);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public Request getRequestFromDB(int idUser) {
        Request request = null;
        try (Connection connection = connectionPool.getConnection()) {

            String sql = "SELECT idorder, idcustomer, idbom, isAccepted, isPaid, finalPrice, iduser, idcarportchoices FROM fogarchive.order WHERE iduser = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, idUser);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int idOrder = rs.getInt("idorder");
                    int idCustomer = rs.getInt("idcustomer");
                    int idBom = rs.getInt("idbom");
                    boolean isAccepted = rs.getBoolean("isAccepted");
                    boolean isPaid = rs.getBoolean("isPaid");
                    int finalPrice = rs.getInt("finalPrice");
                    int idCarportChoices = rs.getInt("idcarportchoices");
                    request = new Request(idOrder, idCustomer, idBom, isAccepted, isPaid, finalPrice, idUser, idCarportChoices);
                }
            } catch (Exception E) {
                System.out.println(E);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return request;
    }

    public Carport getCarportChoices(int carportChoicesId) throws DatabaseException {
        Carport carport = null;

        String sql = "SELECT * FROM fogarchive.`carport choices` where `idcarport choices`=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carportChoicesId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int height = rs.getInt("height");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    String roofMaterial = rs.getString("roofmateriel");
                    String roofShape = rs.getString("roofshape");
                    int roofAngle = rs.getInt("roofangle");
                    int idShed = rs.getInt("idshed");
                    carport = new Carport(height, length, width, roofMaterial, roofShape, roofAngle, idShed);
                }
                else {
                    throw new DatabaseException("Request has no idcarportchoices");
                }
            }

        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error getting carport choices. Something went wrong with the database");
        }
        return carport;
    }
    public Shed getShedChoices(int shedId) throws DatabaseException {
        Shed shed = null;

        String sql = "SELECT * FROM fogarchive.`shed choices` where `idshed choices`=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, shedId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    String floorMaterial = rs.getString("floormateriel");
                    shed = new Shed(width, length, floorMaterial);
                }
                else {
                    throw new DatabaseException("Request has no idshedchoices");
                }
            }

        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error getting shed choices. Something went wrong with the database");
        }
        return shed;
    }


}
