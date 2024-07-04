package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.Reservation;
import lk.ijse.tailorsystem.view.tdm.PaymentTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation> {


    String getReservationCount() throws SQLException, ClassNotFoundException;

    ArrayList<String> getReservationJoinTable(int id) throws SQLException, ClassNotFoundException;

    List<PaymentTm> getDetails() throws SQLException, ClassNotFoundException;
}
