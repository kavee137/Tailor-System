package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dto.OrderDTO;
import lk.ijse.tailorsystem.entity.PlaceOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    boolean add(OrderDTO order) throws SQLException, ClassNotFoundException;

    String getOrderCount() throws SQLException, ClassNotFoundException;

    boolean update(OrderDTO order) throws SQLException, ClassNotFoundException;

    ResultSet getOrderCartTable(String orderId) throws SQLException, ClassNotFoundException;

    List<String> getOrderDetails(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean placeOrder(PlaceOrder po, String paymentType, double netTotal) throws SQLException;
}
