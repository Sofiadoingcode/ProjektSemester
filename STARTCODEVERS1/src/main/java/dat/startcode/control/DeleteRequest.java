package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteRequest extends Command {
    private ConnectionPool connectionPool;

    public DeleteRequest() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();
        String idDelete = request.getParameter("delete");
        int deleteId = Integer.parseInt(idDelete);
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);

        try {
            customerMapper.deleteOrder(deleteId);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        return "fc/requestList?command=requestList";
    }
}
