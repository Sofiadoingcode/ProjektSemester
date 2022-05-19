package dat.startcode.persistence;

import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;
import dat.startcode.model.persistence.UserMapper;
import dat.startcode.model.services.ProductionFacade;
import dat.startcode.model.services.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductionMapperTest {
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/startcode_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;
    static private ProductMapper productMapper;
    private static Product product;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool();
        productMapper = new ProductMapper(connectionPool);
        product = new Product(0, "testBolt", "Beslag & Skruer", "stk", 100);
        product.setProductType("skrue");
        product.setWidth(10);
        product.setHeight(5);
        product.setAmount(1);


    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void productFacade() throws DatabaseException{

        ProductionFacade.createProduct(product.getName(),product.getCategory(),product.getUnit(),product.getAmount(),product.getHeight(),product.getWidth(),product.getPrice(),product.getProductType(),connectionPool);

        List<Product> testProductList = productMapper.getAllProducts();

        assert (testProductList.contains(product));
    }


    @Test
    void getAllProducts() throws DatabaseException {

        List<Product> products = productMapper.getAllProducts();
        assertDoesNotThrow(() -> products.get(0));
        assert (products.size() >= 21);

    }

    @Test
    void getType() throws DatabaseException {
        //check if getProductType id returns id = 2 when product type is "skrue"

        System.out.println(product.getProductType());
        int i = productMapper.getProductTypeID(product.getProductType());
        assert (i == 2);

    }


    @Test
    void delete() throws DatabaseException {

        List<Product> array = productMapper.getAllProducts();
        int size1 = array.size();
        int id =0;
        for (Product tempProduct: array) {

            if (tempProduct.equals(this.product))
            id = tempProduct.getProductID();
        }

        productMapper.deleteProduct(id);

        array = productMapper.getAllProducts();
        int size2 = array.size();

        assert (size1 == size2 +1);

    }


}