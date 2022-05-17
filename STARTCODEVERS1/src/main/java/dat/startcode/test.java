package dat.startcode;

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

        BOMAlgorithm bom = new BOMAlgorithm(connectionPool);

        List<ProductDTO> allproducts = bom.loadAllProducts();

        List<ProductLine> productLines = bom.calculateTagProductLines(allproducts, 400, 450, 900);

        for(ProductLine p: productLines) {
            System.out.println(p.getProductID());
            System.out.println(p.getAmount());
            System.out.println(p.getLengthID());
            System.out.println(p.getTotalproductprice());
            System.out.println("  ");

        }


    }

}
