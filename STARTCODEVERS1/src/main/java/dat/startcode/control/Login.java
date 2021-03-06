package dat.startcode.control;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.*;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.persistence.RequestMapper;
import dat.startcode.model.services.UserFacade;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends Command
{
    private ConnectionPool connectionPool;

    public Login()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException
    {
        HttpSession session = request.getSession();
        String ret="index";
        boolean wrongLogin=false;
        session.setAttribute("user", null); // adding empty user object to session scope
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(" "," ",0);
        try {
            user = UserFacade.login(username, password, connectionPool);
        }
        catch(DatabaseException e){
            wrongLogin=true;
            request.setAttribute("wrongLogin", wrongLogin);
            ret = "login.jsp";
        }
        if(!wrongLogin) {
            RequestMapper requestMapper = new RequestMapper(connectionPool);
            BOMMapper bomMapper = new BOMMapper(connectionPool);
            Request usersRequest = requestMapper.getRequestFromDB(user.getIdUser());
            Carport carport = null;
            Shed shed = null;
            BOMDTO bom = null;
            if (usersRequest != null) {
                carport = requestMapper.getCarportChoices(usersRequest.getIdCarportChoices());
                bom = bomMapper.getBOM(usersRequest.getIdOrder());
                if (carport.getIdShed() != 0) {
                    shed = requestMapper.getShedChoices(carport.getIdShed());
                }
            }
            session = request.getSession();
            session.setAttribute("user", user); // adding user object to session scope
            session.setAttribute("usersRequest", usersRequest);
            session.setAttribute("carportChoices", carport);
            session.setAttribute("shedChoices", shed);
            session.setAttribute("bom", bom);
        }
        if(user.getIdRole()==2){
            ret = "viewcarportorder.jsp";

        }

        return ret;
    }
}
