package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.QueryDAO;
import lk.ijse.tailorsystem.dao.custom.ViewReservationDAO;
import lk.ijse.tailorsystem.db.DbConnection;

import java.sql.*;
import java.util.ArrayList;

public class ViewReservationDAOImpl implements ViewReservationDAO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public ResultSet getReservationDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException {
        return queryDAO.getReservationDetails(dateFrom, dateTo);
    }

    @Override
    public ResultSet getIncompleteReservationDetails() throws SQLException, ClassNotFoundException {
        return queryDAO.getIncompleteReservationDetails();
    }

    @Override
    public ResultSet loadAll() throws SQLException, ClassNotFoundException {
        return queryDAO.loadAll();
    }

    @Override
    public ResultSet getCompleted() throws SQLException, ClassNotFoundException {
        return queryDAO.getReservationCompleted();
    }














    @Override
    public ArrayList<ViewReservationDAO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(ViewReservationDAO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ViewReservationDAO entity) throws SQLException, ClassNotFoundException {
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
    public ViewReservationDAO search(Object... args) throws SQLException, ClassNotFoundException {
        return null;
    }
}
