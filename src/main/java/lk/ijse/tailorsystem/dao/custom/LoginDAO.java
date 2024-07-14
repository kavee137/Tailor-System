package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dao.SuperDAO;
import lk.ijse.tailorsystem.entity.Login;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {
    boolean checkCredential(Login login) throws SQLException, IOException, ClassNotFoundException;
}
