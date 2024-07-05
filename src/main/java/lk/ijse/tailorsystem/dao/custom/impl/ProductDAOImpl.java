package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.ProductDAO;
import lk.ijse.tailorsystem.db.DbConnection;
import lk.ijse.tailorsystem.entity.Product;
import lk.ijse.tailorsystem.entity.ReservationDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {


    @Override
    public  List<String> getQtyOnHand(String name, String color, String size) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT qtyOnHand, productID, unitPrice FROM product WHERE productName = ? AND productColor = ? AND productSize = ?", name, color, size);

        List<String> qty = new ArrayList<>();
        while (resultSet.next()) {
            qty.add(resultSet.getString(1));
            qty.add(resultSet.getString(2));
            qty.add(resultSet.getString(3));
        }
        return qty;
    }

    @Override
    public boolean returnUpdateQty(int proId, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE product SET qtyOnHand = qtyOnHand + ? WHERE productID = ?", qty, proId);
    }


    @Override
    public String getTotalProduct() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS allProduct FROM product");

        if(resultSet.next()) {
            return resultSet.getString(1);
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

            productList.add(new Product(productID, productName, productColor, productSize, unitPrice, qtyOnHand));
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
    public List<String> getProductSize(String name, String color) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT productSize FROM product WHERE productName = ? AND productColor = ?", name, color);
        List<String> codeList = new ArrayList<>();

        while (resultSet.next()) {
            codeList.add(resultSet.getString("productSize"));
        }
        return codeList;
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
    public String generateNewID() throws SQLException, ClassNotFoundException{
        ResultSet resultSet = SQLUtil.execute("SELECT productID FROM product ORDER BY productID DESC LIMIT 1");
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
