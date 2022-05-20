package dat.startcode.control;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductionlineDTO;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.entities.Request;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SeeBOM extends Command {
    ConnectionPool connectionPool;

    public SeeBOM() {
        connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        BOMMapper bomMapper = new BOMMapper(connectionPool);
        HttpSession session = request.getSession();
        Request usersRequest = (Request) session.getAttribute("usersRequest");
        BOMDTO bomdto = bomMapper.getBOM(usersRequest.getIdbom());
        List<ProductionlineDTO> productionlines = bomMapper.getBOMProductlines(bomdto);

        List<ProductionlineDTO> category1 = new ArrayList<>();
        List<ProductionlineDTO> category2 = new ArrayList<>();
        for(ProductionlineDTO dto: productionlines) {
            if (dto.getCategory() == 1) {
                category1.add(dto);
            } else {
                category2.add(dto);
            }
        }
        System.out.println(category1.size());
        System.out.println(category2.size());
        session.setAttribute("category1",category1);
        session.setAttribute("category2",category2);


        return "viewBOMCustomer.jsp";
    }
}
