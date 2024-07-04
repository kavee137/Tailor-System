package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.ViewOrderDAO;

import java.sql.*;
import java.util.ArrayList;

public class ViewOrderDAOImpl implements ViewOrderDAO {


    @Override
    public ResultSet getOrderDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID WHERE o.returnDate BETWEEN ? AND ?", dateFrom, dateTo);
    }

    @Override
    public ResultSet getDetails() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID");
    }


    @Override
    public ResultSet getProcessingOrderDetails() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID WHERE o.status = 'Processing'");
    }

    @Override
    public ResultSet getEmployeeTasks(String tailorId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID WHERE o.employeeID = ? AND o.status = 'Processing'", tailorId);
    }


    @Override
    public ResultSet getCompleted() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID WHERE o.status = 'completed'");
    }


    @Override
    public ArrayList<ViewOrderDAO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(ViewOrderDAO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ViewOrderDAO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ViewOrderDAO search(Object... args) throws SQLException, ClassNotFoundException {
        return null;
    }


}
