package lk.ijse.tailorsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReservationDTO {
    private int reservationID;
    private String customerID;
    private int paymentID;
    private Date reservationDate;
    private Date returnDate;
    private String status;

    public ReservationDTO(String resId) {
        this.reservationID = Integer.parseInt(resId);
    }
}

//
//
//
//reservationID   | int         | NO   | PRI | NULL       | auto_increment |
//        | customerID      | varchar(20) | YES  | MUL | NULL       |                |
//        | paymentID       | int         | YES  | MUL | NULL       |                |
//        | reservationDate | date        | YES  |     | NULL       |                |
//        | returnDate      | date        | YES  |     | NULL       |                |
//        | status          | varchar(20) | YES  |     | Incomplete |