package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.LoginBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.LoginDAO;
import lk.ijse.tailorsystem.dto.LoginDTO;
import lk.ijse.tailorsystem.entity.Login;

import java.io.IOException;
import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);

    @Override
    public boolean checkCredential(LoginDTO login) throws SQLException, IOException, ClassNotFoundException {
       return loginDAO.checkCredential(new Login(login.getUserName(), login.getPassword()));
    }

}
