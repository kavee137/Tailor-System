package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.entity.ReservationDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ReservationDetailsBO extends SuperBO {
    ResultSet getReservationDetailsTable(int id) throws SQLException, ClassNotFoundException;

    boolean save(List<ReservationDetails> rdList) throws SQLException, ClassNotFoundException;
}
