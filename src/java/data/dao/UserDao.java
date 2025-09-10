/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data.dao;

import java.util.List;
import model.User;

/**
 *
 * @author lab
 */
public interface UserDao {
    public User find(String emailphone, String password);
    List<User>  findAll();
}
