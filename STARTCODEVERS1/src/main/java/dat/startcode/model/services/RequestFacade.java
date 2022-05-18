package dat.startcode.model.services;

import dat.startcode.model.entities.Request;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;
import dat.startcode.model.persistence.RequestMapper;

public class RequestFacade {




    public static void insertFullRequestShed(int shedWidth, int shedLength, String floorMaterial, int carportHeight, int carportLength, int carportWidth, String roofMaterial, String roofShape, int roofAngle, String name, int zipCode, int phoneNumber, String email, int idUser, int idBom, ConnectionPool connectionPool ) {

        RequestMapper requestMapper = new RequestMapper(connectionPool);
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);


        try {



            requestMapper.insertShedChoices(shedWidth, shedLength, floorMaterial);
            requestMapper.insertCarportChoicesShed(carportHeight, carportLength, carportWidth, roofMaterial, roofShape, roofAngle);
            requestMapper.insertCustomer(name, zipCode, phoneNumber, email);
            requestMapper.insertRequest(idUser, idBom);



        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public static void insertFullRequest(int carportHeight, int carportLength, int carportWidth, String roofMaterial, String roofShape, int roofAngle, String name, int zipCode, int phoneNumber, String email, int idUser, int idBom, ConnectionPool connectionPool) {

        RequestMapper requestMapper = new RequestMapper(connectionPool);
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);

        try {
            requestMapper.insertCarportChoices(carportHeight, carportLength, carportWidth, roofMaterial, roofShape, roofAngle);
            requestMapper.insertCustomer(name, zipCode, phoneNumber, email);
            requestMapper.insertRequest(idUser, idBom);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
