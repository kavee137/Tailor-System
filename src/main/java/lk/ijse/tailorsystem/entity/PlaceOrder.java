package lk.ijse.tailorsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode

public class PlaceOrder {
    private Order order;
    private List<OrderDetails> odList;
}
