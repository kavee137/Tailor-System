package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.EmployeeDAO;
import lk.ijse.tailorsystem.entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public String getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS activeEmployeeCount FROM employee WHERE status = 'Active'");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public List<String> getTailorId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT employeeId FROM employee WHERE position = 'Tailor'");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public Employee search(Object... nic) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT employeeID, userID, NIC, position, employeeName, phoneNumber, employeeAddress, salary, status FROM employee WHERE NIC = ?", nic);

        if(resultSet.next()) {
            String employeeId = resultSet.getString(1);
            String userId = resultSet.getString(2);
            String NIC = resultSet.getString(3);
            String position = resultSet.getString(4);
            String name = resultSet.getString(5);
            String tel = resultSet.getString(6);
            String address = resultSet.getString(7);
            String salary = resultSet.getString(8);
            String status = resultSet.getString(9);

            return new Employee(employeeId, userId, NIC, position, name, tel, address, salary, status);
        }
        return null;
    }


    @Override
    public boolean update(Employee emp) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employee SET employeeID = ?, userID = ?, NIC = ?, position = ?, employeeName = ?, phoneNumber = ?, employeeAddress = ?, salary = ?, status = ? WHERE NIC = ?";
        return SQLUtil.execute(sql, emp.getEmployeeID(), emp.getUserID(), emp.getNIC(), emp.getPosition(), emp.getEmployeeName(), emp.getPhoneNumber(), emp.getEmployeeAddress(), emp.getSalary(), emp.getStatus(), emp.getNIC());
    }

    @Override
    public boolean exist(String id) {
        return false;
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET status = 'Inactive' WHERE NIC = ?", nic);
    }

    @Override
    public boolean add(Employee emp) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, emp.getEmployeeID(), emp.getUserID(), emp.getNIC(), emp.getPosition(), emp.getEmployeeName(), emp.getPhoneNumber(), emp.getEmployeeAddress(), emp.getSalary(), emp.getStatus());
    }

    @Override
    public List<String> getPosition() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT position FROM employee");

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    @Override
    public String searchByPosition(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT salary FROM employee WHERE position = ?", name);
        if (resultSet.next()) {
                return resultSet.getString(1);
            }
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT employeeID FROM employee ORDER BY employeeID DESC LIMIT 1");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");

        ArrayList<Employee> empList = new ArrayList<>();
        while (resultSet.next()) {
            String employeeID = resultSet.getString(1);
            String userID = resultSet.getString(2);
            String NIC = resultSet.getString(3);
            String position = resultSet.getString(4);
            String employeeName = resultSet.getString(5);
            String phoneNumber = resultSet.getString(6);
            String employeeAddress = resultSet.getString(7);
            String salary = resultSet.getString(8);
            String status = resultSet.getString(9);

            Employee employee = new Employee(employeeID, userID, NIC, position, employeeName, phoneNumber, employeeAddress, salary, status);
            empList.add(employee);
        }
        return empList;
    }
}
