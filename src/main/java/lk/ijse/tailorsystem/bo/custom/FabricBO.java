package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.dto.FabricDTO;
import lk.ijse.tailorsystem.entity.Fabric;
import lk.ijse.tailorsystem.entity.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FabricBO extends SuperBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean update(FabricDTO fabric) throws SQLException, ClassNotFoundException;

    public boolean add(FabricDTO fab) throws SQLException, ClassNotFoundException;

    public Fabric search(Object... args) throws SQLException, ClassNotFoundException;

    public ArrayList<FabricDTO> getAll() throws SQLException, ClassNotFoundException;

    public String generateNewID() throws SQLException, ClassNotFoundException;

    public List<String> getFabricName() throws SQLException, ClassNotFoundException;

    public boolean odQtyUpdate(List<OrderDetails> odList) throws SQLException, ClassNotFoundException;

    public boolean exist(String id) throws SQLException, ClassNotFoundException;

    public List<String> getColorsForFabric(String fabricName) throws SQLException, ClassNotFoundException;

}
