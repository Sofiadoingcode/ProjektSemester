package dat.startcode.persistence;

import dat.startcode.model.entities.BOM;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.IBOMMapper;
import dat.startcode.model.persistence.UserMapper;
import dat.startcode.model.services.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class BOMMapperTest
{
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/startcode_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;
    private static BOMMapper bomMapper;
    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool();
        bomMapper = new BOMMapper(connectionPool);
    }

    @BeforeEach
    void setUp()
    {

    }

    @Test
    void testBOM() throws SQLException
    {
        String description = " ";
        Double price = 100.3;

        //assertDoesNotThrow(()->bomMapper.createBOMinDB(description,price));


    }


}