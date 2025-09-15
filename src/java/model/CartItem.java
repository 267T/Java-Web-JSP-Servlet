/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class CartItem {
    private int itemId;       
    private int cartId;       
    private Product product; 
    private int quantity;

    public int getItemId() {
        return itemId;
    }

    public int getCartId() {
        return cartId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem(int itemId, int cartId, Product product, int quantity) {
        this.itemId = itemId;
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
    }
    
}
