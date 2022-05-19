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
import java.util.ArrayList;
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

            BOMDTO bomdto = bomMapper.getBOM(orderId);


            List<BOMDTO> bomdtos = new ArrayList<>();
            bomdtos.add(bomdto);

            List<ProductionlineDTO> productionlines = bomMapper.getBOMProductlines(bomdto);
            for(ProductionlineDTO pr: productionlines) {
                System.out.println(pr.getCategory());
            }


            List<ProductionlineDTO> category1 = new ArrayList<>();
            List<ProductionlineDTO> category2 = new ArrayList<>();
            for(ProductionlineDTO dto: productionlines) {
                if (dto.getCategory() == 1) {
                    category1.add(dto);
                } else {
                    category2.add(dto);
                }


            }



            request.setAttribute("fullbom", bomdtos);
            request.setAttribute("category1BOM", category1);
            request.setAttribute("category2BOM", category2);




        } catch (DatabaseException e) {
            System.out.println(e);
        }

        return "viewFullRequest.jsp";
    }



}
