package dat.startcode.control;


import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.persistence.ProductMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteProduct extends Command {
    private ConnectionPool connectionPool;

    public DeleteProduct() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        String idDelete = request.getParameter("product_id");
        int deleteId = Integer.parseInt(idDelete);
        ProductMapper productMapper = new ProductMapper(connectionPool);
        productMapper.deleteProduct(deleteId);


        return "fc/viewProducts?command=viewProducts";
    }

}
