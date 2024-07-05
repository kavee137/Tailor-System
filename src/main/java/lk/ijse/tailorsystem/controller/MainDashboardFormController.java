package lk.ijse.tailorsystem.controller;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.tailorsystem.bo.BOFactory;
import lk.ijse.tailorsystem.bo.custom.MainDashBoardBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.*;
import lk.ijse.tailorsystem.entity.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MainDashboardFormController {


    public Label lblEmployeeCount;
    public Label lblOrderCount;
    public Label lblReservationCount;
    public Label lblTransactionCount;
    public Label lblProductCount;
    public Label lblSupplierCount;
    public Label lblUserCount;

    public Label date;
    public Label time;

    @FXML
    private Label lblBlazerColor;

    @FXML
    private Label lblBlazerId;

    @FXML
    private Label lblBlazerQtyOnHand;

    @FXML
    private Label lblBlazerSize;

    @FXML
    private Label lblTrouserColor;

    @FXML
    private Label lblTrouserId;

    @FXML
    private Label lblTrouserQtyOnHand;

    @FXML
    private Label lblTrouserSize;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private PieChart pieChart;  // This is your FXML PieChart node

    @FXML
    private Label lblCustomerCount;

    MainDashBoardBO mainDashBoardBO  = (MainDashBoardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MAINDASHBOARD);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    public void initialize() throws SQLException, ClassNotFoundException {
        loadActiveCustomerCount();
        loadActiveEmployeeCount();
        loadProcessingOrderCount();
        loadProcessingReservationCount();
        loadTransaction();
        loadProduct();
        loadSupplier();
        loadUser();
        setLowFabricPieChart();
        initializeDateTime();
        setBarChart();
        setMostReservedProducts();
    }

    public void setMostReservedProducts() throws SQLException {
        List<Product> pDetails = mainDashBoardBO.getMostReservedProduct();

        // Assuming the most reserved product is at index 0
        if (!pDetails.isEmpty()) {
            lblBlazerId.setText(pDetails.get(0).getProductID());
            lblBlazerColor.setText(pDetails.get(0).getProductColor());
            lblBlazerSize.setText(pDetails.get(0).getProductSize());
            lblBlazerQtyOnHand.setText(String.valueOf(pDetails.get(0).getQtyOnHand()));

            lblTrouserId.setText(pDetails.get(1).getProductID());
            lblTrouserColor.setText(pDetails.get(1).getProductColor());
            lblTrouserSize.setText(pDetails.get(1).getProductSize());
            lblTrouserQtyOnHand.setText(String.valueOf(pDetails.get(1).getQtyOnHand()));
        } else {
            // Handle case when the list is empty
            new Alert(Alert.AlertType.ERROR, "No data to show most reservation products!").show();
        }
    }

    public void setBarChart() {
        try {
            List<MonthlySales> monthlySales = getMonthlySales();

            // Clear existing data from the chart
            barChart.getData().clear();

            // Define axes
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Month");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Total Sales");

            // Create a series for the data
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Monthly Sales");

            // Add data to the series with animations
            for (MonthlySales sale : monthlySales) {
                XYChart.Data<String, Number> data = new XYChart.Data<>(Integer.toString(sale.getOrderMonth()), sale.getTotalSales());
                series.getData().add(data);

                // Add animations to each bar
                data.nodeProperty().addListener((observable, oldNode, newNode) -> {
                    if (newNode != null) {
                        // Translate Transition
                        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), newNode);
                        translateTransition.setFromY(-newNode.getBoundsInParent().getHeight());
                        translateTransition.setToY(0);

                        // Fade Transition
                        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), newNode);
                        fadeTransition.setFromValue(0);
                        fadeTransition.setToValue(1);

                        // Scale Transition
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), newNode);
                        scaleTransition.setFromX(0);
                        scaleTransition.setFromY(0);
                        scaleTransition.setToX(1);
                        scaleTransition.setToY(1);

                        // Rotate Transition
                        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), newNode);
                        rotateTransition.setByAngle(360);

                        // Parallel Transition to combine all transitions
                        ParallelTransition parallelTransition = new ParallelTransition(translateTransition, fadeTransition, scaleTransition, rotateTransition);

                        parallelTransition.play();
                    }
                });
            }

            // Add the series to the chart
            barChart.getData().add(series);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error retrieving monthly sales data").show();
        }
    }

    private List<MonthlySales> getMonthlySales() throws SQLException {
        return mainDashBoardBO.getMonthlySalesFor2024();
    }

    private void initializeDateTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    date.setText(now.format(dateFormatter));
                    time.setText(now.format(timeFormatter));
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setLowFabricPieChart() {
        try {
            List<Fabric> fabrics = mainDashBoardBO.getLowStockFabrics();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            for (Fabric fabric : fabrics) {
                // Use the fabric name and color as the label and the quantity on hand as the value
                String label = fabric.getFabricName() + " (" + fabric.getFabricColor() + ") " + fabric.getFabricQtyOnHand();
                int value = fabric.getFabricQtyOnHand();
                PieChart.Data data = new PieChart.Data(label, value);
                pieChartData.add(data);

                // Add animations to each data slice
                data.nodeProperty().addListener((observable, oldNode, newNode) -> {
                    if (newNode != null) {
                        // Scale Transition
                        newNode.setScaleX(0);
                        newNode.setScaleY(0);
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), newNode);
                        scaleTransition.setToX(1);
                        scaleTransition.setToY(1);

                        // Fade Transition
                        newNode.setOpacity(0);
                        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), newNode);
                        fadeTransition.setToValue(1);

                        // Rotate Transition
                        newNode.setRotate(0);
                        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), newNode);
                        rotateTransition.setByAngle(360);

                        // Translate Transition
                        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), newNode);
                        translateTransition.setFromY(-20);
                        translateTransition.setToY(0);

                        // Fill Transition (color change effect)
                        FillTransition fillTransition = new FillTransition(Duration.millis(1000));
                        fillTransition.setFromValue(Color.RED);
                        fillTransition.setToValue(Color.BLUE);

                        // Play animations sequentially
                        scaleTransition.play();
                        fadeTransition.play();
                        rotateTransition.play();
                        translateTransition.play();
                        fillTransition.play();
                    }
                });
            }

            pieChart.setData(pieChartData);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }




    private void loadUser() throws SQLException, ClassNotFoundException {
        lblUserCount.setText(userDAO.getTotalUser());
    }

    private void loadSupplier() throws SQLException, ClassNotFoundException {
        lblSupplierCount.setText(supplierDAO.getTotalSuppliers());
    }

    private void loadProduct() throws SQLException, ClassNotFoundException {
        lblProductCount.setText(productDAO.getTotalProduct());
    }

    private void loadTransaction() throws SQLException, ClassNotFoundException {
        lblTransactionCount.setText(paymentDAO.getTotalPayment());
    }

    private void loadProcessingReservationCount() throws SQLException, ClassNotFoundException {
        lblReservationCount.setText(reservationDAO.getReservationCount());
    }

    private void loadProcessingOrderCount() throws SQLException, ClassNotFoundException {
        lblOrderCount.setText(orderDAO.getOrderCount());
    }

    private void loadActiveEmployeeCount() throws SQLException, ClassNotFoundException {
        lblEmployeeCount.setText(employeeDAO.getEmployeeCount());
    }

    private void loadActiveCustomerCount() throws SQLException, ClassNotFoundException {
        lblCustomerCount.setText(customerDAO.getCustomerCount());
    }

}
