package lk.ijse.tailorsystem.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.tailorsystem.bo.custom.ProductBO;
import lk.ijse.tailorsystem.dto.ProductDTO;
import lk.ijse.tailorsystem.entity.Product;
import lk.ijse.tailorsystem.entity.Product1;
import lk.ijse.tailorsystem.view.tdm.ProductTm;
import lk.ijse.tailorsystem.dao.custom.impl.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductFormController {
    @FXML
    public TextField txtColor;
    @FXML
    private Label lblProductId;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?>colColor;
    @FXML
    private TableColumn<?, ?> colSize;
    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private TableColumn<?, ?> colQtyOnHand;
    @FXML
    private TableView<ProductTm> tblProduct;
    @FXML
    private TextField txtSize;
    @FXML
    private TextField txtName;
    @FXML
    private JFXComboBox <String> cmbProductSize;
    @FXML
    private JFXComboBox <String> cmbProductColor;
    @FXML
    private JFXComboBox <String> cmbProductName;
    @FXML
    private TextField txtQtyOnHand;
    @FXML
    private TextField txtUnitPrice;

    public AnchorPane rootNode;


    ProductBO productBO  = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);


    public void initialize() {
        setCellValueFactory();
        loadAllProducts();
        getCurrentProductId();
        getProductName();
        showSelectedProductDetails();

    }

    private void showSelectedProductDetails() {
        ProductTm selectedUser = tblProduct.getSelectionModel().getSelectedItem();
        tblProduct.setOnMouseClicked(event -> showSelectedProductDetails());
        if (selectedUser != null) {
            cmbProductName.setValue(selectedUser.getProductName());
            cmbProductColor.setValue(selectedUser.getProductColor());
            cmbProductSize.setValue(selectedUser.getProductSize());
            txtName.setText(selectedUser.getProductName());
            txtColor.setText(selectedUser.getProductColor());
            txtSize.setText(selectedUser.getProductSize());
            lblProductId.setText(String.valueOf(selectedUser.getProductID()));
            txtUnitPrice.setText(String.valueOf(selectedUser.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(selectedUser.getQtyOnHand()));
        }
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("productColor"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("productSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
    }

    private void loadAllProducts() {
        ObservableList<ProductTm> obList = FXCollections.observableArrayList();
        try {
            ArrayList<ProductDTO> productList = productBO.getAll();
            for (ProductDTO product : productList) {
                ProductTm t = new ProductTm(
                        product.getProductID(),
                        product.getProductName(),
                        product.getProductColor(),
                        product.getProductSize(),
                        product.getUnitPrice(),
                        product.getQtyOnHand()
                );
                obList.add(t);
            }
            tblProduct.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void getProductSize() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String color = cmbProductColor.getValue();
        String name = cmbProductName.getValue();

        try {
            List<String> sizeList = productBO.getProductSize(name, color);

            obList.addAll(sizeList);
            cmbProductSize.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private void getProductName() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = productBO.getProductName();

            for (String code : codeList) {
                obList.add(code);
            }
            cmbProductName.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void getProductColor() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String name = (String) cmbProductName.getValue();
        try {
            List<String> colorList = productBO.getProductColor(name);

            for (String code : colorList) {
                obList.add(code);
            }
            cmbProductColor.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentProductId() {
        try {
            int currentId = Integer.parseInt(productBO.generateNewID());

            int nextProductId = Integer.parseInt(String.valueOf(generateNextProductId(currentId)));
            lblProductId.setText(String.valueOf(nextProductId));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private int generateNextProductId(int currentId) {
        if(currentId != 0) {
                return ++currentId;
        }
        return 1;
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String id = lblProductId.getText();
        String name = txtName.getText();
        String color = txtColor.getText();
        String size = txtSize.getText();
        String price = txtUnitPrice.getText();
        String qtyOnHand = txtQtyOnHand.getText();

        if (isValied()) {
            try {
                boolean isUpdated = productBO.update(new ProductDTO(id, name, color, size, price, qtyOnHand));
                if(isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "product updated!").show();
                    clearFields();
                    initialize();
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

    private void validationError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Validation Failed");
        alert.setContentText("Please fill in all fields correctly.");
        alert.showAndWait();
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String id = lblProductId.getText();
        String name = txtName.getText();
        String color = txtColor.getText();
        String size = txtSize.getText();
        String price = txtUnitPrice.getText();
        String qtyOnHand = txtQtyOnHand.getText();

        if (isValied()) {
            try {
                boolean isSaved = productBO.add(new ProductDTO(id, name, color, size, price, qtyOnHand));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "product saved!").show();
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

    public boolean isValied() {
        boolean nameValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtName);
        boolean color = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtColor);
        boolean size = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.QTY, txtSize);
        boolean unitPrice = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.PRICEDOT, txtUnitPrice);
        boolean qtyOnHand = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.QTY, txtQtyOnHand);

        return nameValid && color && size && unitPrice && qtyOnHand;
    }

    public void sizeKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.FABSIZE, txtSize);
    }

    public void colorKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtColor);
    }

    public void priceKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.PRICEDOT, txtUnitPrice);
    }

    public void nameKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NAME, txtName);
    }

    public void qtyOnHandKeyReleaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.QTY, txtQtyOnHand);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String cmbProductNameValue = (String) cmbProductName.getValue();
        String cmbProductColorValue = (String) cmbProductColor.getValue();
        String cmbProductSizeValue = (String) cmbProductSize.getValue();

        if (cmbProductNameValue != null && cmbProductColorValue != null && cmbProductSizeValue != null) {
            txtName.setText(cmbProductNameValue);
            txtColor.setText(cmbProductColorValue);
            txtSize.setText(cmbProductSizeValue);

            Product product1 = productBO.search(cmbProductNameValue, cmbProductColorValue, cmbProductSizeValue);
            if (product1 != null) {
                lblProductId.setText(product1.getProductID());
                txtUnitPrice.setText(product1.getUnitPrice());
                txtQtyOnHand.setText(product1.getQtyOnHand());
            }
        } else {
            // Handle the case where one or more values are null
            showErrorAlert("Product details Failed", "Please select product details!");
        }
    }

    private void showErrorAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblProductId.getText();

        try {
            boolean isDeleted = productBO.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "product deleted!").show();
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

        setCellValueFactory();
        loadAllProducts();
        getCurrentProductId();
        getProductName();
    }

    private void clearFields() {
        cmbProductName.setValue(null);
        cmbProductName.setValue(null);
        cmbProductSize.setValue(null);
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
        txtSize.setText("");
        txtName.setText("");
        txtColor.setText("");

        txtUnitPrice.setStyle(""); // Set text color to default
        txtQtyOnHand.setStyle(""); // Set text color to default
        txtSize.setStyle(""); // Set text color to default
        txtName.setStyle(""); // Set text color to default
        txtColor.setStyle(""); // Set text color to default
        lblProductId.setStyle(""); // Set text color to default

    }

    public void cmbProductNameOnAction(ActionEvent actionEvent) {
        getProductColor();
    }

    public void cmbProductColorOnAction(ActionEvent actionEvent) {
        getProductSize();
    }

    public void cmbProductSizeOnAction(ActionEvent actionEvent) {

    }


}
