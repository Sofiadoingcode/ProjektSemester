package dat.startcode.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;
import dat.startcode.model.services.RequestFacade;
import dat.startcode.model.services.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class RequestMapperTest
{
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/startcode_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool();
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement() ) {
                // Remove all rows from all tables
                stmt.execute("delete from user");
                // Indsæt et par brugere
                stmt.execute("insert into user (username, password, idrole) " +
                        "values ('fog','123',1),('gues','password',2), ('ben','1234',2)");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }

    @Test
    void requestFacede() throws DatabaseException
    {
        int carportHeight = 6;
        int carportLength = 8;
        int carportWidth = 6;
        String roofMaterial = "Plasttrapez";
        String roofShape = "Flat tag";
        int roofAngle = 0;
        String name = "Shishiro";
        int zipcode = 3000;
        int phoneNumber = 22334455;
        String email = "shishiron@lion.com";
        int idUser =1;
        int bom = 1;


        RequestFacade.insertFullRequest(carportHeight,carportLength,carportWidth,roofMaterial,roofShape,roofAngle,name,zipcode,phoneNumber,email,idUser,bom ,connectionPool);


        User expectedUser = new User("fog","123",1);
        User actualUser = UserFacade.login("fog","123", connectionPool);
        System.out.println(actualUser);


        assertEquals(expectedUser, actualUser);



    }

}