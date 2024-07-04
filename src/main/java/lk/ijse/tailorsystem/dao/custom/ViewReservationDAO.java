package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ViewReservationDAO extends CrudDAO<ViewReservationDAO> {


    ResultSet getReservationDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException;

    ResultSet getIncompleteReservationDetails() throws SQLException, ClassNotFoundException;

    ResultSet loadAll() throws SQLException, ClassNotFoundException;

    ResultSet getCompleted() throws SQLException, ClassNotFoundException;
}
