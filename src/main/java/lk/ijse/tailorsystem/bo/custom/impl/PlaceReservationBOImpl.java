package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.PlaceReservationBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.PlaceReservationDAO;
import lk.ijse.tailorsystem.entity.PlaceReservation;

import java.sql.SQLException;

public class PlaceReservationBOImpl implements PlaceReservationBO {

    PlaceReservationDAO placeReservationDAO = (PlaceReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PLACERESERVATION);


    @Override
    public boolean placeReservation(PlaceReservation pr, double netTotal, String paymentType) throws SQLException {
        return placeReservationDAO.placeReservation(pr, netTotal, paymentType);
    }
}
