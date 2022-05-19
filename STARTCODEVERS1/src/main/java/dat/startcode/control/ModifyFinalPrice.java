package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.RequestMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyFinalPrice extends Command{
    private ConnectionPool connectionPool;

    public ModifyFinalPrice(){
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        String orderId = request.getParameter("modifyFP");
        int idOrder = Integer.parseInt(orderId);

        String finalPrice = request.getParameter("finalPriceText");
        double modifyPrice = Double.parseDouble(finalPrice);

        RequestMapper requestMapper = new RequestMapper(connectionPool);

        requestMapper.modifyFinalPrice(idOrder, modifyPrice);


        return "fc/requestList?command=requestList";
    }
}
