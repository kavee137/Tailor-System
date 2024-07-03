package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    String getTotalUser() throws SQLException, ClassNotFoundException;
}
