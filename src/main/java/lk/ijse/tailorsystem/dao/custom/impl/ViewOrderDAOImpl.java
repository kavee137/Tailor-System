package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.QueryDAO;
import lk.ijse.tailorsystem.dao.custom.ViewOrderDAO;

import java.sql.*;
import java.util.ArrayList;

public class ViewOrderDAOImpl implements ViewOrderDAO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public ResultSet getOrderDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException {
        return queryDAO.getOrderDetails(dateFrom, dateTo);
    }

    @Override
    public ResultSet getDetails() throws SQLException, ClassNotFoundException {
        return queryDAO.getDetails();
    }


    @Override
    public ResultSet getProcessingOrderDetails() throws SQLException, ClassNotFoundException {
        return queryDAO.getProcessingOrderDetails();
    }

    @Override
    public ResultSet getEmployeeTasks(String tailorId) throws SQLException, ClassNotFoundException {
        return queryDAO.getEmployeeTasks(tailorId);
    }


    @Override
    public ResultSet getCompleted() throws SQLException, ClassNotFoundException {
        return queryDAO.getCompleted();
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
