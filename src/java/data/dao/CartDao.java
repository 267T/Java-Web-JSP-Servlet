/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data.dao;

import java.util.List;
import model.Cart;
import model.CartItem;
import model.Product;

/**
 *
 * @author Admin
 */
public interface CartDao {
    public Cart findActiveCartByUserId (int user_id); // trả về kiểu dữ liệu là cart. kiểm tra xem user này đã có giỏ hàng chưa
    int CreateCart(int user_id); // tạo giỏ hàng cho từng user
    
    // lấy sản phẩm có sẵn trong giỏ hàng ra
    public List<CartItem> GetItems(int cart_id);
    // lấy ra tổng tiền của sản phẩm
    public double getTotal(int cart_id);
    
    
    
    
    
    public void AddItem(int cart_id,Product product, int quantily);
}
