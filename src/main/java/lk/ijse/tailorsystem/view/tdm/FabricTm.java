package lk.ijse.tailorsystem.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class FabricTm {
    private String fabricID;
    private String supplierID;
    private String fabricName;
    private String fabricColor;
    private int fabricQtyOnHand;

}
//
//
//mysql> desc fabric;
//+-----------------+--------------+------+-----+---------+-------+
//        | Field           | Type         | Null | Key | Default | Extra |
//        +-----------------+--------------+------+-----+---------+-------+
//        | fabricID        | varchar(20)  | NO   | PRI | NULL    |       |
//        | supplierID      | varchar(20)  | YES  | MUL | NULL    |       |
//        | fabricName      | varchar(100) | YES  |     | NULL    |       |
//        | fabricColor     | varchar(50)  | YES  |     | NULL    |       |
//        | fabricQtyOnHand | int          | YES  |     | NULL    |       |
//        +-----------------+--------------+------+-----+---------+-------+