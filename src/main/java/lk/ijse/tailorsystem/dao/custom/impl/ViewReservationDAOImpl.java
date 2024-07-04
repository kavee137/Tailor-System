package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.ViewReservationDAO;
import lk.ijse.tailorsystem.db.DbConnection;

import java.sql.*;
import java.util.ArrayList;

public class ViewReservationDAOImpl implements ViewReservationDAO {

    @Override
    public ResultSet getReservationDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT r.reservationID, c.customerID, c.NIC, c.customerName, c.customerAddress, c.customerTel, r.paymentID, r.reservationDate, r.returnDate, r.status AS reservationStatus, rd.productID, rd.qty AS quantity, rd.total AS totalAmount, p.paymentType, p.price AS paymentPrice FROM reservation AS r JOIN customer AS c ON r.customerID = c.customerID JOIN reservationDetails AS rd ON r.reservationID = rd.reservationID JOIN payment AS p ON r.paymentID = p.paymentID WHERE r.returnDate BETWEEN ? AND ?";

        return SQLUtil.execute(sql, dateFrom, dateTo);
    }

    @Override
    public ResultSet getIncompleteReservationDetails() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT r.reservationID, c.customerID, c.NIC, c.customerName, c.customerAddress, c.customerTel, r.paymentID, r.reservationDate, r.returnDate, r.status AS reservationStatus, rd.productID, rd.qty AS quantity, rd.total AS totalAmount, p.paymentType, p.price AS paymentPrice FROM reservation AS r JOIN customer AS c ON r.customerID = c.customerID JOIN reservationDetails AS rd ON r.reservationID = rd.reservationID JOIN payment AS p ON r.paymentID = p.paymentID WHERE r.status = 'Incomplete'");
    }

    @Override
    public ResultSet loadAll() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT r.reservationID, c.customerID, c.NIC, c.customerName, c.customerAddress, c.customerTel, r.paymentID, r.reservationDate, r.returnDate, r.status AS reservationStatus, rd.productID, rd.qty AS quantity, rd.total AS totalAmount, p.paymentType, p.price AS paymentPrice FROM reservation AS r JOIN customer AS c ON r.customerID = c.customerID JOIN reservationDetails AS rd ON r.reservationID = rd.reservationID JOIN payment AS p ON r.paymentID = p.paymentID");
    }

    @Override
    public ResultSet getCompleted() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT r.reservationID, c.customerID, c.NIC, c.customerName, c.customerAddress, c.customerTel, r.paymentID, r.reservationDate, r.returnDate, r.status AS reservationStatus, rd.productID, rd.qty AS quantity, rd.total AS totalAmount, p.paymentType, p.price AS paymentPrice FROM reservation AS r JOIN customer AS c ON r.customerID = c.customerID JOIN reservationDetails AS rd ON r.reservationID = rd.reservationID JOIN payment AS p ON r.paymentID = p.paymentID WHERE r.status = 'Completed'");
    }














    @Override
    public ArrayList<ViewReservationDAO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(ViewReservationDAO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ViewReservationDAO entity) throws SQLException, ClassNotFoundException {
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
    public ViewReservationDAO search(Object... args) throws SQLException, ClassNotFoundException {
        return null;
    }
}
