package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.FabricBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.EmployeeDAO;
import lk.ijse.tailorsystem.dao.custom.FabricDAO;
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

public class FabricBOImpl implements FabricBO {

    FabricDAO fabricDAO = (FabricDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FABRIC);

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return fabricDAO.delete(id);
    }


    @Override
    public boolean update(FabricDTO fab) throws SQLException, ClassNotFoundException {
        return fabricDAO.update(new Fabric(fab.getFabricID(), fab.getSupplierID(), fab.getFabricName(), fab.getFabricColor(), fab.getFabricQtyOnHand()));
    }

    @Override
    public boolean add(FabricDTO fab) throws SQLException, ClassNotFoundException {
        return fabricDAO.add(new Fabric(fab.getFabricID(), fab.getSupplierID(), fab.getFabricName(), fab.getFabricColor(), fab.getFabricQtyOnHand()));
    }

    @Override
    public Fabric search(Object... args) throws SQLException, ClassNotFoundException {
        return fabricDAO.search(args);
    }


    @Override
    public ArrayList<FabricDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<FabricDTO> allFabric = new ArrayList<>();

        ArrayList<Fabric> all = fabricDAO.getAll();
        for (Fabric f : all) {
            allFabric.add(new FabricDTO(f.getFabricID(), f.getSupplierID(), f.getFabricName(), f.getFabricColor(), f.getFabricQtyOnHand()));
        }
        return allFabric;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return fabricDAO.generateNewID();
    }

    @Override
    public List<String> getFabricName() throws SQLException, ClassNotFoundException {
        return fabricDAO.getFabricName();
    }


    @Override
    public boolean odQtyUpdate(List<OrderDetails> odList) throws SQLException, ClassNotFoundException {
        return fabricDAO.odQtyUpdate(odList);
    }


    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public List<String> getColorsForFabric(String fabricName) throws SQLException, ClassNotFoundException {
        return fabricDAO.getColorsForFabric(fabricName);
    }

}
