/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.dao;

import data.impl.CategoryImpl;
import data.impl.ProductImpl;
import data.impl.UserImpl;

/**
 *
 * @author lab
 */

// lớp này dùng để 
public class Database {
    // gọi lại lớp cha để xây dựng cho lớp con
    public static CategoryDao getCategoryDao(){ 
        return new CategoryImpl(); 
        // đây như hàm khởi tạo bình thường rồi tương tác với dao: CategoryDao dao = new CategoryImpl();
        // còn đây sẽ khai báo ở đây để ở controller dùng Database.getCategoryDao().finall() , thay vì ở controller phải khai báo là CategoryDao dao = new CategoryImpl(). và dao.findall()
        
    }
    // Thêm phương thức lấy ProductDao
    public static ProductDao getProductDao() {
        return new ProductImpl();
    }
    
    public static UserDao getUserDao(){
        return new UserImpl();

    }
}
