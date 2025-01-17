package lk.ijse.tailorsystem.dao;

import lk.ijse.tailorsystem.dao.custom.OrderDetailsDAO;
import lk.ijse.tailorsystem.dao.custom.PlaceReservationDAO;
import lk.ijse.tailorsystem.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,PRODUCT,EMPLOYEE,SUPPLIER,FABRIC,USER,RESERVATION,RESERVATIONDETAILS,PAYMENT,VIEWRESERVATION,ORDER,ORDERDETAILS,VIEWORDER,MAINDASHBOARD,QUERY,LOGIN
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case FABRIC:
                return new FabricDAOImpl();
            case USER:
                return new UserDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case RESERVATIONDETAILS:
                return new ReservationDetailsDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case VIEWRESERVATION:
                return new ViewReservationDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            case VIEWORDER:
                return new ViewOrderDAOImpl();
            case MAINDASHBOARD:
                return new MainDashBoardDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }


}
