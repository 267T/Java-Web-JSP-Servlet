/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data.dao;

import java.util.List;
import model.Product;

/**
 *
 * @author Admin
 */
public interface ProductDao {

    
    List<Product> find(String keyword);
    
    
    // phân trang cho sản phẩm theo danh mục sản phẩm
    List<Product> pagingCategoryByProduct (int categoryId,int index);
    int CoutProductByCategory(int categoryId);
    
    
    //phân trang cho sản phẩm
    List<Product> pagingProduct(int index);
    int cout(); // đếm số lượng sản phẩm để phân trang

    // lấy sản phẩm để lưu nó vào giỏ hàng
    List<Product> getProductById(int productid);
    
    
    boolean insert(Product product);
    boolean update(Product product);
    boolean delete(int id);

    
}
