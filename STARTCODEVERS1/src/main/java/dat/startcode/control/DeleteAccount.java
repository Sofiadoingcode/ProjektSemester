package dat.startcode.control;

import com.mysql.cj.Session;
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

public class DeleteAccount extends Command{
    private ConnectionPool connectionPool;


    public DeleteAccount(){
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String deleteId = request.getParameter("delete");
        int idToDelete = Integer.parseInt(deleteId);
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        try {
            customerMapper.deleteAccount(username, password);
            int userIdInDB = customerMapper.checkDeletedId(username, password);
            System.out.println("uiid = " + userIdInDB + " iTD = " + idToDelete);
            if(userIdInDB == idToDelete){
                session.invalidate();
            }
        } catch (DatabaseException e) {
            System.out.println(e);
        }


        return "adminaccountdeletionconfirmation.jsp";
    }
}
