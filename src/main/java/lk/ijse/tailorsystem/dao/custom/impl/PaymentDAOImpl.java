package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
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
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT paymentID FROM payment ORDER BY paymentID DESC LIMIT 1");

        if(resultSet.next()) {
            return String.valueOf(resultSet.getInt(1));
        }
        return String.valueOf(1);
    }


    @Override
    public String getTotalPayment() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(price) AS totalPayment FROM payment");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean newPayment(int paymentId, double netTotal, String paymentType) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES  (?, ? ,?)", paymentId, netTotal, paymentType);
    }

}
