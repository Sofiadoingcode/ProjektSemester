package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProduct extends Command{
ConnectionPool connectionPool;

    public SaveProduct(){

        connectionPool = ApplicationStart.getConnectionPool();

    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {




        return "ViewProducts";
    }
}
