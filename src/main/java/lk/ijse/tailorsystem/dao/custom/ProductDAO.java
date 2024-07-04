package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.CrudDAO;
import lk.ijse.tailorsystem.dto.ProductDTO;
import lk.ijse.tailorsystem.entity.Customer;
import lk.ijse.tailorsystem.entity.Product;
import lk.ijse.tailorsystem.entity.ReservationDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {

    List<String> getQtyOnHand(String name, String color, String size) throws SQLException, ClassNotFoundException;

    boolean returnUpdateQty(int proId, int qty) throws SQLException, ClassNotFoundException;

    String getTotalProduct() throws SQLException, ClassNotFoundException;

    boolean reservatioQtyUpdate(List<ReservationDetails> prList) throws SQLException, ClassNotFoundException;

    boolean updateQty(int qty, int productID) throws SQLException, ClassNotFoundException;

    List<String> getProductSize(String color, String name) throws SQLException, ClassNotFoundException;

    List<String> getProductColor(String productName) throws SQLException, ClassNotFoundException;

    List<String> getProductName() throws SQLException, ClassNotFoundException;
}
