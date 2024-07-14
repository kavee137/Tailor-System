package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.PlaceOrderBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.*;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.dto.OrderDTO;
import lk.ijse.tailorsystem.dto.OrderDetailsDTO;
import lk.ijse.tailorsystem.dto.PlaceOrderDTO;
import lk.ijse.tailorsystem.entity.Order;
import lk.ijse.tailorsystem.entity.OrderDetails;
import lk.ijse.tailorsystem.entity.PlaceOrder;
import lk.ijse.tailorsystem.view.tdm.CartTm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    FabricDAO fabricDAO = (FabricDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FABRIC);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public boolean add(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDAO.add(new Order(order.getOrderID(), order.getCustomerID(), order.getPaymentID(), order.getEmployeeID(), order.getOrderDate(), order.getReturnDate(), order.getStatus()));
    }

    @Override
    public String getOrderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderCount();
    }

    @Override
    public boolean update(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(order.getOrderID(), order.getCustomerID(), order.getPaymentID(), order.getEmployeeID(), order.getOrderDate(), order.getReturnDate(), order.getStatus()));
    }

    @Override
    public ResultSet getOrderCartTable(String orderId) throws SQLException, ClassNotFoundException {
        return queryDAO.getOrderCartTable(orderId);
    }

    @Override
    public List<String> getOrderDetails(String id) throws SQLException, ClassNotFoundException {
        return queryDAO.getOrderDetails(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }



    @Override
    public boolean placeOrder(PlaceOrderDTO po, String paymentType, double netTotal) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Order order = new Order(po.getOrder().getOrderID(), po.getOrder().getCustomerID(), po.getOrder().getPaymentID(), po.getOrder().getEmployeeID(), po.getOrder().getOrderDate(), po.getOrder().getReturnDate(), po.getOrder().getStatus());
        List<OrderDetails> odList = new ArrayList<>();

        for (int i = 0; i < po.getOdList().size(); i++) {
            OrderDetailsDTO tm = po.getOdList().get(i);

            OrderDetails od = new OrderDetails(
                    tm.getOrderID(),
                    tm.getFabricID(),
                    tm.getDescription(),
                    tm.getMeasurements(),
                    tm.getFabricSize(),
                    tm.getUnitPrice(),
                    tm.getQty(),
                    tm.getTotal()
            );
            odList.add(od);
        }

        try {
            boolean isMakePayment = paymentDAO.newPayment(po.getOrder().getPaymentID(), netTotal, paymentType);
            if (isMakePayment) {
                boolean isOrderSaved = orderDAO.add(order);
                if (isOrderSaved) {
                    boolean isQtyUpdated = fabricDAO.odQtyUpdate(odList);
                    if (isQtyUpdated) {
                        boolean isOrderDetailSaved = orderDetailsDAO.save(odList);
                        if (isOrderDetailSaved) {
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
