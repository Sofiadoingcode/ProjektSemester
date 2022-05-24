package dat.startcode.model.persistence;

import dat.startcode.model.DTOs.BOMDTO;
import dat.startcode.model.DTOs.ProductDTO;
import dat.startcode.model.DTOs.ProductionLineDTO;

import dat.startcode.model.entities.ProductLine;

import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IBOMMapper {

    public BOMDTO getBOM(int orderId) throws DatabaseException;
    public List<ProductionLineDTO> getBOMProductLines(BOMDTO bomdto) throws DatabaseException;
    public void saveFullBom (int orderId, List<ProductLine> fullBom) throws DatabaseException;
    public List<ProductDTO> getAllProductDTOs () throws DatabaseException;





}
