package dat.startcode.model.persistence;

import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    ConnectionPool connectionPool;

    public ProductMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Product> getAllProducts() throws DatabaseException {
        List<Product> products = new ArrayList<>();

        String sql = "\tSELECT * from(((product \n" +
                "    inner join productname on product.idname = productname.idproductnames)\n" +
                "    inner join unit on product.idunit = unit.idunit)\n" +
                "    inner join category on product.idcategory = category.idcategory)";

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql) ) {
                ResultSet rs = ps.executeQuery();

                while ( rs.next() ){
                    int productID, pricePerMeasurement, amount, height, width;
                    String name, type, category;

                    productID = rs.getInt("idProduct");
                    name = rs.getString("name");
                    height = rs.getInt( "height");
                    width = rs.getInt("width");
                    amount = rs.getInt("amount");
                    category = rs.getString("category");
                    pricePerMeasurement = rs.getInt("priceprmeasurment");
                    type = rs.getString("type");


                    Product product = new Product(productID, name, category, type, pricePerMeasurement);

                    if (! (amount ==0)) {
                        product.setAmount(amount);
                    }
                    product.setHeight(height);
                    product.setWidth(width);
                    products.add(product);

                }


            }
        }catch (SQLException ex){

            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");

        }



        return products;
    }


}
