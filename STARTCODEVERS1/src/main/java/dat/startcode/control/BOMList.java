package dat.startcode.control;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductionLineDTO;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

            List<ProductionLineDTO> productionLines = bomMapper.getBOMProductLines(bomdto);
            

            List<ProductionLineDTO> category1 = new ArrayList<>();
            List<ProductionLineDTO> category2 = new ArrayList<>();
            for(ProductionLineDTO dto: productionLines) {
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

        return "viewfullrequest.jsp";
    }



}
