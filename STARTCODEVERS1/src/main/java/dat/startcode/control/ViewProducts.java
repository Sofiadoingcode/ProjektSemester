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
        HashMap<Integer, String> unitList= productMapper.getUnits();
        HashMap<Integer, String> categoryList= productMapper.getCategories();
        HashMap<Integer, String> productTypeList = productMapper.getProductTypes();

        request.getServletContext().setAttribute("productsList", products);
        request.getServletContext().setAttribute("unitList",unitList);
        request.getServletContext().setAttribute("categoryList",categoryList);
        request.getServletContext().setAttribute("productTypeList",productTypeList);
        return "products.jsp";
    }
}
