package dat.startcode.model.persistence;

import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int productID, pricePerMeasurement, amount, height, width;
                    String name, type, category;

                    productID = rs.getInt("idProduct");
                    name = rs.getString("name");
                    height = rs.getInt("height");
                    width = rs.getInt("width");
                    amount = rs.getInt("amount");
                    category = rs.getString("category");
                    pricePerMeasurement = rs.getInt("priceprmeasurment");
                    type = rs.getString("type");


                    Product product = new Product(productID, name, category, type, pricePerMeasurement);

                    if (!(amount == 0)) {
                        product.setAmount(amount);
                    }
                    product.setHeight(height);
                    product.setWidth(width);
                    products.add(product);

                }


            }
        } catch (SQLException ex) {

            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");

        }


        return products;
    }


    public void createProduct(String name, String category, String unit, int amount, int height, int width, int price) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        int categoryID, unitID, nameID;
        String sql1 = "SELECT * FROM  fogarchive.category where category=?";
        String sql2 = "SELECT * FROM fogarchive.unit where type=?";
        String sql3 = "SELECT * FROM fogarchive.productname where name=?";
        String sql4 = "INSERT INTO `fogarchive`.`productname` (`name`) VALUES (?)";

        String sql5 = "insert into product (`idname`, `idunit`, `idcategory`, `priceprmeasurment`, `height`, `width`, `amount`) values (?,?,?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                ps.setString(1, category);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    categoryID = rs.getInt("idcategory");
                    System.out.println(categoryID);
                } else {
                    throw new DatabaseException("Something went wrong looking for category");
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                ps.setString(1, unit);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    unitID = rs.getInt("idunit");
                    System.out.println(unitID);
                } else {
                    throw new DatabaseException("Something went wrong looking for unit");
                }
            }
            System.out.println("check1");
            try (PreparedStatement ps = connection.prepareStatement(sql3)) {
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nameID = rs.getInt("idproductnames");
                    System.out.println(nameID);
                } else {

                    try (PreparedStatement ps1 = connection.prepareStatement(sql4, Statement.RETURN_GENERATED_KEYS)) {

                        ps1.setString(1, name);
                        int rowsAffected = ps1.executeUpdate();

                        ResultSet generatedKeys = ps.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            nameID = generatedKeys.getInt(1);
                        } else {
                            throw new DatabaseException("couldnt find or add name");
                        }

                    }
                }
            }
            System.out.println("check2");

            System.out.println("check3");
            try (PreparedStatement ps = connection.prepareStatement(sql5)) {

                ps.setInt(1, nameID);
                ps.setInt(2, unitID);
                ps.setInt(3, categoryID);
                ps.setInt(4, price);
                ps.setInt(5, height);
                ps.setInt(6, width);
                ps.setInt(7, amount);
                ps.executeUpdate();

            }


        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }

    }

}

