package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.FabricDAO;
import lk.ijse.tailorsystem.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FabricDAOImpl implements FabricDAO {


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM fabric WHERE fabricID = ?", id);
    }


    @Override
    public boolean update(Fabric f) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE fabric SET supplierID = ?, fabricName = ?, fabricColor = ?, fabricQtyOnHand = ? WHERE fabricID = ?", f.getSupplierID(), f.getFabricName(), f.getFabricColor(), f.getFabricQtyOnHand(), f.getFabricID());
    }


    @Override
    public boolean add(Fabric f) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO fabric VALUES(?, ?, ?, ?, ?)", f.getFabricID(), f.getSupplierID(), f.getFabricName(), f.getFabricColor(), f.getFabricQtyOnHand());
    }

    @Override
    public Fabric search(Object... args) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT fabricID, supplierID, fabricName, fabricColor, fabricQtyOnHand FROM fabric WHERE fabricName = ? AND fabricColor = ?", args);

        if (resultSet.next()) {
            String  fabricID = resultSet.getString(1);
            String  supplierID = resultSet.getString(2);
            String  fabricName = resultSet.getString(3);
            String  fabricColor = resultSet.getString(4);
            int  fabricQtyOnHand = resultSet.getInt(5);

            return new Fabric(fabricID, supplierID, fabricName, fabricColor, fabricQtyOnHand);
        }
        return null;
    }

    @Override
    public ArrayList<Fabric> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM fabric");

        ArrayList<Fabric> fabList = new ArrayList<>();
        while (resultSet.next()) {
            String productId = resultSet.getString(1);
            String supplierId = resultSet.getString(2);
            String fName = resultSet.getString(3);
            String fColor = resultSet.getString(4);
            int qty = resultSet.getInt(5);

            fabList.add(new Fabric(productId, supplierId, fName, fColor, qty));
        }
        return fabList;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT fabricID FROM fabric ORDER BY fabricID DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public List<String> getFabricName() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT fabricName FROM fabric");

        List<String> fabricList = new ArrayList<>();
        while (resultSet.next()) {
            fabricList.add(resultSet.getString(1));
        }
        return fabricList;
    }


    @Override
    public boolean odQtyUpdate(List<OrderDetails> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetails od : odList) {
            boolean isUpdateQty = updateQty(od);
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(OrderDetails od) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE fabric SET fabricQtyOnHand = fabricQtyOnHand - ? WHERE fabricID = ?", (int) od.getFabricSize(), od.getFabricID());
    }


    @Override
    public List<String> getColorsForFabric(String fabricName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT fabricColor FROM fabric WHERE fabricName = ?",fabricName);
        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
