package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.SuperDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    ResultSet getReservationCompleted() throws SQLException, ClassNotFoundException;

    ResultSet loadAll() throws SQLException, ClassNotFoundException;

    ResultSet getIncompleteReservationDetails() throws SQLException, ClassNotFoundException;

    ResultSet getReservationDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException;

    ResultSet getCompleted() throws SQLException, ClassNotFoundException;

    ResultSet getEmployeeTasks(String tailorId) throws SQLException, ClassNotFoundException;

    ResultSet getProcessingOrderDetails() throws SQLException, ClassNotFoundException;

    ResultSet getDetails() throws SQLException, ClassNotFoundException;

    ResultSet getOrderDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException;

    ResultSet getReservationDetailsTable(int id) throws SQLException, ClassNotFoundException;

    ResultSet getDetailsForPaymentTm() throws SQLException, ClassNotFoundException;

    ResultSet getReservationJoinTable(int id) throws SQLException, ClassNotFoundException;

    ResultSet getMonthlySalesFor2024() throws SQLException, ClassNotFoundException;

    ResultSet getMostReservedProduct(String productName) throws SQLException, ClassNotFoundException;

    ResultSet getOrderCartTable(String orderId) throws SQLException, ClassNotFoundException;

    List<String> getOrderDetails(String id) throws SQLException, ClassNotFoundException;
}
