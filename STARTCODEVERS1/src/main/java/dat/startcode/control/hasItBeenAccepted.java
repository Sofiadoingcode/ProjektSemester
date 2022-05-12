package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class hasItBeenAccepted extends Command{
    private ConnectionPool connectionPool;

    public hasItBeenAccepted() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
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
