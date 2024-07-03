package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.UserDAO;
import lk.ijse.tailorsystem.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public String getTotalUser() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS allUser FROM user");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(User user) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user VALUES(?, ?, ?, ?, ?)", user.getUserID(), user.getUserName(), user.getUserEmail(), user.getPassword(), "Active");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET status = 'Inactive' WHERE userID = ?", id);
    }


    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET userID = ?, userName = ?, userEmail = ?, password = ?, status = ? WHERE userID = ?", user.getUserID(), user.getUserName(), user.getUserEmail(), user.getPassword(), user.getStatus(), user.getUserID());
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user");

        ArrayList<User> supList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String userName = resultSet.getString(2);
            String email = resultSet.getString(3);
            String password = resultSet.getString(4);
            String status = resultSet.getString(5);

            User user = new User(id, userName, email, password, status);
            supList.add(user);
        }
        return supList;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT userID FROM user ORDER BY userID DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }


    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(Object... args) throws SQLException, ClassNotFoundException {
        return null;
    }

}
