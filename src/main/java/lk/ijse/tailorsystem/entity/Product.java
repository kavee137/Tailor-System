package lk.ijse.tailorsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {
    private String productID;
    private String productName;
    private String productColor;
    private String productSize;
    private String unitPrice;
    private String qtyOnHand;

    public Product(String productID, String productColor, String productSize, int qtyOnHand) {
        this.productID = productID;
        this.productColor = productColor;
        this.productSize = productSize;
        this.qtyOnHand = String.valueOf(qtyOnHand);
    }

    public Product(String productID, String unitPrice, String qtyOnHand) {
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public Product(int productID, String productName, String productColor, String productSize, double unitPrice, int qtyOnHand) {
        this.productID = String.valueOf(productID);
        this.productName = productName;
        this.productColor = productColor;
        this.productSize = productSize;
        this.unitPrice = String.valueOf(unitPrice);
        this.qtyOnHand = String.valueOf(qtyOnHand);

    }
}
