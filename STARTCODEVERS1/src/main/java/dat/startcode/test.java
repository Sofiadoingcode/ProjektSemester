package dat.startcode;

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
        try {
           products  = productMapper.getAllProducts();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        for(Product p: products)
            System.out.println(p);

    }

}
