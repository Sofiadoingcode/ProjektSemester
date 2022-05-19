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
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        System.out.println("name" + name);
        String category =request.getParameter("category");
        System.out.println("category" + category);
        String unit = request.getParameter("unit");
        System.out.println("unit" + unit);
        String productType = request.getParameter("product_producttype");

        int amount = Integer.parseInt(request.getParameter("amount"));
        System.out.println("amount" + amount);
        int height = Integer.parseInt(request.getParameter("height"));
        System.out.println("height" + height);
        int width = Integer.parseInt(request.getParameter("width"));
        System.out.println("width" + width);
        int price = Integer.parseInt(request.getParameter("price"));
        System.out.println("price" + price);

        ProductionFacade.createProduct(name,category,unit,amount,height,width,price,productType,connectionPool);
        System.out.println("b");
        return "products.jsp";
    }
}
