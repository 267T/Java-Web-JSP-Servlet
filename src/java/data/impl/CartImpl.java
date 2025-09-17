/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.impl;

import data.dao.CartDao;
import data.driver.MySQLDriver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.CartItem;
import model.Product;

/**
 *
 * @author Admin
 */
public class CartImpl implements CartDao {

    Connection con = MySQLDriver.getConnection(); // gọi lại hàm getconnet bên data.driver để kết nối đến database

    // kiểm tra xem giỏ hàng đã có chưa để khởi tạo
    @Override
    public Cart findActiveCartByUserId(int user_id) {
        String sql = "SELECT cart_id, user_id, status, created_at FROM carts WHERE user_id = ? AND status = 'Active'";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, user_id);
                 
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                return new Cart(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("created_at"),
                        rs.getString("status"),
                        GetItems(rs.getInt("cart_id")) // load luôn danh sách item
                );
            }
        } catch (Exception e) {
        }
        return null; // chưa khởi tạo giỏ hàng
    }

    // tạo giỏ hàng cho từng user_id
    @Override
    public int CreateCart(int user_id) {
        String sql = "insert into carts(user_id, status) values (?, ?)"; // kích hoạt giỏ hàng
        try {
            PreparedStatement sttm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Statement.RETURN_GENERATED_KEYS báo cho JDBC rằng bạn muốn lấy giá trị khóa tự tăng
            sttm.setInt(1, user_id);
            sttm.setString(2, "Active");
            int affectedRows = sttm.executeUpdate(); // dùng executeUpdate() cho INSERT
            if (affectedRows > 0) {
                ResultSet rs = sttm.getGeneratedKeys(); //sttm.getGeneratedKeys() trả về một ResultSet chứa giá trị đó.
                if (rs.next()) {
                    return rs.getInt(1); // trả về cart_id vừa tạo
                }
            }
        } catch (Exception e) {
        }
        return -1; //  lỗi
    }

    // lấy sản phẩm nếu có giỏ hàng sẵn
    @Override
    public List<CartItem> GetItems(int cart_id) {
        List<CartItem> items = new ArrayList<>();
        String sql ="SELECT categories.category_id, cart_items.item_id, cart_items.cart_id, cart_items.product_id, cart_items.quantity, products.product_name, products.price, products.description, products.image FROM cart_items JOIN products ON cart_items.product_id = products.product_id JOIN categories ON products.category_id = categories.category_id WHERE cart_items.cart_id = ?";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, cart_id);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                int cartId = rs.getInt("cart_id");
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                String product_name = rs.getString("product_name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                Product product = new Product(
                        product_id, // product_id
                        category_id, // category_id
                        product_name, // product_name
                        description, // description
                        price, // price (double)
                        image, // image
                        quantity
                );

                CartItem item = new CartItem(
                        itemId, // item_id
                        cartId, // cart_id
                        product, // Product
                        quantity // quantity
                );
                items.add(item);
            }
        } catch (Exception e) {
            
        }
        return items;
    }

    // lấy tổng số tiền cần thanh toán
    @Override
    public double getTotal(int cart_id) {
        String sql = "SELECT SUM(cart_items.quantity * products.price) FROM cart_items JOIN products ON cart_items.product_id = products.product_id WHERE cart_items.cart_id = ?";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, cart_id);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) { 
        }
        return 0;
    }
    
    @Override
    // thêm sản phẩm vào giỏ hàng
    public void AddItem(int cart_id, Product product, int quantity) {
        String checkSql = "SELECT quantity FROM cart_items WHERE cart_id = ? AND product_id = ?";
        try {
            PreparedStatement sttm = con.prepareStatement(checkSql);
            sttm.setInt(1, cart_id);
            sttm.setInt(2, product.getProduct_id());
            ResultSet rs = sttm.executeQuery();

            if (rs.next()) {
                // Nếu đã tồn tại -> update số lượng
                int currentQuantity = rs.getInt("quantity");
                String updateSql = "UPDATE cart_items SET quantity = ? WHERE cart_id = ? AND product_id = ?";
                PreparedStatement update = con.prepareStatement(updateSql);
                update.setInt(1, currentQuantity + quantity);
                update.setInt(2, cart_id);
                update.setInt(3, product.getProduct_id());
                update.executeUpdate();
            } else {
                // Nếu chưa có -> insert mới
                String insertSql = "INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (?, ?, ?)";
                PreparedStatement insert = con.prepareStatement(insertSql);
                insert.setInt(1, cart_id);
                insert.setInt(2, product.getProduct_id());
                insert.setInt(3, quantity);
                insert.executeUpdate();
            }
        } catch (Exception e) {
            
        }
    }
    
    // xóa sản phẩm theo id
    @Override
    public boolean DeleteProduct(int productId){
        String sql = "DELETE FROM cart_items WHERE product_id = ?";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, productId);
            int rowAffected = sttm.executeUpdate();
            return rowAffected > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    // update số lượng sản phẩm
    @Override
    public boolean UpdateCartProduct(int productId, String action, int cart_id){
        String sql = "";
        switch (action) {
            case "increase": // tăng số lượng
                sql = "UPDATE cart_items SET cart_items.quantity = cart_items.quantity + 1 WHERE cart_items.product_id = ? and cart_items.cart_id = ? ";
                break;
            case "decrease": // xóa bớt và xóa hẳn khi sl về 0
                sql = "UPDATE cart_items SET cart_items.quantity = cart_items.quantity - 1 WHERE cart_items.product_id = ? And and cart_items.cart_id = ? quantity > 1 ";
                break;
            case "delete": // xóa hẳn
                sql = "Delete from cart_items where product_id = ? and cart_items.cart_id = ?";
                break;
        }
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            sttm.setInt(1, productId);
            sttm.setInt(2, cart_id);
            int row = sttm.executeUpdate();
            if("decrease".equals(action) && row == 0){ // nếu truy vấn ra 0 hàng thì nghĩa là quantily = 1 nên sẽ xóa hẳn
                sql = "Delete from cart_items where product_id = ? cart_items.cart_id = ?";
                sttm = con.prepareStatement(sql);
                sttm.setInt(1, productId);
                sttm.setInt(2, cart_id);
                sttm.executeUpdate();
            }
            return row > 0;
        } catch (SQLException ex) { 
        }    
        return false;
    }
    

    

}
