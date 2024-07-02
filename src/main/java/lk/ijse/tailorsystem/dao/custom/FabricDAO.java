package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.Fabric;
import lk.ijse.tailorsystem.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface FabricDAO extends CrudDAO<Fabric> {
    List<String> getFabricName() throws SQLException, ClassNotFoundException;

    boolean odQtyUpdate(List<OrderDetails> odList) throws SQLException, ClassNotFoundException;

    List<String> getColorsForFabric(String fabricName) throws SQLException, ClassNotFoundException;
}
