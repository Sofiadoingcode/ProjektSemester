package dat.startcode.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TempLogin extends Command
{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        String s= request.getParameter("loginCredentials");
        String[] login = s.split(" ");
        String username = login[0];
        String password = login[1];

        request.setAttribute("username", username);
        request.setAttribute("password", password);

        return "login.jsp";
    }
}