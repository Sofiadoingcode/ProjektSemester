package dat.startcode.model.persistence;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductionlineDTO;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IBOMMapper {

    public BOMDTO getBOM(int orderID) throws DatabaseException;
    public List<ProductionlineDTO> getBOMProductlines(BOMDTO bomdto) throws DatabaseException;




}
