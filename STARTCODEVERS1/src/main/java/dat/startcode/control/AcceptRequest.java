package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcceptRequest extends Command{
    private ConnectionPool connectionPool;

    public AcceptRequest() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        String idAccept = request.getParameter("accept");
        int acceptId = Integer.parseInt(idAccept);
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        try {
            customerMapper.acceptRequest(acceptId);
        } catch (DatabaseException e) {
            System.out.println(e);
        }

        return "fc/requestList?command=requestList";
    }
}
