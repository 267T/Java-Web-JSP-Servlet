/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.impl;

import data.dao.CategoryDao;
import data.driver.MySQLDriver;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

/**
 *
 * @author lab
 */
// kế thừ lớp cha và thực hiện các phương thức đã khai báo bên lớp cha
// lớp này dùng để tương tác với cơ sở dữ liệu
public class CategoryImpl implements CategoryDao {

    Connection con = MySQLDriver.getConnection(); // gọi lại hàm getconnet bên data.driver để kết nối đến database

    @Override
    // tạo chức năng cho hàm findAll
    public List<Category> findAll() {
        List<Category> listCategory = new ArrayList<>(); // khởi tạo 1 danh sách rỗng để lưu dữ liệu sau khi truy vấn
        String sql = "select * from categories";

        try {
            //PreparedStatement sttm = con.prepareStatement(sql); gõ cái này rồi ấn fix để ra try catch
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                String category_name = rs.getString("category_name");
                Integer parent_id = (Integer) rs.getObject("parent_id"); // giữ nguyên null
                listCategory.add(new Category(category_id, category_name, parent_id));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategory;
    }

    // thêm danh mục
    @Override
    public boolean insertCategory(String name) {
        String sql = "insert into categories (category_name, parent_id) values (?,?)";
        try {
            //PreparedStatement sttm = con.prepareStatement(sql); gõ cái này rồi ấn fix để ra try catch
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, name);
            sttm.setNull(2, java.sql.Types.INTEGER);
            int rows = sttm.executeUpdate();
            return rows > 0; //trả về true để biết thêm ok rồi
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // kiểm tra trùng lặp
    @Override
    public boolean CheckDuplicateInsertCategory(String name) {
        String sql = "select count(*) from categories where lower(trim(category_name)) = ?";
        try {
            // chuẩn hóa trước
            name = name.trim().toLowerCase();

            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, name);

            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // true = bị trùng, false = không trùng
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(int id, String name) {
        String sql = "UPDATE categories SET category_name = ? WHERE category_id = ? AND parent_id IS NULL";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, name);
            sttm.setInt(2, id);
            return sttm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            // Xóa tất cả danh mục con
            String sqlDeleteChildren = "DELETE FROM categories WHERE parent_id = ?";
            PreparedStatement sttmChildren = con.prepareStatement(sqlDeleteChildren);
            sttmChildren.setInt(1, id);
            sttmChildren.executeUpdate();

            // Xóa danh mục cha
            String sqlDeleteParent = "DELETE FROM categories WHERE category_id = ? AND parent_id IS NULL";
            PreparedStatement sttmParent = con.prepareStatement(sqlDeleteParent);
            sttmParent.setInt(1, id);
            return sttmParent.executeUpdate() > 0;

        } catch (SQLException ex) {
        }
        return false;
    }

    @Override
    public Category find(int id) {
        String sql = "SELECT * FROM categories WHERE category_id = ? AND parent_id IS NULL";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, id);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                int cateId = rs.getInt("category_id");
                String name = rs.getString("category_name");
                return new Category(cateId, name, null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // lấy cate theo productid
    @Override
    public String GetCateByProductID (int productId) {
        String sql = "SELECT categories.category_name FROM products JOIN categories ON products.product_id = categories.category_id WHERE products.product_id = ?";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, productId);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                return rs.getString("category_name");
            }
        } catch (SQLException ex) {
            
        }
        return null;
    }
}
