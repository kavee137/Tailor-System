package lk.ijse.tailorsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.tailorsystem.Util.Regex;
import lk.ijse.tailorsystem.bo.BOFactory;
import lk.ijse.tailorsystem.bo.custom.CustomerBO;
import lk.ijse.tailorsystem.bo.custom.SupplierBO;
import lk.ijse.tailorsystem.dto.SupplierDTO;
import lk.ijse.tailorsystem.entity.Supplier;
import lk.ijse.tailorsystem.view.tdm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierFormController {

    @FXML
    public AnchorPane rootNode;
    @FXML
    public TableColumn<?, ?> colStatus;
    @FXML
    public TableColumn<?, ?> colId;
    @FXML
    public TableColumn<?, ?> colNic;
    @FXML
    public TableColumn<?, ?> colName;
    @FXML
    public TableColumn<?, ?> colAddress;
    @FXML
    public TableColumn<?, ?> colTel;
    @FXML
    public TableView<SupplierTm> tblSupplier;

    @FXML
    private Label lblSupplierId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtTel;

    SupplierBO supplierBO  = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    public void initialize(){
        txtStatus.setText("Active");

        getCurrentSupplierId();
        setCellValueFactory();
        loadAllSuppliers();
        showSelectedSupplierDetails();

    }

    private void showSelectedSupplierDetails() {
        SupplierTm selectedUser = tblSupplier.getSelectionModel().getSelectedItem();
        tblSupplier.setOnMouseClicked(event -> showSelectedSupplierDetails());
        if (selectedUser != null) {
            lblSupplierId.setText(selectedUser.getSupplierID());
            txtName.setText(selectedUser.getSupplierName());
            txtNic.setText(selectedUser.getNIC());
            txtTel.setText(selectedUser.getSupplierContact());
            txtStatus.setText(selectedUser.getStatus());
            txtAddress.setText(selectedUser.getSupplierAddress());
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadAllSuppliers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = supplierBO.getAll();
            for (Supplier supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierID(),
                        supplier.getNIC(),
                        supplier.getSupplierName(),
                        supplier.getSupplierAddress(),
                        supplier.getSupplierContact(),
                        supplier.getStatus()
                );

                obList.add(tm);
            }
            tblSupplier.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentSupplierId() {
        try {
            String currentId = supplierBO.generateNewID();

            String nextEmployeeId = generateNextSupplierId(currentId);
            lblSupplierId.setText(nextEmployeeId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextSupplierId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("S");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "S0" + ++idNum;
        }
        return "SO1";
    }

    @FXML
    void btnActiveOnAction(ActionEvent event) {
        txtStatus.setText("Active");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

        txtStatus.setText("Active");

        getCurrentSupplierId();
        setCellValueFactory();
        loadAllSuppliers();
    }

    private void clearFields() {
        lblSupplierId.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        txtTel.setText("");
        txtName.setText("");

        txtAddress.setStyle("");
        txtNic.setStyle("");
        txtTel.setStyle("");
        txtName.setStyle("");
        txtStatus.setStyle("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String nic = txtNic.getText();

        try {
            boolean isDeleted = supplierBO.delete(nic);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
                clearFields();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (isValied()) {
            String id = lblSupplierId.getText();
            String nic = txtNic.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();
            String status = txtStatus.getText();

            Supplier supplier = new Supplier(id, nic, name, address, tel, status);

            try {
                boolean isSaved = supplierBO.add(supplier);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else {
            // Show error message if validation fails
            validationError();
        }
    }

    private void validationError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Validation Failed");
        alert.setContentText("Please fill in all fields correctly.");
        alert.showAndWait();
    }

    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nic = txtNic.getText();

        Supplier supplier = supplierBO.search(nic);
        if (supplier != null) {
            lblSupplierId.setText(supplier.getSupplierID());
            txtNic.setText(supplier.getNIC());
            txtName.setText(supplier.getSupplierName());
            txtAddress.setText(supplier.getSupplierAddress());
            txtTel.setText(supplier.getSupplierContact());
            txtStatus.setText(supplier.getStatus());
        }else {
            new Alert(Alert.AlertType.INFORMATION, "supplier not found!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (isValied()) {
            String id = lblSupplierId.getText();
            String nic = txtNic.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();
            String status = txtStatus.getText();

            SupplierDTO supplierDTO = new SupplierDTO(id, nic, name, address, tel, status);
            try {
                boolean isUpdated = supplierBO.update(supplierDTO);
                if(isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                    clearFields();
                    initialize();
                } else {
                    new Alert(Alert.AlertType.ERROR, "employee not updated!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Show error message if validation fails
            validationError();
        }
    }

    public boolean isValied(){
        boolean nameValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtName);
        boolean nicValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NIC, txtNic);
        boolean addressValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.ADDRESS, txtAddress);
        boolean telValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.TEL, txtTel);
        boolean statusValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.STATUS, txtStatus);

        return nameValid && nicValid && addressValid && telValid && statusValid;
    }

    public void telKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.TEL, txtTel);
    }

    public void nicKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NIC, txtNic);
    }

    public void nameKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtName);
    }

    public void addressKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.ADDRESS, txtAddress);
    }

    public void statusKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.STATUS, txtStatus);
    }

}
