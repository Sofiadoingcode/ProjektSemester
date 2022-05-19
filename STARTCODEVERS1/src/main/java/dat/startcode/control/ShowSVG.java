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

        svg.addRect(100, 35, 4, 800);
        svg.addRect(100, 600-35, 4, 800);

        for(int x = 0 ; x < 15 ; x++) {
            svg.addRect(100 + 57 * x, 0, 600, 4);
        }

        for (int x = 0 ; x < 15 ; x++) {
        svg.addRect(100 + 57 * x, 0, 600, 4);
        }

        for(int x = 0 ; x < 15 ; x++) {
        svg.addRect(100 + 57 * x, 0, 600, 4);
        }

        svg.addDottedLine(100, 35, 900, 600-35, 3);
        svg.addDottedLine(100, 600-35, 900, 35, 3);

        HttpSession session = request.getSession();
        request.setAttribute("svgdrawing", svg.toString());
        return "svgtegning.jsp";
    }
}