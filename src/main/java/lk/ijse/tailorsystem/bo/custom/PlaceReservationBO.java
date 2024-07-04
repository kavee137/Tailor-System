package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dto.PlaceReservationDTO;
import lk.ijse.tailorsystem.entity.PlaceReservation;

import java.sql.SQLException;

public interface PlaceReservationBO extends SuperBO {
    boolean placeReservation(PlaceReservation pr, double netTotal, String paymentType) throws SQLException;
}
