package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    public String getCustomerCount() throws SQLException, ClassNotFoundException;

    List<String> customerSearch(String cusNic) throws SQLException, ClassNotFoundException;
}
