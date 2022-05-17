package dat.startcode;

import dat.startcode.control.GenerateBOM;
import dat.startcode.model.DTOs.ProductDTO;
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

            List<ProductDTO> allproducts = bomAlgorithm.loadAllProducts();

                List<ProductLine> yeet = bomAlgorithm.calculateHulbåndProductLines(allproducts, 400,300, 500);

            for (ProductLine productLine: yeet) {
                System.out.println("ProductID: " + productLine.getProductID());
                System.out.println("LengthID: " + productLine.getLengthID());
                System.out.println("ProductPrice: " + productLine.getTotalproductprice());
                System.out.println("Amount: " + productLine.getAmount());
            }



    }

}
