package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.*;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.entity.Fabric;
import lk.ijse.tailorsystem.entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderDAOImpl implements SuperBO{

    FabricDAO fabricDAO = (FabricDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FABRIC);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);


    public boolean placeOrder(PlaceOrder po, String paymentType, double netTotal) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);


        try {
            boolean isMakePayment = paymentDAO.newPayment(po.getOrder().getPaymentID(), netTotal, paymentType);
            System.out.println("isMakePayment");
            if (isMakePayment) {
                boolean isOrderSaved = orderDAO.add(po.getOrder());
                System.out.println("isOrderSaved");
                if (isOrderSaved) {
                    boolean isQtyUpdated = fabricDAO.odQtyUpdate(po.getOdList());
                    System.out.println("isQtyUpdated");
                    System.out.println(isQtyUpdated);
                    if (isQtyUpdated) {
                        boolean isOrderDetailSaved = orderDetailsDAO.save(po.getOdList());
                        System.out.println("isOrderDetailSaved");
                        if (isOrderDetailSaved) {
                            System.out.println("commit");
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
