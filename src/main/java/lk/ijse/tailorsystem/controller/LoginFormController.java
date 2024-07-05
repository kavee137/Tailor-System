package lk.ijse.tailorsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.tailorsystem.dao.SQLUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtUserId;
    public TextField txtPassword;

    public AnchorPane rootNode;

    private String loggedInUserName;



    public void initialize() {
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String userId = txtUserId.getText();
        String pw = txtPassword.getText();

        try {
            checkCredential(userId, pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkCredential(String userId, String pw) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT userName, password FROM user WHERE userName = ? AND status = 'Active'", userId);

        if (resultSet.next()) {
            String dbPw = resultSet.getString("password");

            if (pw.equals(dbPw)) {
                loggedInUserName = resultSet.getString("userName");
                navigateToTheDashboard((Stage) rootNode.getScene().getWindow());
            } else {
                new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!").show();
        }
    }

    private void navigateToTheDashboard(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));
        AnchorPane rootNode = loader.load();
        DashboardFormController controller = loader.getController();
        controller.initialize(); // Assuming you want to initialize the dashboard controller

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
    }


    public void txtUserNameOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        String userId = txtUserId.getText();
        String pw = txtPassword.getText();

        try {
            checkCredential(userId, pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtUsernameOnAction(ActionEvent actionEvent) throws IOException {
        String userId = txtUserId.getText();
        String pw = txtPassword.getText();

        try {
            checkCredential(userId, pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}