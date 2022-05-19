package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;
import dat.startcode.model.services.ProductionFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.HashMap;
import java.util.List;

public class ViewProducts extends Command {
    ConnectionPool connectionPool;

    public ViewProducts() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {


        List<Product> products = ProductionFacade.getProducts(connectionPool);

        ProductMapper productMapper = new ProductMapper(connectionPool);
        HashMap<Integer, String> Unitlist= productMapper.getUnits();
        HashMap<Integer, String> CategoryList= productMapper.getCategories();
        HashMap<Integer, String> productTypeList = productMapper.getProductTypes();

        request.getServletContext().setAttribute("ProductsList", products);
        request.getServletContext().setAttribute("UnitList",Unitlist);
        request.getServletContext().setAttribute("CategoryList",CategoryList);
        request.getServletContext().setAttribute("ProductTypeList",productTypeList);
        return "products.jsp";
    }
}
