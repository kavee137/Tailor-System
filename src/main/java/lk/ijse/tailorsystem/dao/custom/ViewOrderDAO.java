package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ViewOrderDAO extends CrudDAO<ViewOrderDAO> {

    ResultSet getOrderDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException;

    ResultSet getDetails() throws SQLException, ClassNotFoundException;

    ResultSet getProcessingOrderDetails() throws SQLException, ClassNotFoundException;

    ResultSet getEmployeeTasks(String tailorId) throws SQLException, ClassNotFoundException;

    ResultSet getCompleted() throws SQLException, ClassNotFoundException;
}
