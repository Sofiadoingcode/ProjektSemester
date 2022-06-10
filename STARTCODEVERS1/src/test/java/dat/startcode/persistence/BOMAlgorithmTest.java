package dat.startcode.persistence;

import dat.startcode.model.entities.CarportChoices;
import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.*;
import dat.startcode.model.services.BOMAlgorithm;
import dat.startcode.model.services.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BOMAlgorithmTest {


    private static ConnectionPool connectionPool;
    private static CarportChoices carportChoices;
    static ProductMapper productMapper;
    static List<String> neededElements = new ArrayList<>();
    static BOMAlgorithm bomAlgorithm;
    static List<Product> productList;
    static HashMap<Integer, String> ProductTypes;

    @BeforeAll
    public static void setUpClass() throws SQLException, DatabaseException {
        connectionPool = ConnectionPool.connectionPool();

        carportChoices = new CarportChoices(3.2, 6, 7.1);

        //List of materials needed to make a carport without a shep and flat roof
        neededElements.add("rem");
        neededElements.add("spær");
        neededElements.add("stern");
        neededElements.add("vandbræt");
        neededElements.add("hulbånd");
        neededElements.add("tag");
        neededElements.add("stolpe");
        neededElements.add("beslag");

        bomAlgorithm = new BOMAlgorithm(connectionPool);
        productMapper = new ProductMapper(connectionPool);


        productList = productMapper.getAllProducts();
        productMapper = new ProductMapper(connectionPool);

        carportChoices = new CarportChoices(3.2, 4, 5.1);
    }

    @Test
    void testBOM() throws SQLException {
        List<ProductLine> bom = bomAlgorithm.generateBOM(carportChoices);
        assertDoesNotThrow(() -> bomAlgorithm.generateBOM(carportChoices));


        for (ProductLine productLine:
             bom) {
            System.out.println(productLine);
        }
        //intention of this test is to loop through the whole list items needed and see if the bom algoritm is able to . YOu can see neededElements as a checklist almost
        for (String productNeeded : neededElements) {

            boolean check = false;


            for (ProductLine productLine : bom) {
                for (Product product : productList) {
                    if (product.getProductId() == productLine.getProductId() && product.getProductType().equals(productNeeded)) {
                        check = true;
                        break;

                    }

                }
                if (check)
                    break;
            }

            System.out.println(productNeeded + " is " + check);

        }


    }
    @Test
    void testBOMalgo() throws SQLException {

        List<ProductLine> bom = bomAlgorithm.generateBOM(carportChoices);
        assertDoesNotThrow(() -> bomAlgorithm.generateBOM(carportChoices));

        for (ProductLine productLine: bom)
            System.out.println(productLine);
    }

}
