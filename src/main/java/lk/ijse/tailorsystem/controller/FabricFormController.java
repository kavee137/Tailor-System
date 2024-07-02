package lk.ijse.tailorsystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.tailorsystem.Util.Regex;
import lk.ijse.tailorsystem.bo.BOFactory;
import lk.ijse.tailorsystem.bo.custom.FabricBO;
import lk.ijse.tailorsystem.bo.custom.SupplierBO;
import lk.ijse.tailorsystem.dto.FabricDTO;
import lk.ijse.tailorsystem.entity.Fabric;
import lk.ijse.tailorsystem.view.tdm.FabricTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FabricFormController {

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private JFXComboBox<String> cmbSupplierId;

    @FXML
    private JFXComboBox<String> cmbFabricColor;

    @FXML
    private JFXComboBox<String> cmbFabricName;

    @FXML
    private TableColumn<?, ?> colFabricColor;

    @FXML
    private TableColumn<?, ?> colFabricId;

    @FXML
    private TableColumn<?, ?> colFabricName;

    @FXML
    private TableColumn<?, ?> colSupplierId;


    @FXML
    private Label lblFabricId;

    @FXML
    private TableView<FabricTm> tblFabric;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQtyOnHand;

    FabricBO fabricBO  = (FabricBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.FABRIC);
    SupplierBO supplierBO  = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);


    public void initialize() {
        getFabricName();
        getCurrentFabricId();
        loadAllSupplierId();
        setCellValueFactory();
        loadAllFabric();
        showSelectedUserDetails();
    }

    private void showSelectedUserDetails() {
        FabricTm selectedUser = tblFabric.getSelectionModel().getSelectedItem();
        tblFabric.setOnMouseClicked(event -> showSelectedUserDetails());
        if (selectedUser != null) {
            lblFabricId.setText(selectedUser.getFabricID());
            txtName.setText(selectedUser.getFabricName());
            cmbSupplierId.setValue(selectedUser.getSupplierID());
            txtColor.setText(selectedUser.getFabricColor());
            txtQtyOnHand.setText(String.valueOf(selectedUser.getFabricQtyOnHand()));
        }
    }

    private void setCellValueFactory() {
        colFabricId.setCellValueFactory(new PropertyValueFactory<>("fabricID"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colFabricName.setCellValueFactory(new PropertyValueFactory<>("fabricName"));
        colFabricColor.setCellValueFactory(new PropertyValueFactory<>("fabricColor"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("fabricQtyOnHand"));
    }

    private void loadAllFabric() {
        ObservableList<FabricTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<FabricDTO> fabricList = fabricBO.getAll();
            for (FabricDTO fabric : fabricList) {
                FabricTm tm = new FabricTm(
                        fabric.getFabricID(),
                        fabric.getSupplierID(),
                        fabric.getFabricName(),
                        fabric.getFabricColor(),
                        fabric.getFabricQtyOnHand()
                );
                obList.add(tm);
            }
            tblFabric.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllSupplierId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = supplierBO.getSuppliereIds();
            obList.addAll(idList);
            cmbSupplierId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentFabricId() {
        try {
            lblFabricId.setText(generateNextFabricId(fabricBO.generateNewID()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextFabricId(String currentId) {
        if (currentId != null && !currentId.isEmpty()) {
            String[] split = currentId.split("F");
            if (split.length > 1) {
                int idNum = Integer.parseInt(split[1]);
                return "F" + String.format("%02d", ++idNum);
            }
        }
        return "F01"; // Default starting ID
    }

    private void getFabricName() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            obList.addAll(fabricBO.getFabricName());
            cmbFabricName.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        getCurrentFabricId();
    }

    private void clearFields() {
        cmbFabricName.setValue(null);
        cmbFabricColor.setValue(null);
        cmbSupplierId.setValue(null);
        lblFabricId.setText("");
        txtName.setText("");
        txtColor.setText("");
        txtQtyOnHand.setText("");

        txtName.setStyle("");
        txtColor.setStyle("");
        txtQtyOnHand.setStyle("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblFabricId.getText();

        try {
            if(fabricBO.delete(id)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Fabric deleted!").show();
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
        if (isValied() && (cmbSupplierId.getValue() != null && !cmbSupplierId.getValue().toString().isEmpty())) {

            String id = lblFabricId.getText();
            String supId = cmbSupplierId.getValue();
            String name = txtName.getText();
            String color = txtColor.getText();
            int qty = Integer.parseInt(txtQtyOnHand.getText());

            FabricDTO fabric = new FabricDTO(id, supId, name, color, qty);
            try {
                if (fabricBO.add(fabric)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Fabric saved!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
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

    public boolean isValied(){
        boolean nameValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtName);
        boolean color = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtColor);
        boolean qtyOnHand = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.QTY, txtQtyOnHand);

        return nameValid && color && qtyOnHand;
    }

    public void nameKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtName);
    }

    public void colorKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtColor);
    }

    public void qtyOnHandKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.QTY, txtQtyOnHand);
    }
    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String fabName = cmbFabricName.getValue();
        String  fabColor = cmbFabricColor.getValue();

        txtName.setText(fabName);
        txtColor.setText(fabColor);

        Fabric fabricSearch = fabricBO.search(fabName, fabColor);
        if (fabricSearch != null) {
            lblFabricId.setText(fabricSearch.getFabricID());
            cmbSupplierId.setValue(fabricSearch.getSupplierID());
            txtName.setText(fabricSearch.getFabricName());
            txtColor.setText(fabricSearch.getFabricColor());
            txtQtyOnHand.setText(String.valueOf(fabricSearch.getFabricQtyOnHand()));
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Fabric not found!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (isValied() && (cmbSupplierId.getValue() != null && !cmbSupplierId.getValue().toString().isEmpty())) {
            String id = lblFabricId.getText();
            String supId = cmbSupplierId.getValue();
            String name = txtName.getText();
            String color = txtColor.getText();
            int qty = Integer.parseInt(txtQtyOnHand.getText());

            FabricDTO fabric = new FabricDTO(id, supId, name, color, qty);
            try {
                boolean isUpdated = fabricBO.update(fabric);
                if(isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Fabric updated!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            // Show error message if validation fails
            validationError();
        }
    }
    @FXML
    void cmbFabricNameOnAction(ActionEvent event) {
        getColorsForFabric();
    }

    public void getColorsForFabric() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        String fabricName = (String) cmbFabricName.getValue();
        try {
            List<String> codeList = fabricBO.getColorsForFabric(fabricName);
            obList.addAll(codeList);
            cmbFabricColor.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
