package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<PaymentDAO> {


//    int generateNewID() throws SQLException;

    String getTotalPayment() throws SQLException, ClassNotFoundException;

    boolean newPayment(int paymentId, double netTotal, String paymentType) throws SQLException, ClassNotFoundException;
}
