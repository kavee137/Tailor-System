package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.ReservationDetailsBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.ReservationDetailsDAO;
import lk.ijse.tailorsystem.entity.ReservationDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservationDetailsBOImpl implements ReservationDetailsBO {


    ReservationDetailsDAO reservationDetailsDAO = (ReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONDETAILS);
    @Override
    public ResultSet getReservationDetailsTable(int id) throws SQLException, ClassNotFoundException {
        return reservationDetailsDAO.getReservationDetailsTable(id);
    }



    @Override
    public boolean save(List<ReservationDetails> rdList) throws SQLException, ClassNotFoundException{
        return reservationDetailsDAO.save(rdList);
    }

}
