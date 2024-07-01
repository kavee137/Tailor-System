package lk.ijse.tailorsystem.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeTm {
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
