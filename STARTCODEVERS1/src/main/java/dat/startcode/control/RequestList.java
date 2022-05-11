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

        ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

        CustomerMapper customerMapper = new CustomerMapper(connectionPool);

        List<Customer> nonAcceptedRequests = customerMapper.getAllNonAcceptedRequests();
        List<Customer> acceptedRequests = customerMapper.getAllAcceptedRequests();
        List<Customer> paidRequests = customerMapper.getAllPaidRequests();
        request.setAttribute("nonAcceptedRequests", nonAcceptedRequests);
        request.setAttribute("acceptedRequests", acceptedRequests);
        request.setAttribute("paidRequests", paidRequests);


        return "requestList.jsp";

    }
}
