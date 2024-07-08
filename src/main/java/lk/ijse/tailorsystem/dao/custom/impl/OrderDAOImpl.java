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
