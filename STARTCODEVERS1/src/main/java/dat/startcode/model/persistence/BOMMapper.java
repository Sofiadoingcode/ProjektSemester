package dat.startcode.model.persistence;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductDTO;
import dat.startcode.model.DTOs.ProductionlineDTO;
import dat.startcode.model.entities.CarportChoices;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BOMMapper implements IBOMMapper{
    ConnectionPool connectionPool;

    public BOMMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    @Override
    public BOMDTO getBOM(int orderID) throws DatabaseException {


        BOMDTO bomdto = new BOMDTO(0, 0, "", 0);

        String sql = "SELECT idbom, totalprice, `description`, o.idorder\n" +
                "FROM BOM\n" +
                "INNER JOIN `order` o USING (idbom)\n" +
                "WHERE idorder = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, orderID);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    double totalprice = rs.getDouble("totalprice");

                    int bomid = rs.getInt("idbom");

                    String description = rs.getString("description");

                    int orderid = rs.getInt("idorder");

                    bomdto = new BOMDTO(bomid, totalprice, description, orderid);

                }

            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }


        return bomdto;
    }



    @Override
    public List<ProductionlineDTO> getBOMProductlines(BOMDTO bomdto) throws DatabaseException {
        System.out.println("AAAA");
        List<ProductionlineDTO> productionLines = new ArrayList<>();

        int bomid = bomdto.getIdbom();


        String sql = "SELECT idproductionline, idproduct, amount, idbom, l.length, totalproductprice\n" +
                "FROM productionline\n" +
                "INNER JOIN length l USING (idlength)\n" +
                "WHERE idbom = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {


                ps.setInt(1, bomid);
                ResultSet rs = ps.executeQuery();


                while (rs.next()) {

                    int productionLineID = rs.getInt("idproductionline");


                    // FEJL SKER HER
                    int idproduct = rs.getInt("idproduct");

                    ProductDTO productDTO = getFullProduct(idproduct);



                    String name = getFullProductionLineName(productDTO);


                    int amount = rs.getInt("amount");


                    int length = rs.getInt("length");


                    int category = productDTO.getIdcategory();


                    String unit = productDTO.getUnit();



                    double totalproductprice = rs.getDouble("totalproductprice");



                    ProductionlineDTO productLine = new ProductionlineDTO(productionLineID, name, amount, length, category, unit, totalproductprice);
                    productionLines.add(productLine);

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }



        return productionLines;
    }


    public int createBOMinDB (String description, double totalprice) throws DatabaseException {
        int bomId = 0;

        String sql = "INSERT INTO `fogarchive`.`bom` (`totalprice`,`description`) VALUES (?,?)";

        System.out.println("cb1");

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                System.out.println("cb1");
                ps.setDouble(1, totalprice);
                System.out.println("cb2");
                ps.setString(2, description);
                System.out.println("cb3");
                ps.executeUpdate();
                System.out.println("cb4");
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    bomId = generatedKeys.getInt(1);
                }
                System.out.println("cb5");

            }
        } catch (SQLException ex) {
            System.out.println("ERROROOROR");
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }

        return bomId;
    }

    @Override
    public void saveFullBom(int bomId, List<ProductLine> fullBom) throws DatabaseException {

        System.out.println("SF1");

        String sql = "insert into `fogarchive`.productionline (idproduct, amount, idbom, idlength, totalproductprice) VALUES (?, ?, ?, ?, ?)";


        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                System.out.println("SF2");

                for(ProductLine p: fullBom) {
                    ps.setInt(1,p.getProductID());
                    ps.setInt(2,p.getAmount());
                    ps.setInt(3, bomId);

                    if(p.getLengthID() == null) {
                        ps.setNull(4, Types.INTEGER);
                    } else {
                        ps.setInt(4, p.getLengthID());
                    }

                    ps.setDouble(5, p.getTotalproductprice());
                    System.out.println("SF3");
                    ps.executeUpdate();

                }

            }
        } catch (SQLException ex) {
            System.out.println("ERRORORORO");
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }
    }






    @Override
    public List<ProductDTO> getAllProductDTOs() throws DatabaseException {
        List<ProductDTO> allProductDTOs = new ArrayList<>();

        String sql = "SELECT *\n" +
                "FROM product";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int productid = rs.getInt("idProduct");

                    ProductDTO productDTO = getFullProduct(productid);

                    allProductDTOs.add(productDTO);

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }


        return allProductDTOs;

    }


    private ProductDTO getFullProduct(int productID) throws DatabaseException {


        ProductDTO productDTO = new ProductDTO(0,"","", 0,0,0,0,0, "");

        String sql = "SELECT idproduct, n.`name`, u.`type`, idcategory, priceprmeasurment, height, width, amount, pt.producttype\n" +
                "FROM product\n" +
                "INNER JOIN productname n USING (idname)\n" +
                "INNER JOIN unit u USING (idunit)\n" +
                "INNER JOIN producttype pt USING (idproducttype)\n" +
                "WHERE idproduct = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, productID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int idproduct = rs.getInt("idproduct");
                    String name = rs.getString("name");
                    String unit = rs.getString("type");
                    int category = rs.getInt("idcategory");
                    double priceprmeasurment = rs.getDouble("priceprmeasurment");
                    double height = rs.getDouble("height");
                    double width = rs.getDouble("width");
                    int amount = rs.getInt("amount");
                    String producttype = rs.getString("producttype");

                    productDTO = new ProductDTO(idproduct, name, unit, category, priceprmeasurment, height, width, amount, producttype);


                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }

        return productDTO;

    }


    private String getFullProductionLineName(ProductDTO productDTO) {
        String fullName = "";

        String name = productDTO.getName();
        double height = productDTO.getHeight();
        double width = productDTO.getWidth();
        int amount = productDTO.getAmount();

        if(height != 0)   {
            fullName += height + "x" + width;

        }

        fullName += " " + name;

        if(amount != 0) {
            fullName += " " + amount + " stk";

        }


        return fullName;
    }


}
