package dat.startcode.control;

import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

abstract class Command
{

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("about", new About());
        commands.put("requestList", new RequestList());
        commands.put("showTempUser", new ShowTempUser());
        commands.put("deleteRequest", new DeleteRequest());
        commands.put("acceptRequest", new AcceptRequest());
        commands.put("unAcceptRequest", new unAcceptRequest());
        commands.put("BOMlist", new BOMList());
        commands.put("generateBom", new GenerateBOM());
        commands.put("createAdminAccount", new CreateAdminAccount());
        commands.put("deleteAccount", new DeleteAccount());
        commands.put("viewProducts", new ViewProducts());
        commands.put("saveproduct",new SaveProduct());
        commands.put("modifyproduct",new ModifyProduct());
        commands.put("deleteproduct",new DeleteProduct());
        commands.put("createRequest", new CreateRequest());
        commands.put("tempLogin", new TempLogin());
        commands.put("payForRequest", new PayForRequest());
        commands.put("seebom", new SeeBOM());

    }

    static Command from( HttpServletRequest request ) {
        String commandName = request.getParameter( "command" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand() );   // unknowncommand er default.
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws DatabaseException;

}
