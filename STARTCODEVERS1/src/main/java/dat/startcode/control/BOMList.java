package dat.startcode.control;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductionlineDTO;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BOMList extends Command {
    private ConnectionPool connectionPool;

    public BOMList() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {


        String idOrder = request.getParameter("seeStykliste");
        int orderId = Integer.parseInt(idOrder);

        BOMMapper bomMapper = new BOMMapper(connectionPool);

        try {
            System.out.println("11");
            BOMDTO bomdto = bomMapper.getBOM(orderId);
            System.out.println("121212");
            List<ProductionlineDTO> productionlines = bomMapper.getBOMProductlines(bomdto);

            System.out.println("22");
            request.setAttribute("fullbom", bomdto);
            request.setAttribute("productionlines", productionlines);




        } catch (DatabaseException e) {
            System.out.println("ERROROORORORO");
            System.out.println(e);
        }
        System.out.println("33");
        return "viewFullRequest.jsp";
    }



}
