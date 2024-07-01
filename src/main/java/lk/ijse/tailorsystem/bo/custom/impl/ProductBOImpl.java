package lk.ijse.tailorsystem.bo.custom.impl;

import lk.ijse.tailorsystem.bo.custom.ProductBO;
import lk.ijse.tailorsystem.dao.DAOFactory;
import lk.ijse.tailorsystem.dao.custom.ProductDAO;
import lk.ijse.tailorsystem.dto.ProductDTO;
import lk.ijse.tailorsystem.entity.Customer;
import lk.ijse.tailorsystem.entity.Product;
import lk.ijse.tailorsystem.entity.ReservationDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    @Override
    public String getTotalProduct() throws SQLException, ClassNotFoundException {
        return productDAO.getTotalProduct();
    }

    @Override
    public boolean reservatioQtyUpdate(List<ReservationDetails> prList) throws SQLException, ClassNotFoundException {
        return productDAO.reservatioQtyUpdate(prList);
    }

    @Override
    public boolean updateQty(int qty, int productID) throws SQLException, ClassNotFoundException {
        return productDAO.updateQty(qty, productID);
    }


    @Override
    public ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> allProducts = new ArrayList<>();
        ArrayList<Product> all = productDAO.getAll();

        for (Product product : all) {
            allProducts.add(new ProductDTO(product.getProductID(), product.getProductName(), product.getProductColor(), product.getProductSize(), product.getUnitPrice(), product.getQtyOnHand()));
        }
        return allProducts;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return productDAO.delete(id);
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        return productDAO.update(product);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean add(ProductDTO product) throws SQLException, ClassNotFoundException {
        return productDAO.add(new Product(product.getProductID(), product.getProductName(), product.getProductColor(), product.getProductSize(), product.getUnitPrice(), product.getQtyOnHand()));
    }

    @Override
    public Product search(Object... args) throws SQLException, ClassNotFoundException {
       return productDAO.search(args);
    }

    @Override
    public List<String> getProductSize(String color, String name) throws SQLException, ClassNotFoundException {
       return productDAO.getProductSize(color, name);
    }

    @Override
    public List<String> getProductColor(String productName) throws SQLException, ClassNotFoundException {
       return productDAO.getProductColor(productName);
    }

    @Override
    public String generateNewID(Object... args) throws SQLException, ClassNotFoundException{
       return productDAO.generateNewID(args);
    }

    @Override
    public List<String> getProductName() throws SQLException, ClassNotFoundException {
        return productDAO.getProductName();
    }


}
