/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import data.driver.MySQLDriver;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        request.getRequestDispatcher("./Views/Register.jsp").include(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String full_name = request.getParameter("fullname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");

        // Kiểm tra mật khẩu trùng khớp
        if (password == null || confirm == null || !password.equals(confirm)) {
            request.setAttribute("error", "Mật khẩu nhập lại không khớp!");
            request.getRequestDispatcher("./Views/Register.jsp").forward(request, response);
            return;
        }

        try (Connection conn = MySQLDriver.getConnection()) {
            if (conn == null) {
                throw new SQLException("Không thể kết nối cơ sở dữ liệu");
            }

            // Kiểm tra email hoặc số điện thoại đã tồn tại
            try (PreparedStatement check = conn.prepareStatement(
                    "SELECT email, phone FROM users WHERE email = ? OR phone = ?")) {
                check.setString(1, email);
                check.setString(2, phone);

                try (ResultSet rs = check.executeQuery()) {
                    if (rs.next()) {
                        String err = email.equals(rs.getString("email")) ? "Email đã tồn tại!" : "Số điện thoại đã tồn tại!";
                        request.setAttribute("error", err);
                        request.getRequestDispatcher("./Views/Register.jsp").forward(request, response);
                        return;
                    }
                }
            }

            // Insert user mới (lưu thẳng password)
            try (PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO users(full_name,email,address,phone,password,role) VALUES(?,?,?,?,?,?)")) {
                insert.setString(1, full_name);
                insert.setString(2, email);
                insert.setString(3, address);
                insert.setString(4, phone);
                insert.setString(5, password);
                insert.setInt(6, 2);
                insert.executeUpdate();
            }

            request.setAttribute("success", "Đăng ký thành công!");
            request.getRequestDispatcher("./Views/Register.jsp").forward(request, response);

        } catch (SQLException e) {
            
            request.setAttribute("error", "Lỗi cơ sở dữ liệu: " + e.getMessage());
            request.getRequestDispatcher("./Views/Register.jsp").forward(request, response);
        }
    }
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
