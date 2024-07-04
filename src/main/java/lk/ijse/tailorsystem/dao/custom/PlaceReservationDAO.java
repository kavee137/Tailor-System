package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.dto.PlaceReservationDTO;
import lk.ijse.tailorsystem.entity.PlaceReservation;

import java.sql.SQLException;

public interface PlaceReservationDAO extends CrudDAO<PlaceReservation> {


    boolean placeReservation(PlaceReservation pr, double netTotal, String paymentType) throws SQLException;
}
