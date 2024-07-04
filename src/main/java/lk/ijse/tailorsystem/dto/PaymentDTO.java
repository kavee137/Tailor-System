package lk.ijse.tailorsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class PaymentDTO {
    private int paymentID;
    private String paymentType;
    private double price;
}
