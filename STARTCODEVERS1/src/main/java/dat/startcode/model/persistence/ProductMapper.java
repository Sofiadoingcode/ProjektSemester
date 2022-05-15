package dat.startcode.model.persistence;

import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    public HashMap<Integer, Integer> getLengths() throws DatabaseException {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM fogarchive.length";
        HashMap<Integer, Integer> lengths = new HashMap<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {


                    int id = rs.getInt("idlength");
                    int length = rs.getInt("length");
                    lengths.put(id,length);


                }


            }
        } catch (SQLException ex) {

            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");

        }


        return lengths;
    }

    public int getCategoryID(String category) throws DatabaseException {

        int categoryID = 0;
        try (Connection connection = connectionPool.getConnection()) {
            String sql1 = "SELECT * FROM  fogarchive.category where category=?";
            try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                ps.setString(1, category);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    categoryID = rs.getInt("idcategory");
                } else {
                    throw new DatabaseException("Something went wrong looking for category");
                }
            }
        } catch (SQLException E) {
            throw new DatabaseException(E, "Couldnt find category");
        }

        return categoryID;
    }

    public int getUnitTypeID(String unit) throws DatabaseException {
        int unitID = 0;

        try (Connection connection = connectionPool.getConnection()) {
            String sql2 = "SELECT * FROM fogarchive.unit where type=?";
            try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                ps.setString(1, unit);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    unitID = rs.getInt("idunit");
                } else {
                    throw new DatabaseException("Something went wrong looking for unit");
                }
            }
        } catch (SQLException E) {
            throw new DatabaseException(E, "Couldnt find category");
        }

        return unitID;
    }


    public int getNameID(String name) throws DatabaseException {
        int nameID = 0;

        try (Connection connection = connectionPool.getConnection()) {
            String sql3 = "SELECT * FROM fogarchive.productname where name=?";
            try (PreparedStatement ps = connection.prepareStatement(sql3)) {
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nameID = rs.getInt("idproductnames");
                    System.out.println(nameID);
                }
                if (nameID == 0) {
                    String sql4 = "INSERT INTO `fogarchive`.`productname` (`name`) VALUES (?)";
                    try (PreparedStatement ps1 = connection.prepareStatement(sql4, Statement.RETURN_GENERATED_KEYS)) {

                        ps1.setString(1, name);
                        int rowsAffected = ps1.executeUpdate();

                        ResultSet generatedKeys = ps1.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            nameID = generatedKeys.getInt(1);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return nameID;
    }

    public void createProduct(int name, int category, int unit, int amount, int height, int width,
                               int price) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");


        try (Connection connection = connectionPool.getConnection()) {

            String sql5 = "insert into product (`idname`, `idunit`, `idcategory`, `priceprmeasurment`, `height`, `width`, `amount`) values (?,?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql5)) {

                ps.setInt(1, name);
                ps.setInt(2, unit);
                ps.setInt(3, category);
                ps.setInt(4, price);
                ps.setInt(5, height);
                ps.setInt(6, width);
                ps.setInt(7, amount);
                ps.executeUpdate();

            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Trouble inserting product");
        }

    }


    public void createProduct(String name, String category, String unit, int amount, int height, int width,
                              int price) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        int categoryID, unitID, nameID;


        try (Connection connection = connectionPool.getConnection()) {
            String sql1 = "SELECT * FROM  fogarchive.category where category=?";
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

            String sql2 = "SELECT * FROM fogarchive.unit where type=?";
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

            String sql3 = "SELECT * FROM fogarchive.productname where name=?";
            try (PreparedStatement ps = connection.prepareStatement(sql3)) {
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nameID = rs.getInt("idproductnames");
                    System.out.println(nameID);
                } else {

                    String sql4 = "INSERT INTO `fogarchive`.`productname` (`name`) VALUES (?)";
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


                String sql5 = "insert into product (`idname`, `idunit`, `idcategory`, `priceprmeasurment`, `height`, `width`, `amount`) values (?,?,?,?,?,?,?)";
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


    public void modifyProduct(int id, int idname, int idunit, int idcategory, int price, int height, int width, int amount) throws DatabaseException{

        String sql= "UPDATE `fogarchive`.`product` SET `idname` = ?, `idunit` = ?, `idcategory` = ?, `priceprmeasurment` = ?, `height` = ?, `width` = ?, `amount` = ? WHERE (`idProduct` = ?)";
        try (Connection connection = connectionPool.getConnection()) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idname);
            ps.setInt(2, idunit);
            ps.setInt(3, idcategory);
            ps.setInt(4, price);
            ps.setInt(5, height);
            ps.setInt(6, width);
            ps.setInt(7, amount);
            ps.setInt(8,id);
            ps.executeUpdate();

        }}catch (SQLException EX){
            throw new DatabaseException(EX, "didnt work");
        }








    }

}

