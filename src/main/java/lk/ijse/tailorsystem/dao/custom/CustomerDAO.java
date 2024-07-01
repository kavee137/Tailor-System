package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    public String getCustomerCount() throws SQLException, ClassNotFoundException;
}
