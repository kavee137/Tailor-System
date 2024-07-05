package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.MainDashBoardBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.MainDashBoardDAO;
import lk.ijse.tailorsystem.entity.Fabric;
import lk.ijse.tailorsystem.entity.MonthlySales;
import lk.ijse.tailorsystem.entity.PieChartData;
import lk.ijse.tailorsystem.entity.Product;

import java.sql.SQLException;
import java.util.List;

public class MainDashBoardBOImpl implements MainDashBoardBO {

    MainDashBoardDAO mainDashBoardDAO= (MainDashBoardDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MAINDASHBOARD);

    @Override
    public List<Product> getMostReservedProduct() throws SQLException {
        return mainDashBoardDAO.getMostReservedProduct();
    }

    @Override
    public List<MonthlySales> getMonthlySalesFor2024() throws SQLException {
        return mainDashBoardDAO.getMonthlySalesFor2024();
    }


    @Override
    public List<Fabric> getLowStockFabrics() throws SQLException {
        return mainDashBoardDAO.getLowStockFabrics();
    }


    @Override
    public List<PieChartData> getPieChartData() throws SQLException, ClassNotFoundException {
     return mainDashBoardDAO.getPieChartData();
    }

}
