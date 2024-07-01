package lk.ijse.tailorsystem.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class PaymentTm {
    private int reservationID;
    private String orderID;
    private int paymentID;
    private String paymentType;
    private double price;

}
