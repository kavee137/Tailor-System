package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.ReservationDetailsDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.entity.ReservationDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDetailsDAOImpl implements ReservationDetailsDAO {


    @Override
    public ResultSet getReservationDetailsTable(int id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT rd.productID,  p.productName,  p.productColor, p.unitPrice, rd.qty FROM reservation r JOIN reservationDetails rd ON r.reservationID = rd.reservationID JOIN product p ON rd.productID = p.productID WHERE rd.reservationID = ?", id);
    }



    @Override
    public boolean save(List<ReservationDetails> rdList) throws SQLException, ClassNotFoundException{
        for (ReservationDetails od : rdList) {
            boolean isSaved = save(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(ReservationDetails rd) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservationDetails VALUES(?, ?, ?, ?)", rd.getReservationID(), rd.getProductID(), rd.getQty(), rd.getTotal());    //false ->  |
    }










    @Override
    public ArrayList<ReservationDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(ReservationDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ReservationDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ReservationDetails search(Object... args) throws SQLException, ClassNotFoundException {
        return null;
    }
}
