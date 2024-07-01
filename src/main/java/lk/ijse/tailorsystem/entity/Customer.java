package lk.ijse.tailorsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Data

public class Customer { // customer representation

    private String customerID;

    private String NIC;

    private String customerName;

    private String customerAddress;

    private String customerTel;

    private String userID;

    private String status;

    public Customer(String customerId, String customerName, String customerAddress, String customerTel, String status) {
        this.customerID = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerTel = customerTel;
        this.status = status;
    }

    public Customer(String customerID, String nic, String customerName, String customerAddress, String customerTel, String status) {
        this.customerID = customerID;
        this.NIC = nic;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerTel = customerTel;
        this.status = status;
    }
}


