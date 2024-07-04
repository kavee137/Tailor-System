package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.ReservationBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.ReservationDAO;
import lk.ijse.tailorsystem.dto.ReservationDTO;
import lk.ijse.tailorsystem.entity.Reservation;
import lk.ijse.tailorsystem.view.tdm.PaymentTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public String getReservationCount() throws SQLException, ClassNotFoundException {
       return reservationDAO.getReservationCount();
    }

    @Override
    public boolean update(ReservationDTO res) throws SQLException, ClassNotFoundException {
        return reservationDAO.update(new Reservation(res.getReservationID(), res.getCustomerID(), res.getPaymentID(), res.getReservationDate(), res.getReturnDate(), res.getStatus()));
    }

    @Override
    public ArrayList<String> getReservationJoinTable(int id) throws SQLException, ClassNotFoundException {
        return reservationDAO.getReservationJoinTable(id);
    }


    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        return reservationDAO.getAll();
    }

    @Override
    public boolean add(ReservationDTO res) throws SQLException, ClassNotFoundException {
        return reservationDAO.add(new Reservation(res.getReservationID(), res.getCustomerID(), res.getPaymentID(), res.getReservationDate(), res.getReturnDate(), res.getStatus()));
    }


    @Override
    public List<PaymentTm> getDetails() throws SQLException, ClassNotFoundException {
        return reservationDAO.getDetails();
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
       return reservationDAO.generateNewID();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return reservationDAO.delete(id);
    }

}
