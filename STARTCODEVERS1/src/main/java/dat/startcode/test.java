package dat.startcode;

import dat.startcode.model.entities.CarportChoices;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.BOMAlgorithm;

import java.util.List;

public class test {

    public static void main(String[] args) throws DatabaseException {
       ConnectionPool connectionPool = new ConnectionPool();

       BOMAlgorithm bomAlgorithm = new BOMAlgorithm(connectionPool);

        CarportChoices c = new CarportChoices(4, 6, 8);

       List<ProductLine> fullbom = bomAlgorithm.generateBOM(c);

       for(ProductLine p: fullbom) {
           System.out.println(p.getProductId());
           System.out.println(p.getAmount());
           System.out.println(p.getLengthId());
           System.out.println(p.getTotalProductPrice());
           System.out.println("  ");


       }



    }

}
