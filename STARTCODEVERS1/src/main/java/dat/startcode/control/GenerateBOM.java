package dat.startcode.control;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductionlineDTO;
import dat.startcode.model.entities.CarportChoices;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.services.BOMAlgorithm;
import dat.startcode.model.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GenerateBOM extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        String heightString = request.getParameter("height");
        double height = Double.parseDouble(heightString);

        String lengthString = request.getParameter("length");
        double length = Double.parseDouble(lengthString);

        String widthString = request.getParameter("width");
        double width = Double.parseDouble(widthString);

        int angle = Integer.parseInt(request.getParameter("angle"));

        String name = request.getParameter("name");

        String email = request.getParameter("email");

        String zipCode = request.getParameter("zipCode");

        String phoneNumber = request.getParameter("phoneNumber");

        CarportChoices carportChoice = new CarportChoices(height, width, length);

        BOMAlgorithm bomAlgorithm = new BOMAlgorithm();
        List<ProductLine> BOMProductlines = new ArrayList<>();
        BOMProductlines = bomAlgorithm.generateBOM(carportChoice);
        SVG svg = bomAlgorithm.getSvg();

        String description = bomAlgorithm.getDescription();

        request.setAttribute("description", description);

        request.setAttribute("height", height);
        request.setAttribute("length", length);
        request.setAttribute("width", width);
        request.setAttribute("angle", angle);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("zipCode", zipCode);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("svg", svg);
        //NOT CORRECT JUST FOR NOW
        return "orderintroduction.jsp";


    }
}

