
package lk.ijse.tailorsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.tailorsystem.Util.Regex;
import lk.ijse.tailorsystem.bo.BOFactory;
import lk.ijse.tailorsystem.bo.custom.*;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.CustomerDAO;
import lk.ijse.tailorsystem.dao.custom.PaymentDAO;
import lk.ijse.tailorsystem.dao.custom.ProductDAO;
import lk.ijse.tailorsystem.dao.custom.ReservationDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.dto.ReservationDTO;
import lk.ijse.tailorsystem.entity.PlaceReservation;
import lk.ijse.tailorsystem.entity.Reservation;
import lk.ijse.tailorsystem.entity.ReservationDetails;
import lk.ijse.tailorsystem.view.tdm.ReservationTm;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static java.lang.Integer.parseInt;

public class ReseravationFormController {
    @FXML
    private Label lblReservationId;
//    @FXML
//    private TextField txtReservationId;
    @FXML
    private JFXComboBox<String> cmbStatus;
    @FXML
    private TextField txtReservationId;
    @FXML
    private TableColumn<?, ?> colProductId;
    @FXML
    private TableColumn<?, ?> colProductName;
    @FXML
    private TableColumn<?, ?> colProductColor;
    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private TableColumn<?, ?> colQty;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableColumn<?, ?> colAction;


    @FXML
    private JFXComboBox<String> cmbSize;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblReservationDate;

    @FXML
    private JFXComboBox<String> cmbPaymentType;

    @FXML
    private JFXComboBox<String> cmbProductColor;

    @FXML
    private JFXComboBox<String> cmbProductName;

    @FXML
    private DatePicker dpReturnDate;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblProductId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<ReservationTm> tblReservation;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtQty;

    private ObservableList<ReservationTm> obList = FXCollections.observableArrayList();
    PlaceReservationBO placeReservationBO  = (PlaceReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACERESERVATION);
    ProductBO productBO  = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);
    CustomerDAO customerDAO  = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ProductDAO productDAO  = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    PaymentDAO paymentDAO  = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);




    public void initialize() {
        setDate();
        getCurrentReservationId();
        getProductName();
        setPaymentType();
        getCurrentPaymentId();
        setCmbStatus();
        setCellValueFactory();

    }

    public void btnIdSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        idSearch();
    }

    private boolean idValid() {
        boolean resValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.RESID, txtReservationId);

        return resValid;
    }

    void idSearch() throws SQLException, ClassNotFoundException {

        if (!txtReservationId.getText().isEmpty() && idValid()) {

            int nicFieldId = Integer.parseInt(txtReservationId.getText());

            if (currentReservationIdForBillMethod >= nicFieldId && nicFieldId > 0) {

                int resId = parseInt(txtReservationId.getText());
                tblReservation.getItems().clear();

                ResultSet reservationDetailsList = placeReservationBO.getReservationDetailsTable(resId);

                while (reservationDetailsList.next()) {
                    int pId = reservationDetailsList.getInt(1);
                    String pName = reservationDetailsList.getString(2);
                    String pColor = reservationDetailsList.getString(3);
                    double unitPrice = reservationDetailsList.getDouble(4);
                    int qty = reservationDetailsList.getInt(5);
                    double total = qty * unitPrice;

                    ReservationTm r = new ReservationTm(pId, pName, pColor, unitPrice, qty, total, new JFXButton());
                    obList.add(r);
                    tblReservation.setItems(obList);
                }

                ArrayList<String> reservationJoinTablesList = placeReservationBO.getReservationJoinTable(resId);

                lblCustomerId.setText(reservationJoinTablesList.get(0));
                lblCustomerName.setText(reservationJoinTablesList.get(1));
                lblPaymentId.setText(reservationJoinTablesList.get(2));
                cmbPaymentType.setValue(reservationJoinTablesList.get(3));
                lblNetTotal.setText(reservationJoinTablesList.get(4));
                lblReservationDate.setText(reservationJoinTablesList.get(5));
                dpReturnDate.setValue(LocalDate.parse(reservationJoinTablesList.get(6)));
                cmbStatus.setValue(reservationJoinTablesList.get(7));
                txtNic.setText(reservationJoinTablesList.get(8));
                lblReservationId.setText(txtReservationId.getText());
            } else {
                clearFieldsBeforeSearch();

                // Show error message if validation fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ReservationID error");
                alert.setHeaderText("Reservartion search Failed");
                alert.setContentText("Please enter valid reservation ID!");
                alert.showAndWait();
            }
        } else {
            clearFieldsBeforeSearch();

            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ReservationID error");
            alert.setHeaderText("Reservartion search Failed");
            alert.setContentText("Please enter valid reservation ID!");
            alert.showAndWait();
        }


    }

    @FXML
    void txtResIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        idSearch();
    }

    @FXML
    void btnViewReservationsOnAction(ActionEvent event) throws IOException {
        FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/view/ViewReservationForm.fxml"));
        Parent customerRoot = customerLoader.load();
        rootNode.getChildren().clear();
        rootNode.getChildren().add(customerRoot);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setTitle("View Reservations");
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (isQtyValied()) {

            if(!lblProductId.getText().isEmpty()){


                int pId = parseInt(lblProductId.getText());
                String pName = cmbProductName.getValue();
                String pColor = cmbProductColor.getValue();
                double unitPrice = Double.parseDouble((lblUnitPrice.getText()));
                int qty = parseInt(txtQty.getText());
                double total = qty * unitPrice;


                // Create a remove button for the cart item
                JFXButton btnRemove = new JFXButton("Remove");
                btnRemove.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if(type.orElse(no) == yes) {
                        int selectedIndex = tblReservation.getSelectionModel().getSelectedIndex();
                        obList.remove(selectedIndex);

                        tblReservation.refresh();
                        calculateNetTotal();
                    }
                });

                // Create a new cart item and add it to the list
                ReservationTm productItem = new ReservationTm(pId, pName, pColor, unitPrice, qty, total, btnRemove);
                obList.add(productItem);

                // Refresh the cart table with the updated list
                tblReservation.setItems(obList);
                removeForNewItem();
                calculateNetTotal();

            } else {
                // Show error message if validation fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Validation Failed");
                alert.setContentText("Please select product details!");
                alert.showAndWait();
            }

        } else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in the qty field number only.");
            alert.showAndWait();
        }



    }

    @FXML
    void btnReservedOnAction(ActionEvent event) throws SQLException, JRException {

        // if කිහිපයක් දාන්න හේතුව error messages වෙන වෙනම පෙන්වන්න.

        if (lblCustomerName.getText() != null && !lblCustomerName.getText().isEmpty()) {

            LocalDate selectedDate = dpReturnDate.getValue();
            if (selectedDate != null && !selectedDate.equals(LocalDate.of(1970, 1, 1))) {

                if (!tblReservation.getItems().isEmpty()) {

                    if (isValied()) {

                        int resId = parseInt(lblReservationId.getText());
                        String cusId = lblCustomerId.getText();
                        int paymentId = parseInt(lblPaymentId.getText());
                        Date resDate = Date.valueOf(lblReservationDate.getText());
                        Date dpReturnDateValue = Date.valueOf(dpReturnDate.getValue());
                        String status = "Incomplete";
                        double netTotal = Double.parseDouble(lblNetTotal.getText());
                        String paymentType = cmbPaymentType.getValue();

                        var reservation = new Reservation(resId, cusId, paymentId, resDate, dpReturnDateValue, status);

                        List<ReservationDetails> rdList = new ArrayList<>();

                        for (int i = 0; i < tblReservation.getItems().size(); i++) {
                            ReservationTm tm = obList.get(i);

                            ReservationDetails rd = new ReservationDetails(
                                    resId,
                                    tm.getProductId(),
                                    tm.getQty(),
                                    tm.getTotal()
                            );

                            rdList.add(rd);
                        }
                        PlaceReservation pr = new PlaceReservation(reservation, rdList);

                        boolean isPlaced = placeReservationBO.placeReservation(pr, netTotal, paymentType);

                        if(isPlaced) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
                            reservedBill();
                            clearFields();
                         } else {
                             new Alert(Alert.AlertType.WARNING, "Order Placed Unsuccessfully!").show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Validation Error");
                        alert.setHeaderText("Validation Failed");
                        alert.setContentText("Please fill in all fields correctly.");
                        alert.showAndWait();
                    }

                } else {
                    // Show error message if table is empty
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Table Error");
                    alert.setHeaderText("Table details Failed");
                    alert.setContentText("Please add to cart 1 or more items!");
                    alert.showAndWait();
                }

            } else {
                // DatePicker is null or empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Date Error");
                alert.setHeaderText("Return Date not selected");
                alert.setContentText("Please select a return date.");
                alert.showAndWait();
            }

        } else {
            // Label text is null
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer Error");
            alert.setHeaderText("Customer not found!");
            alert.setContentText("Please fill in the NIC correctly.");
            alert.showAndWait();
        }
    }

    public void removeForNewItem() {
        cmbProductName.setValue(null);
        cmbProductColor.setValue(null);
        cmbSize.setValue(null);
        lblQtyOnHand.setText("");
        txtQty.setText("");
        lblUnitPrice.setText("");
        lblProductId.setText("");
    }

    private void setCellValueFactory() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colProductColor.setCellValueFactory(new PropertyValueFactory<>("productColor"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void setCmbStatus() {
        ObservableList<String> paymentType = FXCollections.observableArrayList();
        cmbStatus.setValue("Incomplete");

        paymentType.add("Complete");
        paymentType.add("Incomplete");

        cmbStatus.setItems(paymentType);
    }

    private void setPaymentType() {
        ObservableList<String> paymentType = FXCollections.observableArrayList();
        cmbPaymentType.setValue("Cash");

        paymentType.add("Cash");
        paymentType.add("Card");

        cmbPaymentType.setItems(paymentType);
    }

    private void getCurrentPaymentId() {
        try {
            int currentId = Integer.parseInt(paymentDAO.generateNewID());

            int nextPaymentId = 0;
            if (currentId != 0){
                nextPaymentId = currentId + 1;
            }
            lblPaymentId.setText(String.valueOf(nextPaymentId));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getProductName() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = productBO.getProductName();

            obList.addAll(codeList);
            cmbProductName.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        lblCustomerName.setText(null);
        lblCustomerId.setText(null);

        String cusNic = txtNic.getText();

        List<String> list  = customerDAO.customerSearch(cusNic);
        if (list != null) {
            lblCustomerName.setText(list.get(0));
            lblCustomerId.setText(list.get(1));

        }else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    public void txtNicOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        lblCustomerName.setText(null);
        lblCustomerId.setText(null);

        String cusNic = txtNic.getText();

        List<String> list  = customerDAO.customerSearch(cusNic);
        if (list != null) {
            lblCustomerName.setText(list.get(0));
            lblCustomerId.setText(list.get(1));

        }else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    int currentReservationIdForBillMethod = 0;

    private void getCurrentReservationId() {
        try {
            int currentId = Integer.parseInt(placeReservationBO.generateNewID());
            currentReservationIdForBillMethod = currentId;

            int nextReservationId = generateNextReservationId(currentId);
            lblReservationId.setText(String.valueOf(nextReservationId));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private int generateNextReservationId(int currentId) {
        if(currentId != 0 ) {
                return  ++currentId ;
        }
        return 1;
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblReservationDate.setText(String.valueOf(now));
    }

    @FXML
    public void btnClearForNewItem(ActionEvent actionEvent) {
        removeForNewItem();
    }

    private void calculateNetTotal() {
        double netTotal = 0;
        for (ReservationTm item : obList) {
            netTotal += item.getTotal();
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        initialize();
    }

    private void clearFields() {

        txtNic.setText("");
        txtQty.setText("");
        lblProductId.setText("");
        dpReturnDate.setValue(null);
        lblQtyOnHand.setText(null);
        cmbSize.setValue(null);
        cmbProductName.setValue(null);
        cmbProductColor.setValue(null);
        lblUnitPrice.setText("");
        tblReservation.getItems().clear();
        obList.removeAll();
        lblCustomerId.setText(null);
        lblCustomerName.setText("");
        lblNetTotal.setText(null);
        lblReservationId.setText(null);
        txtReservationId.setText(null);

        setDate();
        getCurrentReservationId();
        getProductName();
        setPaymentType();
        getCurrentPaymentId();
        setCmbStatus();
        setCellValueFactory();

        txtNic.setStyle(null);
        txtQty.setStyle(null);
        lblReservationId.setStyle(null);
        txtReservationId.setStyle(null);

    }

    @FXML
    void cmbProductColorOnAction(ActionEvent event) {
        getProductSize();
    }

    public void getProductSize() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        String productName = cmbProductName.getValue();
        String productColor = cmbProductColor.getValue();
        try {
            List<String> codeList = productDAO.getProductSize(productName, productColor);

            obList.addAll(codeList);
            cmbSize.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void getQtyOnHand(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        String name = (String) cmbProductName.getValue();
        String color = (String) cmbProductColor.getValue();
        String size = (String) cmbSize.getValue();

        try {
            List<String> qtyList = productBO.getQtyOnHand(name, color, size);

            if (!qtyList.isEmpty()) { // Check if the list is not empty
                lblQtyOnHand.setText(qtyList.get(0));
                lblProductId.setText(qtyList.get(1));
                lblUnitPrice.setText(qtyList.get(2));
            } else {
                // Handle the case where qtyList is empty
                lblQtyOnHand.setText(null);
                lblProductId.setText(null);
                lblUnitPrice.setText(null);
            }
        } catch (SQLException e) {
            // Handle the SQL exception appropriately
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbProductNameOnAction(ActionEvent actionEvent) {
        getProductColors();
    }

    public void getProductColors() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        String productName = cmbProductName.getValue();
        try {
            List<String> codeList = productBO.getProductColor(productName);

            obList.addAll(codeList);
            cmbProductColor.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cmbSizeOnAction(ActionEvent actionEvent) {
        getQtyOnHand();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String resId = (lblReservationId.getText());
        String proId = (lblProductId.getText());

        boolean isUpdateStatus = placeReservationBO.update(new ReservationDTO(resId));

        boolean isReturnUpdateQty = false;

        for (int i = 0; i < tblReservation.getItems().size(); i++) {
            ReservationTm tm = obList.get(i);
            isReturnUpdateQty = productDAO.returnUpdateQty(tm.getProductId(), tm.getQty());
        }

        if(isReturnUpdateQty && isUpdateStatus) {
            new Alert(Alert.AlertType.CONFIRMATION, "Reservation Completed!").show();
            clearFields();
        } else {
            new Alert(Alert.AlertType.WARNING, "Reservation Completed Unsuccessfully!").show();
        }
    }





    public void btnBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        if (isResIdValied()) {
            int resId = Integer.parseInt(txtReservationId.getText());
            if (currentReservationIdForBillMethod >= resId) {

                JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/reservationBill.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                Map<String, Object> data = new HashMap<>();
                data.put("reservationID", txtReservationId.getText());

                JasperPrint jasperPrint =
                        JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
                JasperViewer.viewReport(jasperPrint, false);
            }else {


                // Show error message if validation fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ReservationID error");
                alert.setHeaderText("Reservartion search Failed");
                alert.setContentText("Please enter valid reservation ID!");
                alert.showAndWait();
            }
        }else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ReservationID error");
            alert.setHeaderText("Reservartion search Failed");
            alert.setContentText("Please enter valid reservation ID!");
            alert.showAndWait();
        }
    }
    private void clearFieldsBeforeSearch() {

        txtNic.setText("");
        txtQty.setText("");
        lblProductId.setText("");
        dpReturnDate.setValue(null);
        lblQtyOnHand.setText(null);
        cmbSize.setValue(null);
        cmbProductName.setValue(null);
        cmbProductColor.setValue(null);
        lblUnitPrice.setText("");
        tblReservation.getItems().clear();
        obList.removeAll();
        lblCustomerId.setText(null);
        lblCustomerName.setText("");
        lblNetTotal.setText(null);
        lblReservationId.setText(null);

        setDate();
        getCurrentReservationId();
        getProductName();
        setPaymentType();
        getCurrentPaymentId();
        setCmbStatus();
        setCellValueFactory();

        txtNic.setStyle(null);
        txtQty.setStyle(null);
        lblReservationId.setStyle(null);
        txtReservationId.setStyle(null);

    }




    public void reservedBill() throws JRException, SQLException {
//        int nicFieldId = Integer.parseInt(txtReservationId.getText());

        if (!tblReservation.getItems().isEmpty()) {

            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/reservationBill.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String, Object> data = new HashMap<>();
            data.put("reservationID", lblReservationId.getText());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        }else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ReservationID error");
            alert.setHeaderText("Reservartion search Failed");
            alert.setContentText("Please enter valid reservation ID!");
            alert.showAndWait();
        }

    }








    public void idKeyRelaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.RESID, txtReservationId);
    }

    public void nicKeyRelaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NIC, txtNic);
    }

    public void qtyKeyRelaseAction(javafx.scene.input.KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.QTY, txtQty);
    }

    public boolean isValied(){
        boolean nicValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.NIC, txtNic);

        return nicValid;
    }

    public boolean isResIdValied(){
        boolean nicValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.RESID, txtReservationId);

        return nicValid;
    }
    public boolean isQtyValied(){
        boolean qtyValid = Regex.setTextColor(lk.ijse.tailorsystem.Util.TextField.QTY, txtQty);

        return qtyValid;
    }

}
