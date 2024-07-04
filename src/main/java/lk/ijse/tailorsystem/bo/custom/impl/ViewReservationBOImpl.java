package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.ViewReservationBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.ViewReservationDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewReservationBOImpl implements ViewReservationBO {

    ViewReservationDAO viewReservationDAO = (ViewReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VIEWRESERVATION);

    @Override
    public ResultSet getReservationDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException {
        return viewReservationDAO.getReservationDetails(dateFrom, dateTo);
    }

    @Override
    public ResultSet getIncompleteReservationDetails() throws SQLException, ClassNotFoundException {
        return viewReservationDAO.getIncompleteReservationDetails();
    }

    @Override
    public ResultSet loadAll() throws SQLException, ClassNotFoundException {
        return viewReservationDAO.loadAll();
    }

    @Override
    public ResultSet getCompleted() throws SQLException, ClassNotFoundException {
        return viewReservationDAO.getCompleted();
    }

}
