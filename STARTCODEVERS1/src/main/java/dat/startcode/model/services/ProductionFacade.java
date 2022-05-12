package dat.startcode.model.services;

import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;

public class ProductionFacade {


    public static void createProduct(String name, String category, String unit, int amount, int height, int width,
                                     int price, ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);

        int categoryID = productMapper.getCategoryID(category);
        int unitID = productMapper.getUnitTypeID(unit);
        int nameID = productMapper.getNameID(name);

        productMapper.createProduct(nameID,categoryID,unitID,amount,height,width,price);

    }

}
