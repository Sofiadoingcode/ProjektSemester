package dat.startcode.model.persistence;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductDTO;
import dat.startcode.model.DTOs.ProductionlineDTO;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


        BOMDTO bomdto = new BOMDTO(0, 0, "");

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

                    bomdto = new BOMDTO(bomid, totalprice, description);

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



    private ProductDTO getFullProduct(int productID) throws DatabaseException {


        ProductDTO productDTO = new ProductDTO(0,"","", 0,0,0,0,0);

        String sql = "SELECT idproduct, n.`name`, u.`type`, idcategory, priceprmeasurment, height, width, amount\n" +
                "FROM product\n" +
                "INNER JOIN productname n USING (idname)\n" +
                "INNER JOIN unit u USING (idunit)\n" +
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

                    productDTO = new ProductDTO(idproduct, name, unit, category, priceprmeasurment, height, width, amount);


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
