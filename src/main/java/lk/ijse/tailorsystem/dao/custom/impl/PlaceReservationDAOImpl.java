package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.*;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.dto.PlaceReservationDTO;
import lk.ijse.tailorsystem.entity.PlaceReservation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceReservationDAOImpl implements PlaceReservationDAO {
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
    public ArrayList<PlaceReservation> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(PlaceReservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PlaceReservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PlaceReservation search(Object... args) throws SQLException, ClassNotFoundException {
        return null;
    }
}
