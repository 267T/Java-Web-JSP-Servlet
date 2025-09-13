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
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;
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
        String sql = "SELECT cart_id, user_id, status, create_at FROM carts WHERE user_id = ? AND status = 'Active' limit 1";
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
        String sql = "SELECT cart_items.item_id, cart_items.cart_id, cart_items.product_id, cart_items.quantity, products.product_name, products.price, products.description, products.image FROM cart_items JOIN products ON cart_items.product_id = products.product_id WHERE cart_items.cart_id = ?";
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
        String sql = "SELECT SUM(ci.quantity * p.price) FROM cart_items ci JOIN products p ON ci.product_id = p.product_id WHERE ci.cart_id = ?";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, cart_id);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (Exception e) {
            
        }
        return 0;
    }
    
    // thêm sản phẩm vào giỏ hàng
    public void AddItem(int cart_id, Product product, int quantity) {
        String checkSql = "SELECT quantity FROM cart_items WHERE cart_id = ? AND product_id = ?";
        try {
            PreparedStatement check = con.prepareStatement(checkSql);
            check.setInt(1, cart_id);
            check.setInt(2, product.getProduct_id());
            ResultSet rs = check.executeQuery();

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

}
