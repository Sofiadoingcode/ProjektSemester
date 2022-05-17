package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PayForRequest extends Command{
    private ConnectionPool connectionPool;

    public PayForRequest() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {
        String idPay = request.getParameter("pay");
        int payId = Integer.parseInt(idPay);
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        try {
            customerMapper.payForRequest(payId);
        } catch (DatabaseException e) {
            System.out.println(e);
        }



        return "viewcarportorder.jsp";
    }
}