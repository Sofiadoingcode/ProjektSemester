package dat.startcode.model.persistence;

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
    private int idCarport=0;

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

                //return the generated primary key from sql
                ResultSet generatedKeys = ps1.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idShed = generatedKeys.getInt(1);
                }

            }
        }catch (SQLException e) {
                System.out.println(e);
            }
        }

    public void insertCarportChoices(int carportHeight, int carportLength, int carportWidth, String roofMaterial, String roofShape, int roofAngle) throws DatabaseException {


        Logger.getLogger("web").log(Level.INFO, "");


        try (Connection connection = connectionPool.getConnection()) {

            String sql = "INSERT INTO `fogarchive`.`carport choices` () VALUES (?, ?, ?, ?, ?, ?, ?) ";
            try (PreparedStatement ps1 = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setInt(1, carportHeight);
                ps1.setInt(2, carportLength);
                ps1.setInt(3, carportWidth);
                ps1.setString(4,roofMaterial);
                ps1.setString(5,roofShape);
                ps1.setInt(6, roofAngle);
                ps1.setInt(7, idShed);

                //return the generated primary key from sql
                ResultSet generatedKeys = ps1.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idCarport = generatedKeys.getInt(1);
                }

            }
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
    }


