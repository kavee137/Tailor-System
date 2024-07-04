package lk.ijse.tailorsystem.bo.custom;

import lk.ijse.tailorsystem.bo.SuperBO;
import lk.ijse.tailorsystem.dto.ProductDTO;
import lk.ijse.tailorsystem.entity.Product;
import lk.ijse.tailorsystem.entity.ReservationDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductBO extends SuperBO {


    List<String> getQtyOnHand(String name, String color, String size) throws SQLException, ClassNotFoundException;

    String getTotalProduct() throws SQLException, ClassNotFoundException;

    boolean reservatioQtyUpdate(List<ReservationDetails> prList) throws SQLException, ClassNotFoundException;

    boolean updateQty(int qty, int productID) throws SQLException, ClassNotFoundException;

    ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(Product product) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    boolean add(ProductDTO product) throws SQLException, ClassNotFoundException;

    Product search(Object... args) throws SQLException, ClassNotFoundException;

    List<String> getProductSize(String color, String name) throws SQLException, ClassNotFoundException;

    List<String> getProductColor(String productName) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    List<String> getProductName() throws SQLException, ClassNotFoundException;
}
