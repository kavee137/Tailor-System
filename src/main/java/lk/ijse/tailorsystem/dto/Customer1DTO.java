package lk.ijse.tailorsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer1DTO {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerTel;
    private String status;
}
