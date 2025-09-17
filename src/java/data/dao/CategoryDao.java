/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data.dao;

import java.util.List;
import model.Category;

/**
 *
 * @author lab
 */
// lớp này dùng để khai báo các phương thức có thể thao tác với database

// tạo thêm lớp implemt để thực thi các thao tác với database, kế thừa với lớp cha CategoryDao
public interface CategoryDao {
    public List<Category> findAll(); // lưu tất cả dữ liệu của Category vào đây, vạy nên đây mới có chức năng là lấy tất cả danh mục sản phẩm
    
    public boolean insertCategory(String name);
    public boolean CheckDuplicateInsertCategory(String name); // kiểm tra trùng lặp
    
    // lấy cate theo id của product
    public String GetCateByProductID(int productId);
    
    public boolean update(int id, String name);
    public boolean delete(int id);
    public Category find(int id);
}
