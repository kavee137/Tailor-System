package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.ViewOrderBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.ViewOrderDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewOrderBOImpl implements ViewOrderBO {

    ViewOrderDAO viewOrderDAO = (ViewOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VIEWORDER);

    @Override
    public ResultSet getOrderDetails(Date dateFrom, Date dateTo) throws SQLException, ClassNotFoundException {
        return viewOrderDAO.getOrderDetails(dateFrom, dateTo);
    }

    @Override
    public ResultSet getDetails() throws SQLException, ClassNotFoundException {
        return viewOrderDAO.getDetails();
    }


    @Override
    public ResultSet getProcessingOrderDetails() throws SQLException, ClassNotFoundException {
        return viewOrderDAO.getProcessingOrderDetails();
    }

    @Override
    public ResultSet getEmployeeTasks(String tailorId) throws SQLException, ClassNotFoundException {
        return viewOrderDAO.getEmployeeTasks(tailorId);
    }


    @Override
    public ResultSet getCompleted() throws SQLException, ClassNotFoundException {
        return viewOrderDAO.getCompleted();
    }

}
