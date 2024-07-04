package lk.ijse.tailorsystem.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Payment {
    private int paymentID;
    private String paymentType;
    private double price;
}
