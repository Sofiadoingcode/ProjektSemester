package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyProduct extends Command {
    private ConnectionPool connectionPool;

    public ModifyProduct() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        ProductMapper productMapper = new ProductMapper(connectionPool);


        String name = request.getParameter("product_name");
        String unit = request.getParameter("product_unit");
        String category = request.getParameter("product_category2");
        String productType = request.getParameter("product_producttype");

        int id = Integer.parseInt(request.getParameter("product_id"));
        int idName = productMapper.getNameId(name);
        int idUnit = productMapper.getUnitTypeId(unit);
        int idCategory = productMapper.getCategoryId(category);
        double price = Double.parseDouble(request.getParameter("product_price"));
        double height = Double.parseDouble(request.getParameter("product_height"));
        double width = Double.parseDouble(request.getParameter("product_width"));
        int amount = Integer.parseInt(request.getParameter("product_amount"));
        int idProductType = productMapper.getProductTypeId(productType);

        productMapper.modifyProduct(id, idName,idUnit,idCategory,price,height,width,amount,idProductType);


        return "/fc/viewProducts?command=viewProducts";
    }
}
