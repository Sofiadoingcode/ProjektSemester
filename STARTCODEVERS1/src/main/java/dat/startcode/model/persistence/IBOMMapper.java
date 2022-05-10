package dat.startcode.model.persistence;

import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IBOMMapper {

    public List<ProductLine> getBOMProductlines(int orderID) throws DatabaseException;



}
