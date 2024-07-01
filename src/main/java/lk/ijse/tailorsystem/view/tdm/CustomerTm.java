package lk.ijse.tailorsystem.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class CustomerTm {
    private String customerID;
    private String NIC;
    private String customerName;
    private String customerAddress;
    private String customerTel;
    private String userID;
    private String status;
}