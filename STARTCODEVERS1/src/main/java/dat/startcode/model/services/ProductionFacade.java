package dat.startcode.model.services;

import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;

import java.util.List;

public class ProductionFacade {


    public static void createProduct(String name, String category, String unit, int amount, int height, int width,
                                     int price, String productType, ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);

        int categoryID = productMapper.getCategoryID(category);
        int unitID = productMapper.getUnitTypeID(unit);
        int nameID = productMapper.getNameID(name);
        int productTypeId = productMapper.getProductTypeID(productType);
        productMapper.createProduct(nameID, categoryID, unitID, amount, height, width, price, productTypeId);


    }

    public static List<Product> getProducts(ConnectionPool connectionPool)throws DatabaseException{

        ProductMapper productMapper = new ProductMapper(connectionPool);
        return productMapper.getAllProducts();

    }




}