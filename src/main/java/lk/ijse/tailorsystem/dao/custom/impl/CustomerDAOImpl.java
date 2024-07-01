
package lk.ijse.tailorsystem.dao.custom.impl;

import javafx.fxml.FXML;
import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.CustomerDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public String getCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS activeCustomerCount FROM customer WHERE status = 'Active'");
        if(resultSet.next()) {
            String count = resultSet.getString(1);
            return count;
        }
        return null;
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET status = 'Inactive' WHERE NIC = ?", nic);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET customerID = ?, NIC = ?, customerName = ?, customerAddress = ?, customerTel = ?, status = ? WHERE NIC = ?", customer.getCustomerID(), customer.getNIC(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerTel(), customer.getStatus(), customer.getNIC());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(String nic) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT customerID, customerName, customerAddress, customerTel, status FROM customer WHERE NIC = ?", nic);

        if(resultSet.next()) {
            String customerId = resultSet.getString(1);
            String customerName = resultSet.getString(2);
            String customerAddress = resultSet.getString(3);
            String customerTel = resultSet.getString(4);
            String status = resultSet.getString(5);

            Customer customer = new Customer(customerId, customerName, customerAddress, customerTel, status);

            return customer;
        }
        return null;
    }

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?, ?)", customer.getCustomerID(), customer.getNIC(), customer.getCustomerName(), customer.getCustomerAddress(),customer.getCustomerTel(), customer.getUserID(), customer.getStatus());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT customerID FROM customer ORDER BY customerID DESC LIMIT 1");

        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> cusList = new ArrayList<>();
        while (resultSet.next()) {
            String customerID = resultSet.getString(1);
            String NIC = resultSet.getString(2);
            String customerName = resultSet.getString(3);
            String customerAddress = resultSet.getString(4);
            String customerTel = resultSet.getString(5);
            String userID = resultSet.getString(6);
            String status = resultSet.getString(7);

            Customer customer = new Customer(customerID, NIC, customerName, customerAddress, customerTel, userID, status );
            cusList.add(customer);
        }
        return cusList;
    }
}