package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.OrderDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {



    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?, ?, ?, ?, ?)", order.getOrderID(), order.getCustomerID(), order.getPaymentID(), order.getEmployeeID(), Date.valueOf(String.valueOf(order.getOrderDate())), Date.valueOf(String.valueOf(order.getReturnDate())), order.getStatus());
    }

    @Override
    public String getOrderCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS processingOrderCount FROM orders WHERE status = 'Processing'");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE orders SET status = ? WHERE orderID = ?", order.getStatus(), order.getOrderID());
    }

    @Override
    public ResultSet getOrderCartTable(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT od.description, f.fabricName, f.fabricColor, od.measurements, od.fabricSize, od.unitPrice, od.qty FROM orders o JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID WHERE o.orderID = ?", orderId);
    }

    @Override
    public List<String> getOrderDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT o.orderDate, o.returnDate, o.employeeID, o.status, o.paymentID, c.NIC, c.customerName, c.customerID, e.employeeName, p.paymentType, p.price FROM orders o JOIN customer c ON o.customerID = c.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN payment p ON o.paymentID = p.paymentID WHERE o.orderID = ?", id);

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
            rowData.add(resultSet.getString(10));
            rowData.add(resultSet.getString(11));
        }
        return rowData;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT orderID FROM orders ORDER BY orderID DESC LIMIT 1");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }










    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(Object... args) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
