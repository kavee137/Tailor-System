package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.PlaceReservationBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.PaymentDAO;
import lk.ijse.tailorsystem.dao.custom.ProductDAO;
import lk.ijse.tailorsystem.dao.custom.ReservationDAO;
import lk.ijse.tailorsystem.dao.custom.ReservationDetailsDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.dto.ReservationDTO;
import lk.ijse.tailorsystem.entity.PlaceReservation;
import lk.ijse.tailorsystem.entity.Reservation;
import lk.ijse.tailorsystem.entity.ReservationDetails;
import lk.ijse.tailorsystem.view.tdm.PaymentTm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceReservationBOImpl implements PlaceReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    ReservationDetailsDAO reservationDetailsDAO = (ReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONDETAILS);


    @Override
    public boolean placeReservation(PlaceReservation pr, double netTotal, String paymentType) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isMakePayment = paymentDAO.newPayment(pr.getReservation().getPaymentID(), netTotal, paymentType);
            boolean isOrderSaved = reservationDAO.add(pr.getReservation());
            boolean isQtyUpdated = productDAO.reservatioQtyUpdate(pr.getRdList());
            boolean isOrderDetailSaved = reservationDetailsDAO.save(pr.getRdList());

            if (isMakePayment && isOrderSaved && isQtyUpdated && isOrderDetailSaved) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        }  catch (SQLException e) {
            connection.rollback();
            throw e; // Re-throw the exception to propagate it
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }


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



    @Override
    public ResultSet getReservationDetailsTable(int id) throws SQLException, ClassNotFoundException {
        return reservationDetailsDAO.getReservationDetailsTable(id);
    }


    @Override
    public boolean save(List<ReservationDetails> rdList) throws SQLException, ClassNotFoundException{
        return reservationDetailsDAO.save(rdList);
    }


}
