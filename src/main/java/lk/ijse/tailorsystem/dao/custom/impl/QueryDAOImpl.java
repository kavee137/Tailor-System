package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.QueryDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {



    @Override
    public ResultSet getReservationCompleted() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT r.reservationID, c.customerID, c.NIC, c.customerName, c.customerAddress, c.customerTel, r.paymentID, r.reservationDate, r.returnDate, r.status AS reservationStatus, rd.productID, rd.qty AS quantity, rd.total AS totalAmount, p.paymentType, p.price AS paymentPrice FROM reservation AS r JOIN customer AS c ON r.customerID = c.customerID JOIN reservationDetails AS rd ON r.reservationID = rd.reservationID JOIN payment AS p ON r.paymentID = p.paymentID WHERE r.status = 'Completed'");
    }

    @Override
    public ResultSet loadAll() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT r.reservationID, c.customerID, c.NIC, c.customerName, c.customerAddress, c.customerTel, r.paymentID, r.reservationDate, r.returnDate, r.status AS reservationStatus, rd.productID, rd.qty AS quantity, rd.total AS totalAmount, p.paymentType, p.price AS paymentPrice FROM reservation AS r JOIN customer AS c ON r.customerID = c.customerID JOIN reservationDetails AS rd ON r.reservationID = rd.reservationID JOIN payment AS p ON r.paymentID = p.paymentID");
    }

    @Override
    public ResultSet getIncompleteReservationDetails() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT r.reservationID, c.customerID, c.NIC, c.customerName, c.customerAddress, c.customerTel, r.paymentID, r.reservationDate, r.returnDate, r.status AS reservationStatus, rd.productID, rd.qty AS quantity, rd.total AS totalAmount, p.paymentType, p.price AS paymentPrice FROM reservation AS r JOIN customer AS c ON r.customerID = c.customerID JOIN reservationDetails AS rd ON r.reservationID = rd.reservationID JOIN payment AS p ON r.paymentID = p.paymentID WHERE r.status = 'Incomplete'");
    }

    @Override
    public ResultSet getReservationDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT r.reservationID, c.customerID, c.NIC, c.customerName, c.customerAddress, c.customerTel, r.paymentID, r.reservationDate, r.returnDate, r.status AS reservationStatus, rd.productID, rd.qty AS quantity, rd.total AS totalAmount, p.paymentType, p.price AS paymentPrice FROM reservation AS r JOIN customer AS c ON r.customerID = c.customerID JOIN reservationDetails AS rd ON r.reservationID = rd.reservationID JOIN payment AS p ON r.paymentID = p.paymentID WHERE r.returnDate BETWEEN ? AND ?", dateFrom, dateTo);
    }

    @Override
    public ResultSet getCompleted() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID WHERE o.status = 'completed'");
    }

    @Override
    public ResultSet getEmployeeTasks(String tailorId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID WHERE o.employeeID = ? AND o.status = 'Processing'", tailorId);
    }

    @Override
    public ResultSet getProcessingOrderDetails() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID WHERE o.status = 'Processing'");
    }

    @Override
    public ResultSet getDetails() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID");
    }


    @Override
    public ResultSet getOrderDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID WHERE o.returnDate BETWEEN ? AND ?", dateFrom, dateTo);
    }

    @Override
    public ResultSet getReservationDetailsTable(int id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT rd.productID,  p.productName,  p.productColor, p.unitPrice, rd.qty FROM reservation r JOIN reservationDetails rd ON r.reservationID = rd.reservationID JOIN product p ON rd.productID = p.productID WHERE rd.reservationID = ?", id);
    }

    @Override
    public ResultSet getDetailsForPaymentTm() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT r.reservationID, o.orderID, p.paymentID AS payment_paymentID, p.paymentType, p.price FROM reservation r LEFT JOIN orders o ON r.customerID = o.customerID LEFT JOIN payment p ON r.paymentID = p.paymentID");
    }

    @Override
    public ResultSet getReservationJoinTable(int id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT r.customerID, c.customerName, r.paymentID, p.paymentType, p.price, r.reservationDate, r.returnDate, r.status AS reservationStatus, c.NIC FROM reservation r JOIN payment p ON r.paymentID = p.paymentID JOIN customer c ON r.customerID = c.customerID WHERE reservationID = ?", id);
    }

    @Override
    public ResultSet getMonthlySalesFor2024() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT MONTH(o.orderDate) AS order_month, SUM(od.total) AS total_sales " +
                "FROM orders o " +
                "JOIN orderDetails od ON o.orderID = od.orderID " +
                "WHERE YEAR(o.orderDate) = 2024 " +
                "GROUP BY MONTH(o.orderDate)"
        );
    }

    @Override
    public ResultSet getMostReservedProduct(String productName) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT p.productID, p.productName, p.productColor, p.productSize, p.qtyOnHand " +
                        "FROM product p INNER JOIN " +
                        "(SELECT productID, COUNT(*) AS reservationCount FROM reservationDetails " +
                        "WHERE productID IN (SELECT productID FROM product WHERE productName = ?) " +
                        "GROUP BY productID ORDER BY reservationCount DESC LIMIT 1) AS r ON p.productID = r.productID",
                productName
        );
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

}
