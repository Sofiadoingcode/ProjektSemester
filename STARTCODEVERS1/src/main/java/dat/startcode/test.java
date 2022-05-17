package dat.startcode;

import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.persistence.ProductMapper;
import dat.startcode.model.services.BOMAlgorithm;
import dat.startcode.model.services.ProductionFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class test {
    //If you have found this you have found my easter egg, its my test file so that I don't have to use b* ass junit

    public static void main(String[] args) {
       ConnectionPool connectionPool = new ConnectionPool();
        ProductMapper productMapper = new ProductMapper(connectionPool);
        try {

            BOMAlgorithm bomAlgorithm = new BOMAlgorithm(connectionPool);
            System.out.println(bomAlgorithm.getLengthsNeeded(1000)[0]);


            //HashMap<Integer,Integer> list = productMapper.getLengths();


        }catch (DatabaseException e)
        {

        }
    }

}
