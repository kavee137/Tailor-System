package lk.ijse.tailorsystem.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class ReservationTm {
    private int productId;
    private String productName;
    private String productColor;
    private double unitPrice;
    private int qty;
    private double total;
    private JFXButton btnRemove;
}
