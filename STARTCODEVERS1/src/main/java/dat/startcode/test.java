package dat.startcode;

import dat.startcode.model.entities.BOM;
import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.persistence.ProductMapper;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        ProductMapper productMapper = new ProductMapper(new ConnectionPool());
        ConnectionPool connectionPool = new ConnectionPool();
        /*
        try {
           products  = productMapper.getAllProducts();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        for(Product p: products)
            System.out.println(p);
*/
        try {
            BOM bom = new BOM(3, 6, 7.8, true, 2.1, 5.3, 0, connectionPool);
            bom.generateFullBom();
            bom.printBom();
        }catch (DatabaseException e){
            e.printStackTrace();

        }   

    }

}
