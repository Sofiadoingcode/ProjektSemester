package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.ProductionFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaveProduct extends Command{
ConnectionPool connectionPool;

    public SaveProduct(){

        connectionPool = ApplicationStart.getConnectionPool();

    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        String name = request.getParameter("name");

        String category =request.getParameter("category");

        String unit = request.getParameter("unit");

        String productType = request.getParameter("product_producttype");

        int amount = Integer.parseInt(request.getParameter("amount"));

        double height =Double.parseDouble(request.getParameter("height"));

        double width = Double.parseDouble(request.getParameter("width"));

        double price = Double.parseDouble(request.getParameter("price"));


        ProductionFacade.createProduct(name,category,unit,amount,height,width,price,productType,connectionPool);

        return "fc/viewProducts?command=viewProducts";
    }
}
