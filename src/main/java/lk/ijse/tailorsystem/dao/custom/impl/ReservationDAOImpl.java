package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.ReservationDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.entity.Reservation;
import lk.ijse.tailorsystem.view.tdm.PaymentTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {


    @Override
    public String getReservationCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS incompleteReservationCount FROM reservation WHERE status = 'Incomplete'");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean update(Reservation resId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE reservation SET status = 'Completed' WHERE reservationID = ?", resId.getReservationID());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public ArrayList<String> getReservationJoinTable(int id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT r.customerID, c.customerName, r.paymentID, p.paymentType, p.price, r.reservationDate, r.returnDate, r.status AS reservationStatus, c.NIC FROM reservation r JOIN payment p ON r.paymentID = p.paymentID JOIN customer c ON r.customerID = c.customerID WHERE reservationID = ?", id);

        ArrayList<String> rowData = new ArrayList<>();
        if (resultSet.next()) {
            rowData.add(resultSet.getString(1));
            rowData.add(resultSet.getString(2));
            rowData.add(resultSet.getString(3));
            rowData.add(resultSet.getString(4));
            rowData.add(resultSet.getString(5));
            rowData.add(resultSet.getString(6));
            rowData.add(resultSet.getString(7));
            rowData.add(resultSet.getString(8));
            rowData.add(resultSet.getString(9));
        }
        return rowData;
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Reservation r) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservation VALUES(?, ?, ?, ?, ? ,?)", r.getReservationID(), r.getCustomerID(), r.getPaymentID(), r.getReservationDate(), r.getReturnDate(), r.getStatus());
    }

    @Override
    public List<PaymentTm> getDetails() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT r.reservationID, o.orderID, p.paymentID AS payment_paymentID, p.paymentType, p.price FROM reservation r LEFT JOIN orders o ON r.customerID = o.customerID LEFT JOIN payment p ON r.paymentID = p.paymentID");

        List<PaymentTm> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            int resId = Integer.parseInt(resultSet.getString(1));
            String orderId = resultSet.getString(2);
            int paymentId = Integer.parseInt(resultSet.getString(3));
            String payementType = resultSet.getString(4);
            double price = Double.parseDouble(resultSet.getString(5));

            paymentList.add(new PaymentTm(resId, orderId, paymentId, payementType, price));
        }
        return paymentList;
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT reservationID FROM reservation ORDER BY reservationID DESC LIMIT 1");

        if(resultSet.next()) {
            return String.valueOf(resultSet.getInt(1));
        }
        return String.valueOf(1);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Reservation search(Object... args) throws SQLException, ClassNotFoundException {
        return null;
    }
}
