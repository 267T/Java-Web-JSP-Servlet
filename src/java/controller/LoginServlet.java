/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import data.dao.Database;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        Boolean isLoginUser = (Boolean) request.getSession().getAttribute("isLoggedInUser"); // lấy session đăng nhập của người dùng
        Boolean isLoginAdmin = (Boolean) request.getSession().getAttribute("isLoggedInAdmin"); // lấy session đăng nhập của người dùng
        if (isLoginUser != null && isLoginUser) {
            response.sendRedirect("Home");
            return;
        } else if (isLoginAdmin != null && isLoginAdmin) {
            response.sendRedirect("Admin");
            return;
        }
        request.getRequestDispatcher("./Views/Login.jsp").forward(request, response);
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

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = Database.getUserDao().find(email, password);

        // Kiểm tra nhập thiếu
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.getSession().setAttribute("error", "Vui lòng nhập email và mật khẩu");
            response.sendRedirect("Login");
            return;
        }

        // Kiểm tra không tìm thấy dữ liệu trong database
        if (user == null) {
            request.getSession().setAttribute("error", "Email hoặc mật khẩu không đúng");
            response.sendRedirect("Login");
            return;
        }

        // Nếu tìm thấy user
        if ("1".equals(user.getRole())) {
            // Admin
            request.getSession().removeAttribute("error");
            request.getSession().setAttribute("isLoggedInAdmin", true); // lưu lại sesion đăng nhập của admin
            request.getSession().setAttribute("user", user);
            String splitname = user.getName();
            request.getSession().setAttribute("splitname", splitname);
            response.sendRedirect("Admin");
        } else {
            // Khách hàng
            request.getSession().removeAttribute("error");
            request.getSession().setAttribute("isLoggedInUser", true); // lưu lại sesion đăng nhập của người dùng
            request.getSession().setAttribute("user", user);
            Cart cart = Database.getCartDao().findActiveCartByUserId(user.getId());
            int cartId;
            if(cart == null){ // chưa có giỏ hàng thì tạo mới
                cartId = Database.getCartDao().CreateCart(user.getId());
            }else{ //có rồi thì lấy id của giỏ hàng ra
                cartId = cart.getCartId();
            }
            
            // lưu cartId vào session để dùng cho sevelect
            request.getSession().setAttribute("CartId",cartId); 
            // Lấy họ và tên 
            String splitname = user.getName();
            request.getSession().setAttribute("splitname", splitname);
            response.sendRedirect("Home");
        }
    }

}
