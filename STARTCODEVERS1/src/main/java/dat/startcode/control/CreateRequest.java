package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.RequestMapper;
import dat.startcode.model.persistence.UserMapper;
import dat.startcode.model.services.UserFacade;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        int height = Integer.parseInt(request.getParameter("height"));
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        String tagMateriale = request.getParameter("tagMateriale");
        String tag = request.getParameter("tag");
        int angle = Integer.parseInt(request.getParameter("angle"));
        String name = request.getParameter("name");
        int zipCode = Integer.parseInt(request.getParameter("zipCode"));
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
        String email = request.getParameter("email");


        User user;
        user=(User)request.getAttribute("tempUser");

        RequestMapper requestMapper= new RequestMapper(connectionPool);
        if(Objects.equals(request.getParameter("shedCheckbox"), "shed")) {
            int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
            int shedLength = Integer.parseInt(request.getParameter("shedLength"));
            String floorMaterial = request.getParameter("floorMaterial");
            requestMapper.insertFullRequestShed(shedWidth, shedLength, floorMaterial, height, length, width, tagMateriale, tag, angle, name, zipCode, phoneNumber, email, user.getIdUser(), 1);
        }
        else{
            requestMapper.insertFullRequest(height, length, width, tagMateriale, tag, angle, name, zipCode, phoneNumber, email, user.getIdUser(), 1);
        }
        request.setAttribute("tempUser", user);
        return "orderintroduction.jsp";


    }
}
