package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.OrderDetailsDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.entity.OrderDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean save(List<OrderDetails> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetails od : odList) {
            boolean isSaved = save(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(OrderDetails od) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderDetails VALUES(?, ?, ?, ?, ?, ?, ?, ?)", od.getOrderID(), od.getFabricID(), od.getDescription(), od.getMeasurements(), od.getFabricSize(), od.getUnitPrice(), od.getQty(), od.getTotal());    //false ->  |
    }
}
