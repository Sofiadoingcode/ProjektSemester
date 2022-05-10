package dat.startcode.model.persistence;

import dat.startcode.model.entities.Customer;
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

    @Override
    public List<ProductLine> getBOMProductlines(int orderID) throws DatabaseException {
        List<ProductLine> productLines = new ArrayList<>();

        String sql = "SELECT idbom, totalprice, `description`, o.idorder, pl.idproductionline, pl.idproduct, pl.amount, pl.idlength, pl.totalproductprice\n" +
                "FROM BOM\n" +
                "INNER JOIN `order` o USING (idbom)\n" +
                "INNER JOIN `productionline` pl USING (idbom)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {



                    ProductLine productLine = new ProductLine();
                    productLines.add(productLine);

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning fra databasen");
        }


        return productLines;
    }
}
