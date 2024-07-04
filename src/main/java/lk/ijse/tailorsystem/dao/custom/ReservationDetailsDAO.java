package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.ReservationDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ReservationDetailsDAO extends CrudDAO<ReservationDetails> {


    ResultSet getReservationDetailsTable(int id) throws SQLException, ClassNotFoundException;

    boolean save(List<ReservationDetails> rdList) throws SQLException, ClassNotFoundException;
}
