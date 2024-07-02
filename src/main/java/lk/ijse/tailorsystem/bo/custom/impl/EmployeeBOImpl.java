package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.EmployeeBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.CustomerDAO;
import lk.ijse.tailorsystem.dao.custom.EmployeeDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public String getEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeCount();
    }

    @Override
    public List<String> getTailorId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getTailorId();
    }

    @Override
    public Employee search(Object... nic) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(nic);
    }


    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(employee);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(nic);
    }

    @Override
    public boolean add(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(employee);
    }

    @Override
    public List<String> getPosition() throws SQLException, ClassNotFoundException {
        return employeeDAO.getPosition();
    }

    @Override
    public String searchByPosition(String name) throws SQLException, ClassNotFoundException {
        return employeeDAO.searchByPosition(name);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

}
