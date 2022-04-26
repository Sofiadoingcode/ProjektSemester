package dat.startcode.control;

import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sofia extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        int hej = 1;
        return "index";
    }
}
