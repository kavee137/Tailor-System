package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {
    String getTotalSuppliers() throws SQLException, ClassNotFoundException;

    List<String> getSuppliereIds() throws SQLException, ClassNotFoundException;
}
