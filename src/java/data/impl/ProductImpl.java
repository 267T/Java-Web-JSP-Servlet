/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.impl;

import data.dao.ProductDao;
import model.Product;
import data.driver.MySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductImpl implements ProductDao {

    Connection con = MySQLDriver.getConnection();
    
    @Override
    public boolean insert(Product product) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
    
    
    
    // đếm số lượng sản phẩm để phân trang
    @Override
    public int cout(){
        String sql = "select count(*) from products ";
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            
            while (rs.next()) {
                 return rs.getInt(1); // đếm được bao nhiêu sản phẩm thì nó sẽ hiển thị ở cột số 1 sau khi thực hiện truy vấn
            }
        } catch (SQLException ex) {
        }
        return 0;
    }
    @Override
    public List<Product> pagingProduct (int index){
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY product_id LIMIT 3 OFFSET ?";
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            sttm.setInt(1, (index-1)*3); //số 1 là dấu chấm hỏi thứ 1. vì truyền index 1 thì 1-1*3 = 0 thì lấy các sản phẩm có stt từ 0 đến 3
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                String product_name = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String imge = rs.getString("image");
                String description = rs.getString("description");
                list.add(new Product(product_id, category_id, product_name, description, price, imge, quantity));
            }
        } catch (Exception e) {
        }
        return list;
    }
    // đếm số lượng sản phẩm theo category
    @Override
    public int CoutProductByCategory(int categoryId){
        String sql = "SELECT COUNT(*) FROM products WHERE category_id IN (SELECT category_id FROM categories WHERE parent_id = ? OR category_id = ?)";
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            sttm.setInt(1, categoryId);
            sttm.setInt(2, categoryId);
            ResultSet rs = sttm.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1); // lấy giá trị ở cột 1
            }
        } catch (SQLException ex) {
        }
        return 0;
    }
    @Override
    public List<Product> pagingCategoryByProduct (int categoryId,int index){
        List<Product> list = new ArrayList<>();
        String sql = "select * from products where category_id = ? or category_id in (select category_id from categories where parent_id = ?) order by product_id limit 3 offset ?";
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            sttm.setInt(1, categoryId);
            sttm.setInt(2, categoryId);
            sttm.setInt(3, (index-1)*3); //số 1 là dấu chấm hỏi thứ 1. vì truyền index 1 thì 1-1*3 = 0 thì lấy các sản phẩm có stt từ 0 đến 3
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                String product_name = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String imge = rs.getString("image");
                String description = rs.getString("description");
                list.add(new Product(product_id, category_id, product_name, description, price, imge, quantity));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    // lấy sản phẩm để thêm vào giỏ hàng
    @Override
    public List<Product> getProductById(int productid){
        List<Product> ListProduct = new ArrayList<>();
        String sql = "select * from products where product_id = ?";
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            sttm.setInt(1, productid);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                String product_name = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String imge = rs.getString("image");
                String description = rs.getString("description");
                ListProduct.add(new Product(product_id, category_id, product_name, description, price, imge, quantity));
            }
        } catch (Exception e) {
        }
        return ListProduct;
    }
    
    
    
    public static void main(String[] args) {
        ProductImpl dao = new ProductImpl();
        List<Product> list = dao.getProductById(1);
        for (Product product : list) {
            System.out.println(product);
        }
 
    }
    
    
    
//    @Override
//    public List<Product> find(String keyword) {
//        List<Product> listFind = new ArrayList<>();
//        String sql = "select * from products where product_name like N?";
//        PreparedStatement sttm;
//        try {
//            sttm = con.prepareStatement(sql);
//            sttm.setString(1, "%" + keyword +"%");
//            ResultSet rs = sttm.executeQuery();
//            
//            while (rs.next()) {
//                int product_id = rs.getInt("product_id");
//                int category_id = rs.getInt("category_id");
//                String product_name = rs.getString("product_name");
//                int quantity = rs.getInt("quantity");
//                double price = rs.getDouble("price");
//                String imge = rs.getString("image");
//                String description = rs.getString("description");
//                listFind.add(new Product(product_id, category_id, product_name, description, price, imge, quantity));
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return listFind;
//    }
//    
    
    
    
    
    
    
    
}
