/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lab
 */
public class Category {

    private int category_id;
    private String category_name;
     private Integer parent_id; // Cho phép null
// DÙNG PHÍM TẮT TẠO HÀM KHỞI TẠO

    public Category(int category_id, String category_name, Integer parent_id) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.parent_id = parent_id;
    }

    public int getCategory_id() {
        return category_id;
    }

        
    public String getCategory_name() {
        return category_name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    

}
