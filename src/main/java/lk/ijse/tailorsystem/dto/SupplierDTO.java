package lk.ijse.tailorsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class SupplierDTO {
    private String supplierID;
    private String NIC;
    private String supplierName;
    private String supplierAddress;
    private String supplierContact;
    private String status;
}
