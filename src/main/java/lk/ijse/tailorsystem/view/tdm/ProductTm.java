package lk.ijse.tailorsystem.view.tdm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTm {
    private int productID;
    private String productName;
    private String productColor;
    private String productSize;
    private Double unitPrice;
    private int qtyOnHand;

    public ProductTm(String productID, String productName, String productColor, String productSize, String unitPrice, String qtyOnHand) {
        this.productID = Integer.parseInt(productID);
        this.productName = productName;
        this.productColor = productColor;
        this.productSize = productSize;
        this.unitPrice = Double.valueOf(unitPrice);
        this.qtyOnHand = Integer.parseInt(qtyOnHand);
    }
}


