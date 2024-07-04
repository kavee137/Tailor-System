package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.OrderBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.OrderDAO;
import lk.ijse.tailorsystem.dto.OrderDTO;
import lk.ijse.tailorsystem.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

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
        return orderDAO.getOrderCartTable(orderId);
    }

    @Override
    public List<String> getOrderDetails(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderDetails(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

}
