package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class unAcceptRequest extends Command{
    private ConnectionPool connectionPool;

    public unAcceptRequest() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();
        String idUnAccept = request.getParameter("unAccept");
        int unAcceptId = Integer.parseInt(idUnAccept);
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        try {
            customerMapper.unAcceptRequest(unAcceptId);
        } catch (DatabaseException e) {
            System.out.println(e);
        }

        return "fc/requestList?command=requestList";
    }
}
