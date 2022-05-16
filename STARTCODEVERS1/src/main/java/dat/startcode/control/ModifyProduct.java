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

        System.out.println(productType);

        int id = Integer.parseInt(request.getParameter("product_id"));
        int idname = productMapper.getNameID(name);
        int idunit = productMapper.getUnitTypeID(unit);
        int idcategory = productMapper.getCategoryID(category);
        int price = Integer.parseInt(request.getParameter("product_price"));
        int heihgt = Integer.parseInt(request.getParameter("product_height"));
        int width = Integer.parseInt(request.getParameter("product_width"));
        int amount = Integer.parseInt(request.getParameter("product_amount"));
        int idproductType = productMapper.getProductTypeID(productType);

        productMapper.modifyProduct(id, idname,idunit,idcategory,price,heihgt,width,amount,idproductType);


        return "/fc/ViewProducts?command=ViewProducts";
    }
}
