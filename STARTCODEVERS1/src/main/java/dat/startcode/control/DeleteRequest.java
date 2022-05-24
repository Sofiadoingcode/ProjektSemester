package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteRequest extends Command {
    private ConnectionPool connectionPool;

    public DeleteRequest() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        String idDelete = request.getParameter("delete");
        int orderDeleteId = Integer.parseInt(idDelete);
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        int userId = customerMapper.checkIdOfUserFromOrderId(orderDeleteId);


        try {
            customerMapper.deleteOrder(orderDeleteId);
            customerMapper.deleteAccountUsingId(userId);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        return "fc/requestList?command=requestList";
    }
}
