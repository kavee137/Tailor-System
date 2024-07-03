package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.UserBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.UserDAO;
import lk.ijse.tailorsystem.dto.UserDTO;
import lk.ijse.tailorsystem.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String getTotalUser() throws SQLException, ClassNotFoundException {
        return userDAO.getTotalUser();
    }

    @Override
    public boolean add(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.add(new User(user.getUserID(), user.getUserName(), user.getUserEmail(), user.getPassword(), user.getStatus()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }


    @Override
    public boolean update(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(user.getUserID(), user.getUserName(), user.getUserEmail(), user.getPassword(), user.getStatus()));
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return userDAO.getAll();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return userDAO.generateNewID();
    }

}
