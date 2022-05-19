package dat.startcode.control;

import dat.startcode.model.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSVG extends Command
{
    int stolpeDisplacementLength = 110;
    int stolpeDisplacementWith = 35;
    int spærStartingPoint = 100;

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        SVG svg = new SVG(0,0, "0 0 800 600", 100, 100);

        /*Rem*/

        svg.addRect(100, stolpeDisplacementWith, 4, 800);
        svg.addRect(100, 600-stolpeDisplacementWith, 4, 800);

        /*Spær*/

        for(int x = 0 ; x < 15 ; x++) {
            svg.addRect(spærStartingPoint + 57 * x, 0, 600, 4);
        }

        /*Stolper*/

        svg.addRectStolpe(spærStartingPoint + stolpeDisplacementLength, stolpeDisplacementWith, 12, 12);
        svg.addRectStolpe(spærStartingPoint + stolpeDisplacementLength, 565, 12, 12);
        svg.addRectStolpe(900, stolpeDisplacementWith, 12, 12);
        svg.addRectStolpe(900, 600-stolpeDisplacementWith, 12, 12);


/*

        for (int x = 0 ; x < 15 ; x++) {
        svg.addRect(100 + 57 * x, 0, 600, 4);
        }

        for(int x = 0 ; x < 15 ; x++) {
        svg.addRect(100 + 57 * x, 0, 600, 4);
        }
*/

        /*Hulbånd*/

        svg.addDottedLine(100, 35, 900, 600-35, 3);
        svg.addDottedLine(100, 600-35, 900, 35, 3);

        HttpSession session = request.getSession();
        request.setAttribute("svgdrawing", svg.toString());
        return "svgtegning.jsp";
    }
}