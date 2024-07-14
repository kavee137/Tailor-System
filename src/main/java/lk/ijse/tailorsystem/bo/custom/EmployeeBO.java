package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dto.EmployeeDTO;
import lk.ijse.tailorsystem.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {


    String getEmployeeCount() throws SQLException, ClassNotFoundException;

    List<String> getTailorId() throws SQLException, ClassNotFoundException;

    Employee search(Object... nic) throws SQLException, ClassNotFoundException;

    boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    boolean delete(String nic) throws SQLException, ClassNotFoundException;

    boolean add(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

    List<String> getPosition() throws SQLException, ClassNotFoundException;

    String searchByPosition(String name) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException;
}
