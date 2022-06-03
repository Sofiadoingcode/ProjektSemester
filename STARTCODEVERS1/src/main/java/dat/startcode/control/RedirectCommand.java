package dat.startcode.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RedirectCommand extends Command
{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        String ret="";
        ret="WEB-INF/orderintroduction.jsp";

        return ret;
    }
}