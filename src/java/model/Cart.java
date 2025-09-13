/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {
   private int cartId;       // PK
    private int userId;       // FK -> users
    private Date createdAt;
    private String status;    // ACTIVE / CHECKOUT
    private List<CartItem> items; // Danh sách sản phẩm trong giỏ

    public int getCartId() {
        return cartId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Cart(int cartId, int userId, Date createdAt, String status, List<CartItem> items) {
        this.cartId = cartId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.status = status;
        this.items = items;
    }
   
}
