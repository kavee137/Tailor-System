package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ViewReservationBO extends SuperBO {


    ResultSet getReservationDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException;

    ResultSet getIncompleteReservationDetails() throws SQLException, ClassNotFoundException;

    ResultSet loadAll() throws SQLException, ClassNotFoundException;

    ResultSet getCompleted() throws SQLException, ClassNotFoundException;
}
