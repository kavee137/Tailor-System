package lk.ijse.tailorsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDTO { // customer representation

    private String customerID;

    private String NIC;

    private String customerName;

    private String customerAddress;

    private String customerTel;

    private String userID;

    private String status;

}


