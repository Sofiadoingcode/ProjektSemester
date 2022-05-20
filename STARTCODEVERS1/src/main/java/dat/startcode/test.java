package dat.startcode;

import dat.startcode.control.GenerateBOM;
import dat.startcode.model.DTOs.ProductDTO;
import dat.startcode.model.entities.CarportChoices;
import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.persistence.ProductMapper;
import dat.startcode.model.services.BOMAlgorithm;
import dat.startcode.model.services.ProductionFacade;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) throws DatabaseException {
       ConnectionPool connectionPool = new ConnectionPool();

       BOMAlgorithm bomAlgorithm = new BOMAlgorithm(connectionPool);

        CarportChoices c = new CarportChoices(4, 6, 8);

       List<ProductLine> fullbom = bomAlgorithm.generateBOM(c);

       for(ProductLine p: fullbom) {
           System.out.println(p.getProductID());
           System.out.println(p.getAmount());
           System.out.println(p.getLengthID());
           System.out.println(p.getTotalproductprice());
           System.out.println("  ");


       }



    }

}
