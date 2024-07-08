package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dto.ReservationDTO;
import lk.ijse.tailorsystem.entity.PlaceReservation;
import lk.ijse.tailorsystem.entity.Reservation;
import lk.ijse.tailorsystem.entity.ReservationDetails;
import lk.ijse.tailorsystem.view.tdm.PaymentTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlaceReservationBO extends SuperBO {


    String getReservationCount() throws SQLException, ClassNotFoundException;

    boolean update(ReservationDTO resId) throws SQLException, ClassNotFoundException;

    ArrayList<String> getReservationJoinTable(int id) throws SQLException, ClassNotFoundException;

    ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException;

    boolean add(ReservationDTO reservation) throws SQLException, ClassNotFoundException;

    List<PaymentTm> getDetails() throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    ResultSet getReservationDetailsTable(int id) throws SQLException, ClassNotFoundException;

    boolean save(List<ReservationDetails> rdList) throws SQLException, ClassNotFoundException;

    boolean placeReservation(PlaceReservation pr, double netTotal, String paymentType) throws SQLException;
}
