package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RequestList extends Command {




    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        System.out.println("AAA");
        HttpSession session = request.getSession();
        session.setAttribute("user", null);

        System.out.println("BBB");
        ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);

        System.out.println("CCC");
        List<Customer> nonAcceptedRequests = customerMapper.getAllNonAcceptedRequests();
        request.setAttribute("nonAcceptedRequests", nonAcceptedRequests);

        System.out.println("DDD");
        return "requestList";

    }
}
