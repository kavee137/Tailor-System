package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.SuperDAO;
import lk.ijse.tailorsystem.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends SuperDAO {
    boolean save(List<OrderDetails> odList) throws SQLException, ClassNotFoundException;
}
