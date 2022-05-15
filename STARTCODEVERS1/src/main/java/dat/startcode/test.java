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

        try {

            ProductionFacade.createProduct("hej","Træ & Tagplader", "sæt", 10,10,10,10,"rem",connectionPool);


        }catch (Exception e){

            System.out.println("something went wrong");

        }




    }

}
