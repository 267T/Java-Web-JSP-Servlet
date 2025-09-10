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

    List<Product> findAll();
    List<Product> findByCategory(int categoryId);
    List<Product> find(String keyword); // để hắn có thể trả về 1 dãy kết quả

    boolean insert(Product product);

    boolean update(Product product);

    boolean delete(int id);

    
}
