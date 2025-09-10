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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ProductImpl implements ProductDao {

    Connection con = MySQLDriver.getConnection();

    @Override
    public List<Product> findAll() {
        List<Product> listProduct = new ArrayList<>(); // khởi tạo 1 danh sách rỗng để lưu dữ liệu sau khi truy vấn
        String sql = "select * from products";

        try {
            //PreparedStatement sttm = con.prepareStatement(sql); gõ cái này rồi ấn fix để ra try catch
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                String product_name = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String imge = rs.getString("image");
                String description = rs.getString("description");
                listProduct.add(new Product(product_id, category_id, product_name, description, price, imge, quantity));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    @Override
    public List<Product> findByCategory(int categoryId) {
        List<Product> listFilter = new ArrayList<>();
        String sql = "SELECT * FROM products "
                + "WHERE category_id = ? "
                + "OR category_id IN (SELECT category_id FROM categories WHERE parent_id = ?)";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, categoryId);
            sttm.setInt(2, categoryId);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                String product_name = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String imge = rs.getString("image");
                String description = rs.getString("description");
                listFilter.add(new Product(product_id, category_id, product_name, description, price, imge, quantity));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFilter;
    }

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
    
    
    
    // đếm số lượng sản phẩm để phân trang tìm kiếm
    @Override
    public int cout(String keyword){
        String sql = "select count(*) from products where product_name like N?";
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            sttm.setString(1, "%" + keyword +"%");
            ResultSet rs = sttm.executeQuery();
            
            while (rs.next()) {
                 return rs.getInt(1); // đếm được bao nhiêu sản phẩm thì nó sẽ hiển thị ở cột số 1 sau khi thực hiện truy vấns
            }
        } catch (SQLException ex) {
        }
        return 0;
    }
    
    
    @Override
    public List<Product> find(String keyword) {
        List<Product> listFind = new ArrayList<>();
        String sql = "select * from products where product_name like N?";
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            sttm.setString(1, "%" + keyword +"%");
            ResultSet rs = sttm.executeQuery();
            
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                String product_name = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String imge = rs.getString("image");
                String description = rs.getString("description");
                listFind.add(new Product(product_id, category_id, product_name, description, price, imge, quantity));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listFind;
    }
    public static void main(String[] args) {
        ProductImpl dao = new ProductImpl();
        int count = dao.cout("d");
        System.out.println(count);
    }
    
}
