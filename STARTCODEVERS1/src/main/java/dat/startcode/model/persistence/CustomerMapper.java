package dat.startcode.model.persistence;

import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;

public class CustomerMapper implements ICustomerMapper{
    ConnectionPool connectionPool;

    public CustomerMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    @Override
    public Customer getAllNonAcceptedRequests() throws DatabaseException {
        return null;
    }

    @Override
    public Customer getAllAcceptedRequests() throws DatabaseException {
        return null;
    }

    @Override
    public Customer getAllPaidRequests() throws DatabaseException {
        return null;
    }
}
