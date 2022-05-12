package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAdminAccount extends Command{
    private ConnectionPool connectionPool;

    public CreateAdminAccount(){
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        System.out.println("tiger");
       String username = request.getParameter("username");
       String password = request.getParameter("password");
        UserMapper userMapper = new UserMapper(connectionPool);
        System.out.println("Abe");
        try {
            userMapper.createAdmin(username, password);
        } catch (DatabaseException e) {
            System.out.println(e);
        }

        return "index";
    }
}
