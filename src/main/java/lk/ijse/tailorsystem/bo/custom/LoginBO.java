package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dto.LoginDTO;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean checkCredential(LoginDTO login) throws SQLException, IOException, ClassNotFoundException;
}
