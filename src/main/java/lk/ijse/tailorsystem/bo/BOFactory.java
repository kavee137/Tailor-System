package lk.ijse.tailorsystem.bo;

import lk.ijse.tailorsystem.bo.custom.impl.*;
import lk.ijse.tailorsystem.dao.custom.impl.UserDAOImpl;
import lk.ijse.tailorsystem.dao.custom.impl.ViewReservationDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,PRODUCT,EMPLOYEE,SUPPLIER,FABRIC,USER,RESERVATION,RESERVATIONDETAILS,PLACERESERVATION,VIEWRESERVATION
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
            case RESERVATION:
                return new ReservationBOImpl();
            case RESERVATIONDETAILS:
                return new ReservationDetailsBOImpl();
            case PLACERESERVATION:
                return new PlaceReservationBOImpl();
            case VIEWRESERVATION:
                return new ViewReservationBOImpl();
            default:
                return null;
        }
    }

}
