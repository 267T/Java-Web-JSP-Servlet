package data.impl;

import data.dao.UserDao;
import data.driver.MySQLDriver;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserImpl implements UserDao {

    Connection con = MySQLDriver.getConnection();

    @Override
    public User find(String emailphone, String password) {
        try {
            String sql;
            if (emailphone.contains("@")) {
                sql = "select * from users where email='" + emailphone + "' and password='" + password + "'";
            } else {
                sql = "select * from users where email='" + emailphone + "' and password='" + password + "'";
            }
            
            
            PreparedStatement sttm = con.prepareStatement(sql);
            
            
            ResultSet rs = sttm.executeQuery();

            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String full_name = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String pass = rs.getString("password");
                String role = rs.getString("role");

                return new User(user_id, full_name, email, phone, pass, role, address);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    @Override
    public List<User> findAll() {
       List<User> ListUser = new ArrayList<>();
       String sql = "select * from users";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String full_name = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String pass = rs.getString("password");
                String role = rs.getString("role"); 
               ListUser.add(new User(user_id, full_name, email, phone, pass, role, address));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListUser;
    }
    
    
    
    
    
    
}
