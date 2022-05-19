package dat.startcode.control;

import dat.startcode.model.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSVG extends Command
{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        SVG svg = new SVG(0,0, "0 0 800 600", 100, 100);

        for(int x = 0; x < 14 ; x++) {
            svg.addRect(100 + 50 * x, 0, 600, 4.5);
        }

        HttpSession session = request.getSession();
        request.setAttribute("svgdrawing", svg.toString());
        return "svgtegning.jsp";
    }
}