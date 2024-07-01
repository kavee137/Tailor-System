package lk.ijse.tailorsystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tailorsystem.Util.Regex;
import lk.ijse.tailorsystem.bo.BOFactory;
import lk.ijse.tailorsystem.bo.custom.CustomerBO;
import lk.ijse.tailorsystem.dto.CustomerDTO;
import lk.ijse.tailorsystem.entity.Customer;
import lk.ijse.tailorsystem.entity.Customer1;
import lk.ijse.tailorsystem.view.tdm.CustomerTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {
    public AnchorPane rootNode;
    @FXML
    private JFXComboBox<String> cmbStatus;
    @FXML
    private Label lblCustomerId;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        getCurrentCustomerId();
        showSelectedUserDetails();
        setTxtStatus();
    }

    private void showSelectedUserDetails() {
        CustomerTm selectedUser = tblCustomer.getSelectionModel().getSelectedItem();
        tblCustomer.setOnMouseClicked(event -> showSelectedUserDetails());
        if (selectedUser != null) {
            lblCustomerId.setText(selectedUser.getCustomerID());
            txtName.setText(selectedUser.getCustomerName());
            txtNic.setText(selectedUser.getNIC());
            txtAddress.setText(selectedUser.getCustomerAddress());
            txtTel.setText(selectedUser.getCustomerTel());
            cmbStatus.setValue(selectedUser.getStatus());
        }
    }

    private void getCurrentCustomerId() {
        try {
            String currentId = customerBO.generateNewCustomerID();

            String nextCustomerId = generateNextCustomerId(currentId);
            lblCustomerId.setText(nextCustomerId);
            cmbStatus.setValue("Active");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextCustomerId(String currentId) {
        if(currentId != null && !currentId.isEmpty()) {
            String[] split = currentId.split("C");
            if (split.length > 1) {
                int idNum = Integer.parseInt(split[1]);
                return "C0" + String.format("%02d", ++idNum);
            }
        }
        return "C01"; // Default starting ID
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("customerTel"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadAllCustomers() {
//        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
//
//        try {
//            List<Customer> customerList = customerBO.getAllCustomers();
//            for (Customer customer : customerList) {
//                CustomerTm tm = new CustomerTm(
//                        customer.getCustomerID(),
//                        customer.getNIC(),
//                        customer.getCustomerName(),
//                        customer.getCustomerAddress(),
//                        customer.getCustomerTel(),
//                        customer.getUserID(),
//                        customer.getStatus()
//                );
////                System.out.println(tm);
//                obList.add(tm);
//            }
//
//            tblCustomer.setItems(obList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        tblCustomer.getItems().clear();
        try {
            /*Get all customers*/
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();

            for (CustomerDTO c : allCustomers) {
                tblCustomer.getItems().add(new CustomerTm(c.getCustomerID(),c.getNIC(),c.getCustomerName(), c.getCustomerAddress(), c.getCustomerTel(), c.getUserID(), c.getStatus()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = lblCustomerId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        String status = cmbStatus.getValue();
        String userId = "U01";

        if (isValied()) {
            CustomerDTO customerDTO = new CustomerDTO(id, nic, name, address, tel, userId, status);

            try {
                boolean isUpdated = customerBO.updateCustomer(customerDTO);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = lblCustomerId.getText();
        String nic = txtNic.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        String userId = "U01";
        String status = "Active";

        CustomerDTO customerDTO = new CustomerDTO(id, nic, name, address, tel, userId, status);
        if (isValied()) {
            try {
                boolean isSaved = customerBO.addCustomer(customerDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    clearFields();
//                getCurrentCustomerId();
                    initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    public void nameKeyRelaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtName);
    }

    public void nicKeyRelaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NIC, txtNic);
    }

    public void addressKeyRelaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.ADDRESS, txtAddress);
    }

    public void telKeyRelaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.TEL, txtTel);
    }


//    public void idKeyRelaseAction(javafx.scene.input.KeyEvent keyEvent) {
//        Regex.setTextColor(lk.ijse.tailorshopmanagementsystem.Util.TextField.CUSID, lblCustomerId);
//    }

    public boolean isValied(){
        boolean nameValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtName);
        boolean nicValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NIC, txtNic);
        boolean addressValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.ADDRESS, txtAddress);
        boolean telValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.TEL, txtTel);
//        boolean idValid = Regex.setTextColor(lk.ijse.tailorshopmanagementsystem.Util.TextField.CUSID, txtCustomerId);

        return nameValid && nicValid && addressValid && telValid;
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nic = txtNic.getText();

        if (isSearchNicValied()) {
            Customer customer = customerBO.customerSearch(nic);
            if (customer != null) {
                lblCustomerId.setText(customer.getCustomerID());
                txtName.setText(customer.getCustomerName());
                txtAddress.setText(customer.getCustomerAddress());
                txtTel.setText(customer.getCustomerTel());
                cmbStatus.setValue(customer.getStatus());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
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

    public boolean isSearchNicValied(){
        boolean nicValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NIC, txtNic);
        return nicValid;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String nic = txtNic.getText();

        try {
            boolean isDeleted = customerBO.deleteCustomer(nic);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
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
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        getCurrentCustomerId();
    }

    private void clearFields() {
        lblCustomerId.setText("");

        txtNic.setText("");
        txtNic.setStyle(""); // Reset style to default

        txtName.setText("");
        txtName.setStyle(""); // Reset style to default

        txtAddress.setText("");
        txtAddress.setStyle(""); // Reset style to default

        txtTel.setText("");
        txtTel.setStyle(""); // Reset style to default

    }

    @FXML
    public void btnActiveOnAction(ActionEvent actionEvent) {
        cmbStatus.setValue("Active");
    }

    private void setTxtStatus() {
        ObservableList<String> status = FXCollections.observableArrayList();
        cmbStatus.setValue("Active");

        status.add("Active");
        status.add("Inactive");

        cmbStatus.setItems(status);
    }

}