package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dto.SupplierDTO;
import lk.ijse.tailorsystem.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    String getTotalSuppliers() throws SQLException, ClassNotFoundException;

    List<String> getSuppliereIds() throws SQLException, ClassNotFoundException;

    ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String nic) throws SQLException, ClassNotFoundException;

    boolean add(Supplier supplier) throws SQLException, ClassNotFoundException;

    boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    Supplier search(String nic) throws SQLException, ClassNotFoundException;

    String  generateNewID() throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;


}
