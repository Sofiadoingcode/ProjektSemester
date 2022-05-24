package dat.startcode.model.persistence;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductDTO;
import dat.startcode.model.DTOs.ProductionLineDTO;
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
    public BOMDTO getBOM(int orderId) throws DatabaseException {

        BOMDTO bomdto = new BOMDTO(0, 0, "", 0, "");

        String sql = "SELECT idbom, totalprice, `description`, svgDrawing, o.idorder FROM bom INNER JOIN `order` o USING (idbom) WHERE idorder = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    double totalPrice = rs.getDouble("totalprice");

                    int bomId = rs.getInt("idbom");

                    String description = rs.getString("description");

                    String svgDrawing = rs.getString("svgDrawing");

                    int idOrder = rs.getInt("idorder");

                    bomdto = new BOMDTO(bomId, totalPrice, description, idOrder, svgDrawing);

                }

            }
        } catch (SQLException ex) {
                    System.out.println(" ");
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }


        return bomdto;
    }



    @Override
    public List<ProductionLineDTO> getBOMProductLines(BOMDTO bomdto) throws DatabaseException {


        List<ProductionLineDTO> productionLines = new ArrayList<>();

        int bomId = bomdto.getIdBOM();


        String sql = "SELECT idproductionline, idproduct, amount, idbom, l.length, totalproductprice\n" +
                "                FROM productionline\n" +
                "                left join length l on productionline.idlength=l.idlength\n" +
                "                WHERE idbom = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {


                ps.setInt(1, bomId);
                ResultSet rs = ps.executeQuery();


                while (rs.next()) {

                    int productionLineID = rs.getInt("idproductionline");


                    int idProduct = rs.getInt("idproduct");

                    ProductDTO productDTO = getFullProduct(idProduct);



                    String name = getFullProductionLineName(productDTO);


                    int amount = rs.getInt("amount");

                    int length;

                    if(rs.getObject("length") == null) {
                        length = 0;

                    } else {
                        length = rs.getInt("length");
                    }


                    int category = productDTO.getIdCategory();


                    String unit = productDTO.getUnit();



                    double totalProductPrice = rs.getDouble("totalproductprice");



                    ProductionLineDTO productLine = new ProductionLineDTO(productionLineID, name, amount, length, category, unit, totalProductPrice);
                    productionLines.add(productLine);

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }



        return productionLines;
    }


    public int createBOMInDB(String description, double totalPrice, String svg) throws DatabaseException {
        int bomId = 0;

        String sql = "INSERT INTO `fogarchive`.`bom` (`totalprice`,`description`, `svgDrawing`) VALUES (?,?,?)";



        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setDouble(1, totalPrice);

                ps.setString(2, description);

                ps.setString(3, svg);

                ps.executeUpdate();

                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    bomId = generatedKeys.getInt(1);
                }

            }
        } catch (SQLException ex) {

            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }

        return bomId;
    }

    @Override
    public void saveFullBom(int orderId, List<ProductLine> fullBom) throws DatabaseException {



        String sql = "insert into `fogarchive`.productionline (idproduct, amount, idbom, idlength, totalproductprice) VALUES (?, ?, ?, ?, ?)";


        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {



                for(ProductLine p: fullBom) {
                    ps.setInt(1,p.getProductId());
                    ps.setInt(2,p.getAmount());
                    ps.setInt(3, orderId);

                    if(p.getLengthId() == null) {
                        ps.setNull(4, Types.INTEGER);
                    } else {
                        ps.setInt(4, p.getLengthId());
                    }

                    ps.setDouble(5, p.getTotalProductPrice());

                    ps.executeUpdate();

                }

            }
        } catch (SQLException ex) {

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
                    int productId = rs.getInt("idProduct");

                    ProductDTO productDTO = getFullProduct(productId);

                    allProductDTOs.add(productDTO);

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }


        return allProductDTOs;

    }


    private ProductDTO getFullProduct(int productId) throws DatabaseException {


        ProductDTO productDTO = new ProductDTO(0,"","", 0,0,0,0,0, "");

        String sql = "SELECT idproduct, n.`name`, u.`type`, idcategory, priceprmeasurment, height, width, amount, pt.producttype\n" +
                "FROM product\n" +
                "INNER JOIN productname n USING (idname)\n" +
                "INNER JOIN unit u USING (idunit)\n" +
                "INNER JOIN producttype pt USING (idproducttype)\n" +
                "WHERE idproduct = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, productId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int idProduct = rs.getInt("idproduct");
                    String name = rs.getString("name");
                    String unit = rs.getString("type");
                    int category = rs.getInt("idcategory");
                    double pricePrMeasurement = rs.getDouble("priceprmeasurment");
                    double height = rs.getDouble("height");
                    double width = rs.getDouble("width");
                    int amount = rs.getInt("amount");
                    String productType = rs.getString("producttype");

                    productDTO = new ProductDTO(idProduct, name, unit, category, pricePrMeasurement, height, width, amount, productType);


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
