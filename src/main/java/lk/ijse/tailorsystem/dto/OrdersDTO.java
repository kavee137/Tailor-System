package lk.ijse.tailorsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data   // getter setter and tostring
public class OrdersDTO {
    private String orderID;
    private String customerID;
    private int paymentID;
    private String employeeID;
    private String orderDate;
    private String measurements;
    private String description;
    private String returnDate;
    private String fabricSize;
}
