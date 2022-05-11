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
            System.out.println(productMapper.getLengths());

        }catch (Exception e){

            
        }




    }

}
