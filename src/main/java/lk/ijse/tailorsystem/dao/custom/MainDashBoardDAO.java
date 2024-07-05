package lk.ijse.tailorsystem.dao.custom;

import lk.ijse.tailorsystem.dao.SuperDAO;
import lk.ijse.tailorsystem.entity.Fabric;
import lk.ijse.tailorsystem.entity.MonthlySales;
import lk.ijse.tailorsystem.entity.PieChartData;
import lk.ijse.tailorsystem.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface MainDashBoardDAO extends SuperDAO {
    List<Product> getMostReservedProduct() throws SQLException;

    List<MonthlySales> getMonthlySalesFor2024() throws SQLException;

    List<Fabric> getLowStockFabrics() throws SQLException;

    List<PieChartData> getPieChartData() throws SQLException, ClassNotFoundException;
}
