package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            int userIdInDB = customerMapper.checkDeletedId(username, password);
            customerMapper.deleteAccountUsingLogin(username, password);
            if(userIdInDB == idToDelete){
                session.invalidate();
            }
        } catch (DatabaseException e) {
            System.out.println(e);
        }


        return "adminaccountdeletionconfirmation.jsp";
    }
}
