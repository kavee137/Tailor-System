package lk.ijse.tailorsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class EmployeeDTO {
    private String employeeID;
    private String userID;
    private String NIC;
    private String position;
    private String employeeName;
    private String phoneNumber;
    private String employeeAddress;
    private String salary;
    private String status;

}
