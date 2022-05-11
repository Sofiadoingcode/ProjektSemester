package dat.startcode;

import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.persistence.ProductMapper;
import dat.startcode.model.services.ProductionFacade;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
       ConnectionPool connectionPool = new ConnectionPool();
       ProductMapper productMapper = new ProductMapper(connectionPool);


       try {
           ProductionFacade.createProduct("meganut","Beslag & Skruer","stk",3,100,100,5,connectionPool);
       }catch (Exception e){

           System.out.println(e);

       }


        /*
        try {
            productMapper.createProduct("deeznuts", "Beslag & Skruer", "stk", 2, 5100, 5100, 69);
        }catch (Exception e){

            System.out.println("lol");
        }

*/

    }

}
