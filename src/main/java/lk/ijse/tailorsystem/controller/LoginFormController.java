package lk.ijse.tailorsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.tailorsystem.bo.BOFactory;
import lk.ijse.tailorsystem.bo.custom.FabricBO;
import lk.ijse.tailorsystem.bo.custom.LoginBO;
import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dto.LoginDTO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtUserId;
    public TextField txtPassword;

    public AnchorPane rootNode;

    LoginBO loginBO  = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);



    public void initialize() {
    }


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String userId = txtUserId.getText();
        String pw = txtPassword.getText();

        try {
            boolean isCredentialTrue = loginBO.checkCredential(new LoginDTO(userId, pw));
            if (isCredentialTrue) {
                navigateToTheDashboard((Stage) rootNode.getScene().getWindow());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
            boolean isCredentialTrue = loginBO.checkCredential(new LoginDTO(userId, pw));
            if (isCredentialTrue) {
                navigateToTheDashboard((Stage) rootNode.getScene().getWindow());
            }
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
            boolean isCredentialTrue = loginBO.checkCredential(new LoginDTO(userId, pw));
            if (isCredentialTrue) {
                navigateToTheDashboard((Stage) rootNode.getScene().getWindow());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}