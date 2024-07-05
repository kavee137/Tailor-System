package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.entity.Supplier;
import lk.ijse.tailorsystem.dao.custom.SupplierDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {


    @Override
    public String getTotalSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS allSupplier FROM supplier WHERE status = 'Active'");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public List<String> getSuppliereIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplierID FROM supplier WHERE status = ?", "Active");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");

        ArrayList<Supplier> supList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String nic = resultSet.getString(2);
            String name = resultSet.getString(3);
            String address = resultSet.getString(4);
            String tel = resultSet.getString(5);
            String status = resultSet.getString(6);

            supList.add(new Supplier(id, nic, name, address, tel, status));
        }
        return supList;
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET status = 'Inactive' WHERE NIC = ?", nic);
    }

    @Override
    public boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?, ?, ?)", supplier.getSupplierID(), supplier.getNIC(), supplier.getSupplierName(), supplier.getSupplierAddress(), supplier.getSupplierContact(), supplier.getStatus());
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET supplierID = ?, NIC = ?, supplierName = ?, supplierAddress = ?, supplierContact = ?, status = ? WHERE NIC = ?", supplier.getSupplierID(), supplier.getNIC(), supplier.getSupplierName(), supplier.getSupplierAddress(), supplier.getSupplierContact(), supplier.getStatus(), supplier.getNIC());
    }


    @Override
    public Supplier search(Object... nic) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplierID, NIC, supplierName, supplierAddress, supplierContact, status FROM supplier WHERE NIC = ?", nic);

        if(resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String NIC = resultSet.getString(2);
            String supplierName = resultSet.getString(3);
            String supplierAddress = resultSet.getString(4);
            String supplierContact = resultSet.getString(5);
            String status = resultSet.getString(6);

            return new Supplier(supplierId, NIC, supplierName, supplierAddress, supplierContact, status);
        }
        return null;
    }

    @Override
    public String  generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplierID FROM supplier ORDER BY supplierID DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
