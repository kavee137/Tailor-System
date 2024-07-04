package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dao.SuperDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ViewOrderBO extends SuperBO {


    ResultSet getOrderDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException;

    ResultSet getDetails() throws SQLException, ClassNotFoundException;

    ResultSet getProcessingOrderDetails() throws SQLException, ClassNotFoundException;

    ResultSet getEmployeeTasks(String tailorId) throws SQLException, ClassNotFoundException;

    ResultSet getCompleted() throws SQLException, ClassNotFoundException;
}
