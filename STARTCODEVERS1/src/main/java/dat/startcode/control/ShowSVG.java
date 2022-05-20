package dat.startcode.control;

import dat.startcode.model.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSVG extends Command {
    int stolpeDisplacementLengthFront = 110;
    int stolpeDisplacementWidth = 35;
    int spærStartingPoint = 100;
    int stolpeCounter = 0;
    int carportWidthWithDisplacement = 600 - (stolpeDisplacementWidth * 2); //Change 600 to carportWidth
    int carportLengthWithDisplacement = 800 - (stolpeDisplacementLengthFront); //Change 800 to carportLength
    int stolpeAmountWidth = 0;
    int stolpeAmountLength = 0;
    int stolpeAmountTotal = 9;
    int distanceBewteenStolpeWidth = 0;
    int distanceBewteenStolpeLength = 0;

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {
        SVG svg = new SVG(0, 0, "0 0 800 600", 100, 100);

        svg.addRect(spærStartingPoint, 0, 600, 800);

        /*Rem*/

        svg.addRect(100, stolpeDisplacementWidth, 4, 800);
        svg.addRect(100, 600 - stolpeDisplacementWidth, 4, 800);

        /*Spær*/

        for (int x = 0; x < 15; x++) {
            svg.addRect(spærStartingPoint + 57 * x, 0, 600, 4);
        }

        /*Stolper*/

        while (true) {
            stolpeCounter += 300;
            if (stolpeCounter < carportWidthWithDisplacement) {
                stolpeAmountWidth++;
            } else {
                break;
            }
        }
        stolpeAmountWidth = 2;

        stolpeAmountLength = (stolpeAmountTotal - stolpeAmountWidth) / 2;

        distanceBewteenStolpeWidth = carportWidthWithDisplacement / (stolpeAmountWidth + 1);
        distanceBewteenStolpeLength = carportLengthWithDisplacement / (stolpeAmountLength + 1);


        svg.addRectStolpe(spærStartingPoint + stolpeDisplacementLengthFront, stolpeDisplacementWidth, 12, 12);
        svg.addRectStolpe(spærStartingPoint + stolpeDisplacementLengthFront, 565, 12, 12);
        svg.addRectStolpe(900, stolpeDisplacementWidth, 12, 12);
        svg.addRectStolpe(900, 600 - stolpeDisplacementWidth, 12, 12);

        for (int i = 1; i <= stolpeAmountWidth; i++) {
            svg.addRectStolpe(900, stolpeDisplacementWidth + distanceBewteenStolpeWidth * i, 12, 12); //Change 900 to carportLength
        }

        for (int i = 1; i <= stolpeAmountLength; i++) {
            svg.addRectStolpe(spærStartingPoint + stolpeDisplacementLengthFront + distanceBewteenStolpeLength * i, stolpeDisplacementWidth, 12, 12); //Change 900 to carportLength
        }

        for (int i = 1; i <= stolpeAmountLength; i++) {
            svg.addRectStolpe(spærStartingPoint + stolpeDisplacementLengthFront + distanceBewteenStolpeLength * i, 600 -  stolpeDisplacementWidth, 12, 12); //Change 900 to carportLength
        }



/*

        for (int x = 0 ; x < 15 ; x++) {
        svg.addRect(100 + 57 * x, 0, 600, 4);
        }

        for(int x = 0 ; x < 15 ; x++) {
        svg.addRect(100 + 57 * x, 0, 600, 4);
        }
*/

        /*Hulbånd*/

        svg.addDottedLine(100, 35, 900, 600 - 35, 3);
        svg.addDottedLine(100, 600 - 35, 900, 35, 3);

        HttpSession session = request.getSession();
        request.setAttribute("svgdrawing", svg.toString());
        return "svgtegning.jsp";
    }
}