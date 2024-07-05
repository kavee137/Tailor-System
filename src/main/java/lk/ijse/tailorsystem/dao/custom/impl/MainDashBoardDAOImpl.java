package lk.ijse.tailorsystem.dao.custom.impl;

import lk.ijse.tailorsystem.dao.SQLUtil;
import lk.ijse.tailorsystem.dao.custom.MainDashBoardDAO;
import lk.ijse.tailorsystem.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainDashBoardDAOImpl implements MainDashBoardDAO {

    @Override
    public List<Product> getMostReservedProduct() throws SQLException {
        List<Product> products = new ArrayList<>();
        String[] productNames = {"Blazer", "Trouser"};

        for (String productName : productNames) {
            try {
                ResultSet resultSet = SQLUtil.execute(
                        "SELECT p.productID, p.productName, p.productColor, p.productSize, p.qtyOnHand " +
                                "FROM product p INNER JOIN " +
                                "(SELECT productID, COUNT(*) AS reservationCount FROM reservationDetails " +
                                "WHERE productID IN (SELECT productID FROM product WHERE productName = ?) " +
                                "GROUP BY productID ORDER BY reservationCount DESC LIMIT 1) AS r ON p.productID = r.productID",
                        productName
                );

                while (resultSet.next()) {
                    String productID = resultSet.getString("productID");
                    String productSize = resultSet.getString("productSize");
                    String productColor = resultSet.getString("productColor");
                    int qtyOnHand = resultSet.getInt("qtyOnHand");
                    products.add(new Product(productID, productColor, productSize, qtyOnHand));
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return products;
    }


    @Override
    public List<MonthlySales> getMonthlySalesFor2024() throws SQLException {
        List<MonthlySales> monthlySales = new ArrayList<>();

        try {
            ResultSet resultSet = SQLUtil.execute("SELECT MONTH(o.orderDate) AS order_month, SUM(od.total) AS total_sales " +
                    "FROM orders o " +
                    "JOIN orderDetails od ON o.orderID = od.orderID " +
                    "WHERE YEAR(o.orderDate) = 2024 " +
                    "GROUP BY MONTH(o.orderDate)");
            while (resultSet.next()) {
                int orderMonth = resultSet.getInt("order_month");
                double totalSales = resultSet.getDouble("total_sales");
                monthlySales.add(new MonthlySales(orderMonth, totalSales));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return monthlySales;
    }


    @Override
    public List<Fabric> getLowStockFabrics() throws SQLException {
        List<Fabric> fabrics = new ArrayList<>();

        try {
            ResultSet resultSet = SQLUtil.execute("SELECT fabricID, fabricName, fabricColor, fabricQtyOnHand FROM fabric WHERE fabricQtyOnHand < 50");
            while (resultSet.next()) {
                String fabricID = resultSet.getString("fabricID");
                String fabricName = resultSet.getString("fabricName");
                String fabricColor = resultSet.getString("fabricColor");
                int fabricQtyOnHand = resultSet.getInt("fabricQtyOnHand");
                fabrics.add(new Fabric(fabricID, fabricName, fabricColor, fabricQtyOnHand));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fabrics;
    }


    @Override
    public List<PieChartData> getPieChartData() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT product_name, sales_count FROM sales_table");

        List<PieChartData> data = new ArrayList<>();
        while (resultSet.next()) {
            String label = resultSet.getString("product_name");
            double value = resultSet.getDouble("sales_count");
            data.add(new PieChartData(label, value));
        }
        return data;
    }

}
