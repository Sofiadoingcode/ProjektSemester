package dat.startcode.persistence;

import dat.startcode.model.entities.*;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.*;
import dat.startcode.model.services.BOMAlgorithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestAndBOMMapperTest {

    private static ConnectionPool connectionPool;
    private static UserMapper userMapper;
    private static BOMMapper bomMapper;
    private static RequestMapper  requestMapper;
    private static CustomerMapper customerMapper;


    private static BOMAlgorithm bomAlgorithm;

    private static User user;
    private static CarportChoices carportChoices;
    private static Request request;
    private static Customer customer;
    private  static int BOMId;

    List<ProductLine> BOM;

    @BeforeAll
    public static void setUpClass() throws Exception {
        connectionPool = ConnectionPool.connectionPool();

        carportChoices = new CarportChoices(3.3, 3.5,5);
        user = new User("Charlie", "password",1 );


        userMapper = new UserMapper(connectionPool);
        requestMapper = new RequestMapper(connectionPool);
        bomMapper = new BOMMapper(connectionPool);
        customerMapper = new CustomerMapper(connectionPool);
        bomAlgorithm = new BOMAlgorithm(connectionPool);



    }

    @BeforeEach
    void setUp(){

        BOM = bomAlgorithm.generateBOM(carportChoices);
    }


    @Test
    void testBOMmapper() throws SQLException,DatabaseException{

       String svg = bomAlgorithm.getSvg().toString();
       String description = bomAlgorithm.getDescription();
       double price = bomAlgorithm.getTotalBOMPrice();

       BOMId = bomMapper.createBOMInDB(description,price,svg);
       assertNotEquals(0,BOMId);
       System.out.println(BOMId);


       assertDoesNotThrow(()->bomMapper.saveFullBom(BOMId,BOM));


    }

    @Test
    void requestMapper() throws DatabaseException{

        int nonAcceptedRequestsSize = customerMapper.getAllNonAcceptedRequests().size();
        int acceptedRequestsSize = customerMapper.getAllAcceptedRequests().size();

        user = userMapper.createTempUser("c@hotmail.com");

       assertDoesNotThrow(()-> requestMapper.insertFullRequest(carportChoices.getHeight(),carportChoices.getLength(), carportChoices.getWidth(),
                "Plasttrapez","Flat tag",0,"Charlie",2800,11223344,user.getUsername(),user.getIdUser(),BOMId,bomAlgorithm.getTotalBOMPrice()));


       int expectedNonAcceptedRequestsSize2 = customerMapper.getAllNonAcceptedRequests().size()-1;
       int expectedAcceptedRequestsSize2 = customerMapper.getAllAcceptedRequests().size();

        assertEquals(nonAcceptedRequestsSize, expectedNonAcceptedRequestsSize2);
        assertEquals(acceptedRequestsSize,expectedAcceptedRequestsSize2);

    }

    @Test
    void setRequestMapper() throws DatabaseException{
        customerMapper.unAcceptRequest(21);

        int nonAcceptedRequestsSize = customerMapper.getAllNonAcceptedRequests().size();
        int acceptedRequestsSize = customerMapper.getAllAcceptedRequests().size();

        customerMapper.acceptRequest(21);

        int nonAcceptedRequestsSize2 = customerMapper.getAllNonAcceptedRequests().size()+1;
        int acceptedRequestsSize2 = customerMapper.getAllAcceptedRequests().size()-1;

        assertEquals(nonAcceptedRequestsSize,nonAcceptedRequestsSize2);
        assertEquals(acceptedRequestsSize,acceptedRequestsSize2);

        customerMapper.unAcceptRequest(21);

        nonAcceptedRequestsSize2 = customerMapper.getAllNonAcceptedRequests().size();
        acceptedRequestsSize2 = customerMapper.getAllAcceptedRequests().size();
        assertEquals(nonAcceptedRequestsSize,nonAcceptedRequestsSize2 );
        assertEquals(acceptedRequestsSize,acceptedRequestsSize2 );

    }

    @Test
    void deleteRequest() throws DatabaseException{


        int expectedSize = customerMapper.getAllNonAcceptedRequests().size() + customerMapper.getAllAcceptedRequests().size() + customerMapper.getAllPaidRequests().size() -1;


        assert(customerMapper.deleteOrder(21));

        int newSize = customerMapper.getAllNonAcceptedRequests().size() +customerMapper.getAllAcceptedRequests().size() + customerMapper.getAllPaidRequests().size();
        assertEquals(newSize,expectedSize);

    }


}