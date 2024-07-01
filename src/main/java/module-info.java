module lk.ijse.tailorsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.datatransfer;
    requires com.jfoenix;
    requires jasperreports;
    requires spring.web;
    requires javafx.graphics;


    opens lk.ijse.tailorsystem to javafx.fxml;
    exports lk.ijse.tailorsystem;
    exports lk.ijse.tailorsystem.controller;
    opens lk.ijse.tailorsystem.controller to javafx.fxml;
    opens lk.ijse.tailorsystem.view.tdm to javafx.base;
}