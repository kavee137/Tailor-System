package lk.ijse.tailorsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode

public class OrderDTO {

    private String orderID;
    private String customerID;
    private int paymentID;
    private String employeeID;
    private Date orderDate;
    private Date returnDate;
    private String status;

    public OrderDTO(String orderId, String status) {
        this.orderID = orderId;
        this.status = status;
    }
}
