package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dto.CustomerDTO;
import lk.ijse.tailorsystem.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String nic) throws SQLException, ClassNotFoundException;

    public String generateNewCustomerID() throws SQLException, ClassNotFoundException;

    public Customer customerSearch(String nic) throws SQLException, ClassNotFoundException;
}
