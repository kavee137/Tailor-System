package lk.ijse.tailorsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDTO {
    private String productID;
    private String productName;
    private String productColor;
    private String productSize;
    private String unitPrice;
    private String qtyOnHand;

    public ProductDTO(String productID, String productColor, String productSize, int qtyOnHand) {
        this.productID = productID;
        this.productColor = productColor;
        this.productSize = productSize;
        this.qtyOnHand = String.valueOf(qtyOnHand);
    }
}
