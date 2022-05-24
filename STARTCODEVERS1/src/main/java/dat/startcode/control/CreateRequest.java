package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.CarportChoices;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.persistence.RequestMapper;
import dat.startcode.model.services.BOMAlgorithm;
import dat.startcode.model.services.SVG;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class CreateRequest extends Command
{
    private ConnectionPool connectionPool;

    public CreateRequest()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException
    {


        double height = Double.parseDouble(request.getParameter("height"));
        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));

        CarportChoices carportChoice = new CarportChoices(height, width, length);

        BOMAlgorithm bomAlgorithm = new BOMAlgorithm();
        List<ProductLine> fullBomList = bomAlgorithm.generateBOM(carportChoice);




        String bomDescription = bomAlgorithm.getDescription();

        double bomTotalPrice = bomAlgorithm.getTotalBOMPrice();

        SVG svg = bomAlgorithm.getSvg();



        String roofMaterial = request.getParameter("tagMateriale");
        String roof = request.getParameter("tag");
        int angle = Integer.parseInt(request.getParameter("angle"));
        String name = request.getParameter("name");
        int zipCode = Integer.parseInt(request.getParameter("zipCode"));
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
        String email = request.getParameter("email");


        User user;
        user=(User)request.getAttribute("tempUser");


        BOMMapper bomMapper = new BOMMapper(connectionPool);

        int bomId = bomMapper.createBOMInDB(bomDescription, bomTotalPrice, svg.toString());

        bomMapper.saveFullBom(bomId, fullBomList);


        RequestMapper requestMapper= new RequestMapper(connectionPool);

        if(Objects.equals(request.getParameter("shedCheckbox"), "shed")) {
            int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
            int shedLength = Integer.parseInt(request.getParameter("shedLength"));
            String floorMaterial = request.getParameter("floorMaterial");
            requestMapper.insertFullRequestShed(shedWidth, shedLength, floorMaterial, height, length, width, roofMaterial, roof, angle, name, zipCode, phoneNumber, email, user.getIdUser(), bomId, bomTotalPrice);
        }
        else{
            requestMapper.insertFullRequest(height, length, width, roofMaterial, roof, angle, name, zipCode, phoneNumber, email, user.getIdUser(), bomId, bomTotalPrice);
        }


        request.setAttribute("tempUser", user);
        request.setAttribute("svg", svg);


        return "orderintroduction.jsp";


    }
}
