package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.custom.PaymentDAO;
import lk.ijse.tailorsystem.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {


    @Override
    public ArrayList<PaymentDAO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(PaymentDAO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PaymentDAO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PaymentDAO search(Object... args) throws SQLException, ClassNotFoundException {
        return null;
    }






    @Override
    public String generateNewID() throws SQLException {
        String sql = "SELECT paymentID FROM payment ORDER BY paymentID DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return String.valueOf(resultSet.getInt(1));
        }
        return String.valueOf(1);
    }


    @Override
    public String getTotalPayment() throws SQLException {
        String sql = "SELECT SUM(price) AS totalPayment FROM payment";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String count = resultSet.getString(1);
            return count;
        }
        return null;
    }

    @Override
    public boolean newPayment(int paymentId, double netTotal, String paymentType) throws SQLException {
        String sql = "INSERT INTO payment VALUES  (?, ? ,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, paymentId);
        pstm.setString(2, paymentType);
        pstm.setDouble(3, netTotal);

        return pstm.executeUpdate() > 0;

    }

}
