package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order> {
    String getOrderCount() throws SQLException, ClassNotFoundException;
}
