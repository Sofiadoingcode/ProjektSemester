package dat.startcode.control;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductionlineDTO;
import dat.startcode.model.entities.CarportChoices;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.services.BOMAlgorithm;

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

        CarportChoices carportChoice = new CarportChoices(height, width, length);

        BOMAlgorithm bomAlgorithm = new BOMAlgorithm();
        List<ProductLine> BOMProductlines = new ArrayList<>();
        BOMProductlines = bomAlgorithm.generateBOM(carportChoice);



        //NOT CORRECT JUST FOR NOW
        return "orderintroduction.jsp";


    }
}

