package lk.ijse.tailorsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class ReservationDetailsDTO {
    private int reservationID;
    private int productID;
    private int qty;
    private double total;
}



//
//+---------------+------+------+-----+---------+-------+
//        | Field         | Type | Null | Key | Default | Extra |
//        +---------------+------+------+-----+---------+-------+
//        | reservationID | int  | NO   | PRI | NULL    |       |
//        | productID     | int  | NO   | PRI | NULL    |       |
//        | qty           | int  | YES  |     | NULL    |       |
//        +---------------+------+------+-----+---------+-------+