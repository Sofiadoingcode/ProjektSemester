package dat.startcode.model.persistence;

import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;

public interface ICustomerMapper {

    public Customer getAllNonAcceptedRequests() throws DatabaseException;
    public Customer getAllAcceptedRequests() throws DatabaseException;
    public Customer getAllPaidRequests() throws DatabaseException;



}
