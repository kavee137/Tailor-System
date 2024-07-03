package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dto.UserDTO;
import lk.ijse.tailorsystem.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    String getTotalUser() throws SQLException, ClassNotFoundException;

    boolean add(UserDTO user) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(UserDTO user) throws SQLException, ClassNotFoundException;

    ArrayList<User> getAll() throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
