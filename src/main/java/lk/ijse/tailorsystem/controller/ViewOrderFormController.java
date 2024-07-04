//package lk.ijse.tailorshopmanagementsystem.controller;
//
//public class ViewOrderFormController {

//
//    SELECT
//    c.NIC,
//    c.customerName,
//    c.customerAddress,
//    c.customerTel,
//    o.orderID,
//    o.employeeID,  -- Added employeeID
//    o.orderDate,
//    o.returnDate,
//    o.status AS orderStatus,
//    e.employeeName,
//    od.fabricID,
//    od.description,
//    od.measurements,
//    od.fabricSize,
//    od.unitPrice,
//    od.qty,
//    od.total,
//    f.fabricName,
//    f.fabricColor,
//    p.paymentID,
//    p.paymentType,
//    p.price AS paymentPrice
//            FROM
//    customer c
//    JOIN
//    orders o ON c.customerID = o.customerID
//            JOIN
//    employee e ON o.employeeID = e.employeeID
//            JOIN
//    orderDetails od ON o.orderID = od.orderID
//            JOIN
//    fabric f ON od.fabricID = f.fabricID
//            JOIN
//    payment p ON o.paymentID = p.paymentID;

//    SELECT c.NIC, c.customerName, c.customerAddress, c.customerTel, o.orderID, o.employeeID, o.orderDate, o.returnDate, o.status AS orderStatus, e.employeeName, od.fabricID, od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, f.fabricName, f.fabricColor, p.paymentID, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID;

//    SELECT o.orderID, p.paymentID, c.NIC, o.orderDate, o.returnDate, o.status AS orderStatus, c.customerName, c.customerAddress, c.customerTel , o.employeeID,  e.employeeName, od.fabricID, f.fabricName, f.fabricColor,od.description, od.measurements, od.fabricSize, od.unitPrice, od.qty, od.total, p.paymentType, p.price AS paymentPrice FROM customer c JOIN orders o ON c.customerID = o.customerID JOIN employee e ON o.employeeID = e.employeeID JOIN orderDetails od ON o.orderID = od.orderID JOIN fabric f ON od.fabricID = f.fabricID JOIN payment p ON o.paymentID = p.paymentID;

//}


package lk.ijse.tailorsystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tailorsystem.bo.BOFactory;
import lk.ijse.tailorsystem.bo.custom.ProductBO;
import lk.ijse.tailorsystem.bo.custom.ViewOrderBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.EmployeeDAO;
import lk.ijse.tailorsystem.dao.custom.ViewOrderDAO;
import lk.ijse.tailorsystem.view.tdm.OrderViewTm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ViewOrderFormController {

    @FXML
    private JFXComboBox<String> cmbTailorId;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colFabricColor;

    @FXML
    private TableColumn<?, ?> colFabricId;

    @FXML
    private TableColumn<?, ?> colFabricName;

    @FXML
    private TableColumn<?, ?> colFabricSize;

    @FXML
    private TableColumn<?, ?> colMeasurements;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentType;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private DatePicker dpDateFrom;

    @FXML
    private DatePicker dpDateTo;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<OrderViewTm> tblViewOrders;
    private ObservableList<OrderViewTm> obList = FXCollections.observableArrayList();

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    ViewOrderBO viewOrderBO  = (ViewOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VIEWORDER);




    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllProcessingOrders();
        getEmployeeId();
    }

    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = employeeDAO.getTailorId();

            obList.addAll(idList);
            cmbTailorId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("customerTel"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colFabricId.setCellValueFactory(new PropertyValueFactory<>("fabricID"));
        colFabricName.setCellValueFactory(new PropertyValueFactory<>("fabricName"));
        colFabricColor.setCellValueFactory(new PropertyValueFactory<>("fabricColor"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMeasurements.setCellValueFactory(new PropertyValueFactory<>("measurements"));
        colFabricSize.setCellValueFactory(new PropertyValueFactory<>("fabricSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("paymentPrice"));
    }

    public void loadAllProcessingOrders() throws SQLException, ClassNotFoundException {
        tblViewOrders.getItems().clear();

        ResultSet reservationDetails = viewOrderBO.getProcessingOrderDetails();

        while (reservationDetails.next()) {
            OrderViewTm r = new OrderViewTm();
            r.setOrderID(reservationDetails.getString("orderID"));
            r.setPaymentID(reservationDetails.getString("paymentID"));
            r.setNIC(reservationDetails.getString("NIC"));
            r.setOrderDate(reservationDetails.getDate("orderDate"));
            r.setReturnDate(reservationDetails.getDate("returnDate"));
            r.setOrderStatus(reservationDetails.getString("orderStatus"));
            r.setCustomerName(reservationDetails.getString("customerName"));
            r.setCustomerAddress(reservationDetails.getString("customerAddress"));
            r.setCustomerTel(reservationDetails.getString("customerTel"));
            r.setEmployeeID(reservationDetails.getString("employeeID"));
            r.setEmployeeName(reservationDetails.getString("employeeName"));
            r.setFabricID(reservationDetails.getString("fabricID"));
            r.setFabricName(reservationDetails.getString("fabricName"));
            r.setFabricColor(reservationDetails.getString("fabricColor"));
            r.setDescription(reservationDetails.getString("description"));
            r.setMeasurements(reservationDetails.getString("measurements"));
            r.setFabricSize(reservationDetails.getDouble("fabricSize"));
            r.setUnitPrice(reservationDetails.getDouble("unitPrice"));
            r.setQty(reservationDetails.getInt("qty"));
            r.setTotal(reservationDetails.getDouble("total"));
            r.setPaymentType(reservationDetails.getString("paymentType"));
            r.setPaymentPrice(reservationDetails.getDouble("paymentPrice"));

            obList.add(r);
        }
        tblViewOrders.setItems(obList);
    }
    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        dpDateTo.setValue(null);
        dpDateFrom.setValue(null);
        cmbTailorId.setValue(null);

        initialize();
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (dpDateFrom.getValue() != null && dpDateTo.getValue() != null) {

            Date dateFrom = Date.valueOf(dpDateFrom.getValue());
            Date dateTo = Date.valueOf(dpDateTo.getValue());

            tblViewOrders.getItems().clear();

            ResultSet orderDetails = viewOrderBO.getOrderDetails(dateFrom, dateTo);
            while (orderDetails.next()) {
                OrderViewTm r = new OrderViewTm();
                r.setOrderID(orderDetails.getString("orderID"));
                r.setPaymentID(orderDetails.getString("paymentID"));
                r.setNIC(orderDetails.getString("NIC"));
                r.setOrderDate(orderDetails.getDate("orderDate"));
                r.setReturnDate(orderDetails.getDate("returnDate"));
                r.setOrderStatus(orderDetails.getString("orderStatus"));
                r.setCustomerName(orderDetails.getString("customerName"));
                r.setCustomerAddress(orderDetails.getString("customerAddress"));
                r.setCustomerTel(orderDetails.getString("customerTel"));
                r.setEmployeeID(orderDetails.getString("employeeID"));
                r.setEmployeeName(orderDetails.getString("employeeName"));
                r.setFabricID(orderDetails.getString("fabricID"));
                r.setFabricName(orderDetails.getString("fabricName"));
                r.setFabricColor(orderDetails.getString("fabricColor"));
                r.setDescription(orderDetails.getString("description"));
                r.setMeasurements(orderDetails.getString("measurements"));
                r.setFabricSize(orderDetails.getDouble("fabricSize"));
                r.setUnitPrice(orderDetails.getDouble("unitPrice"));
                r.setQty(orderDetails.getInt("qty"));
                r.setTotal(orderDetails.getDouble("total"));
                r.setPaymentType(orderDetails.getString("paymentType"));
                r.setPaymentPrice(orderDetails.getDouble("paymentPrice"));

                obList.add(r);
            }

        } else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Order Search error");
            alert.setHeaderText("Order search Failed");
            alert.setContentText("Please select return date period!");
            alert.showAndWait();
        }

    }
    @FXML
    void btnShowAllOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        tblViewOrders.getItems().clear();

        ResultSet orderDetails = viewOrderBO.getDetails();
        while (orderDetails.next()) {
            OrderViewTm r = new OrderViewTm();
            r.setOrderID(orderDetails.getString("orderID"));
            r.setPaymentID(orderDetails.getString("paymentID"));
            r.setNIC(orderDetails.getString("NIC"));
            r.setOrderDate(orderDetails.getDate("orderDate"));
            r.setReturnDate(orderDetails.getDate("returnDate"));
            r.setOrderStatus(orderDetails.getString("orderStatus"));
            r.setCustomerName(orderDetails.getString("customerName"));
            r.setCustomerAddress(orderDetails.getString("customerAddress"));
            r.setCustomerTel(orderDetails.getString("customerTel"));
            r.setEmployeeID(orderDetails.getString("employeeID"));
            r.setEmployeeName(orderDetails.getString("employeeName"));
            r.setFabricID(orderDetails.getString("fabricID"));
            r.setFabricName(orderDetails.getString("fabricName"));
            r.setFabricColor(orderDetails.getString("fabricColor"));
            r.setDescription(orderDetails.getString("description"));
            r.setMeasurements(orderDetails.getString("measurements"));
            r.setFabricSize(orderDetails.getDouble("fabricSize"));
            r.setUnitPrice(orderDetails.getDouble("unitPrice"));
            r.setQty(orderDetails.getInt("qty"));
            r.setTotal(orderDetails.getDouble("total"));
            r.setPaymentType(orderDetails.getString("paymentType"));
            r.setPaymentPrice(orderDetails.getDouble("paymentPrice"));

            obList.add(r);
        }
    }
    @FXML
    public void btnShowCompletedOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        tblViewOrders.getItems().clear();

        ResultSet orderDetails = viewOrderBO.getCompleted();
        while (orderDetails.next()) {
            OrderViewTm r = new OrderViewTm();
            r.setOrderID(orderDetails.getString("orderID"));
            r.setPaymentID(orderDetails.getString("paymentID"));
            r.setNIC(orderDetails.getString("NIC"));
            r.setOrderDate(orderDetails.getDate("orderDate"));
            r.setReturnDate(orderDetails.getDate("returnDate"));
            r.setOrderStatus(orderDetails.getString("orderStatus"));
            r.setCustomerName(orderDetails.getString("customerName"));
            r.setCustomerAddress(orderDetails.getString("customerAddress"));
            r.setCustomerTel(orderDetails.getString("customerTel"));
            r.setEmployeeID(orderDetails.getString("employeeID"));
            r.setEmployeeName(orderDetails.getString("employeeName"));
            r.setFabricID(orderDetails.getString("fabricID"));
            r.setFabricName(orderDetails.getString("fabricName"));
            r.setFabricColor(orderDetails.getString("fabricColor"));
            r.setDescription(orderDetails.getString("description"));
            r.setMeasurements(orderDetails.getString("measurements"));
            r.setFabricSize(orderDetails.getDouble("fabricSize"));
            r.setUnitPrice(orderDetails.getDouble("unitPrice"));
            r.setQty(orderDetails.getInt("qty"));
            r.setTotal(orderDetails.getDouble("total"));
            r.setPaymentType(orderDetails.getString("paymentType"));
            r.setPaymentPrice(orderDetails.getDouble("paymentPrice"));

            obList.add(r);
        }
    }

    public void cmbTailorIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        getEmployeeOrders();
    }

    public void getEmployeeOrders() throws SQLException, ClassNotFoundException {
        tblViewOrders.getItems().clear();

        ResultSet orderDetails = viewOrderBO.getEmployeeTasks(cmbTailorId.getValue());

        while (orderDetails.next()) {
            OrderViewTm r = new OrderViewTm();
            r.setOrderID(orderDetails.getString("orderID"));
            r.setPaymentID(orderDetails.getString("paymentID"));
            r.setNIC(orderDetails.getString("NIC"));
            r.setOrderDate(orderDetails.getDate("orderDate"));
            r.setReturnDate(orderDetails.getDate("returnDate"));
            r.setOrderStatus(orderDetails.getString("orderStatus"));
            r.setCustomerName(orderDetails.getString("customerName"));
            r.setCustomerAddress(orderDetails.getString("customerAddress"));
            r.setCustomerTel(orderDetails.getString("customerTel"));
            r.setEmployeeID(orderDetails.getString("employeeID"));
            r.setEmployeeName(orderDetails.getString("employeeName"));
            r.setFabricID(orderDetails.getString("fabricID"));
            r.setFabricName(orderDetails.getString("fabricName"));
            r.setFabricColor(orderDetails.getString("fabricColor"));
            r.setDescription(orderDetails.getString("description"));
            r.setMeasurements(orderDetails.getString("measurements"));
            r.setFabricSize(orderDetails.getDouble("fabricSize"));
            r.setUnitPrice(orderDetails.getDouble("unitPrice"));
            r.setQty(orderDetails.getInt("qty"));
            r.setTotal(orderDetails.getDouble("total"));
            r.setPaymentType(orderDetails.getString("paymentType"));
            r.setPaymentPrice(orderDetails.getDouble("paymentPrice"));

            obList.add(r);
        }
        tblViewOrders.setItems(obList);
    }

}
