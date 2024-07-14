package lk.ijse.tailorsystem.dao.custom.impl;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.LoginDAO;
import lk.ijse.tailorsystem.entity.Login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean checkCredential(Login login) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT userName, password FROM user WHERE userName = ? AND status = 'Active'", login.getUserName());

        if (resultSet.next()) {
            String dbPw = resultSet.getString("password");

            if (login.getPassword().equals(dbPw)) {
                return true;
            } else {
                new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
                return false;
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!").show();
            return false;
        }
    }


}
