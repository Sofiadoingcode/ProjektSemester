package dat.startcode.model.persistence;

import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface ICustomerMapper {


    public List<Customer> getAllNonAcceptedRequests() throws DatabaseException;
    public List<Customer> getAllAcceptedRequests() throws DatabaseException;
    public List<Customer> getAllPaidRequests() throws DatabaseException;



}
