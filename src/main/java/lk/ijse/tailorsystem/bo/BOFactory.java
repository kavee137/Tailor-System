package lk.ijse.tailorsystem.bo;

import lk.ijse.tailorsystem.bo.custom.impl.*;
import lk.ijse.tailorsystem.dao.custom.impl.LoginDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,PRODUCT,EMPLOYEE,SUPPLIER,FABRIC,USER,RESERVATION,RESERVATIONDETAILS,PLACERESERVATION,VIEWRESERVATION,ORDER,VIEWORDER,MAINDASHBOARD,LOGIN
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case FABRIC:
                return new FabricBOImpl();
            case USER:
                return new UserBOImpl();
            case PLACERESERVATION:
                return new PlaceReservationBOImpl();
            case VIEWRESERVATION:
                return new ViewReservationBOImpl();
            case ORDER:
                return new PlaceOrderBOImpl();
            case VIEWORDER:
                return new ViewOrderBOImpl();
            case MAINDASHBOARD:
                return new MainDashBoardBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            default:
                return null;
        }
    }

}
