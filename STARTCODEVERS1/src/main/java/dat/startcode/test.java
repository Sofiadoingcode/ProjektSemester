package dat.startcode;

import dat.startcode.model.DTOs.ProductDTO;
import dat.startcode.model.entities.ProductLine;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BOMMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.BOMAlgorithm;
import dat.startcode.model.services.ProductionFacade;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
       ConnectionPool connectionPool = new ConnectionPool();
        BOMAlgorithm bomAlgorithm = null;
        try {
            bomAlgorithm = new BOMAlgorithm(connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        BOMMapper bomMapper = new BOMMapper(connectionPool);
        List <ProductDTO> allproducts= new ArrayList<>();
        List<ProductLine> rems = new ArrayList<>();
        List<ProductLine> spærs = new ArrayList<>();
        try {
            allproducts = bomMapper.getAllProductDTOs();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        rems = bomAlgorithm.calculateRemProductLines(allproducts, 8, 8, 200);

        spærs = bomAlgorithm.calculateSpærProductLines(allproducts, 8, 220, 220);

        for (ProductLine spær : spærs) {
            System.out.println(spær.getAmount());
            System.out.println(spær.getTotalproductprice());
        }
    }
}
