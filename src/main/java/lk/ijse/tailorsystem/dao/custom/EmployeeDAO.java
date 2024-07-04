package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {

    String getEmployeeCount() throws SQLException, ClassNotFoundException;

    List<String> getTailorId() throws SQLException, ClassNotFoundException;

    List<String> getPosition() throws SQLException, ClassNotFoundException;

    String searchByPosition(String name) throws SQLException, ClassNotFoundException;

    String getEmployeeName(String id) throws SQLException, ClassNotFoundException;
}
