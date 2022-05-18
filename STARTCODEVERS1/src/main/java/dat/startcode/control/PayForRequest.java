package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.persistence.RequestMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PayForRequest extends Command {
    private ConnectionPool connectionPool;

    public PayForRequest() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String idPay = request.getParameter("pay");
        System.out.println("idPay: " + idPay);
        int payId = Integer.parseInt(idPay);
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        RequestMapper requestMapper = new RequestMapper(connectionPool);
        Request usersRequest = requestMapper.getRequestFromDB(user.getIdUser());
        Carport carport = null;
        Shed shed = null;

        try {
            customerMapper.payForRequest(payId);
            if (usersRequest != null) {
                carport = requestMapper.getCarportChoices(usersRequest.getIdcarportchoices());
                if (carport.getIdShed() != 0) {
                    shed = requestMapper.getShedChoices(carport.getIdShed());
                }
            }
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        usersRequest = requestMapper.getRequestFromDB(user.getIdUser());
        session = request.getSession();
        session.setAttribute("usersRequest", usersRequest);
        session.setAttribute("carportChoices", carport);
        session.setAttribute("shedChoices", shed);


        return "viewcarportorder.jsp";
    }
}