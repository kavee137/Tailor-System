package lk.ijse.tailorsystem.bo;

import lk.ijse.tailorsystem.bo.custom.impl.CustomerBOImpl;
import lk.ijse.tailorsystem.bo.custom.impl.ProductBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,PRODUCT,ITEM,PO
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            default:
                return null;
        }
    }

}
