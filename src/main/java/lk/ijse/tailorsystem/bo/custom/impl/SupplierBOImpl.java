package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.bo.custom.SupplierBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.CustomerDAO;
import lk.ijse.tailorsystem.dao.custom.SupplierDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.dto.SupplierDTO;
import lk.ijse.tailorsystem.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public String getTotalSuppliers() throws SQLException, ClassNotFoundException {
        return supplierDAO.getTotalSuppliers();
    }

    @Override
    public List<String> getSuppliereIds() throws SQLException, ClassNotFoundException {
        return supplierDAO.getSuppliereIds();
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(nic);
    }

    @Override
    public boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(supplier);
    }

    @Override
    public boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplier.getSupplierID(), supplier.getNIC(), supplier.getSupplierName(), supplier.getSupplierAddress(), supplier.getSupplierContact(), supplier.getStatus()));
    }


    @Override
    public Supplier search(String nic) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(nic);
    }

    @Override
    public String  generateNewID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
