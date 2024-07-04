package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.CustomerBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.CustomerDAO;
import lk.ijse.tailorsystem.dto.CustomerDTO;
import lk.ijse.tailorsystem.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();

        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getCustomerID(),c.getNIC(),c.getCustomerName(), c.getCustomerAddress(), c.getCustomerTel(), c.getUserID(), c.getStatus()));
        }
        return allCustomers;
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getCustomerID(), dto.getNIC(), dto.getCustomerName(), dto.getCustomerAddress(), dto.getCustomerTel(), dto.getUserID(), dto.getStatus()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCustomerID(), dto.getNIC(), dto.getCustomerName(), dto.getCustomerAddress(), dto.getCustomerTel(), dto.getUserID(), dto.getStatus()));
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String nic) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(nic);
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {

        return customerDAO.generateNewID();
    }

    @Override
    public Customer customerSearch(String nic) throws SQLException, ClassNotFoundException {
        return customerDAO.search(nic);
    }
}
