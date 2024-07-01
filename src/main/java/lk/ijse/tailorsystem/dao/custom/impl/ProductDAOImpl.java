package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.ProductDAO;
import lk.ijse.tailorsystem.dto.ProductDTO;
import lk.ijse.tailorsystem.entity.Product;
import lk.ijse.tailorsystem.entity.ReservationDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public String getTotalProduct() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS allProduct FROM product");
        if(resultSet.next()) {
            String count = resultSet.getString(1);
            return count;
        }
        return null;
    }

    @Override
    public boolean reservatioQtyUpdate(List<ReservationDetails> prList) throws SQLException, ClassNotFoundException {
        for (ReservationDetails od : prList) {
            boolean isUpdateQty = updateQty(od.getQty(), od.getProductID());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(int qty, int productID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE product SET qtyOnHand = qtyOnHand - ? WHERE productID = ?", qty, productID);
    }



    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM product");

        ArrayList<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            int productID = resultSet.getInt(1);
            String productName = resultSet.getString(2);
            String productColor = resultSet.getString(3);
            String productSize = resultSet.getString(4);
            double unitPrice = resultSet.getDouble(5);
            int qtyOnHand = resultSet.getInt(6);

            Product product = new Product(productID, productName, productColor, productSize, unitPrice, qtyOnHand);
            productList.add(product);
        }
        return productList;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM product WHERE productID = ?", id);
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE product SET productID = ?, productName = ?, productColor = ?, productSize = ?, unitPrice = ?, qtyOnHand = ? WHERE  productID =  ?", product.getProductID(), product.getProductName(), product.getProductColor(), product.getProductSize(), product.getUnitPrice(), product.getQtyOnHand(), product.getProductID());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean add(Product product) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO product VALUES(?, ?, ?, ?, ?, ?)", product.getProductID(), product.getProductName(), product.getProductColor(), product.getProductSize(), product.getUnitPrice(), product.getQtyOnHand());
    }

    @Override
    public Product search(Object... args) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT productID, unitPrice, qtyOnHand FROM product WHERE productName = ? AND productColor = ? AND productSize = ?", args);
        if (resultSet.next()) {
            String  productID = resultSet.getString(1);
            String  unitPrice = resultSet.getString(2);
            String  qtyOnHand = resultSet.getString(3);

            return new Product(productID, unitPrice, qtyOnHand);
        }
        return null;
    }

    @Override
    public List<String> getProductSize(String color, String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT productSize FROM product WHERE productName = ? AND productColor = ?", name, color);
        List<String> sizes = new ArrayList<>();

        while (resultSet.next()) {
            sizes.add(String.valueOf(resultSet.getInt("productSize")));
        }

        return sizes;

    }



    @Override
    public List<String> getProductColor(String productName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT productColor FROM product WHERE productName = ?", productName);

        List<String> colorList = new ArrayList<>();
        while (resultSet.next()) {
            colorList.add(resultSet.getString(1));
        }
        return colorList;
    }

    @Override
    public String generateNewID(Object... args) throws SQLException, ClassNotFoundException{
        ResultSet resultSet = SQLUtil.execute("SELECT productID FROM product ORDER BY productID DESC LIMIT 1", args);
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return String.valueOf(1);
    }

    @Override
    public List<String> getProductName() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT productName FROM product");
        List<String> productList = new ArrayList<>();
        while (resultSet.next()) {
            productList.add(resultSet.getString(1));
        }
        return productList;
    }

}
