/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import data.dao.CategoryDao;
import data.dao.Database;
import data.dao.ProductDao;
import data.impl.CategoryImpl;
import data.impl.ProductImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CartItem;
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/Cart"})
public class CartServlet extends HttpServlet {

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
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
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

        Integer cartId = (Integer) request.getSession().getAttribute("CartId");
        if (cartId == null) {
            response.sendRedirect("Login");
            return;
        }
        // lấy ra danh sách các mặt hàng
        List<CartItem> items = Database.getCartDao().GetItems(cartId);
        request.setAttribute("items", items);
        
        // lấy tính tổng số lượng
        double total = Database.getCartDao().getTotal(cartId);
        request.setAttribute("total", total);

        request.getRequestDispatcher("./Views/Cart.jsp").forward(request, response);
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
        
        ProductDao productDao = new ProductImpl();
        CategoryDao categoryDao = new CategoryImpl();
        
        List<Product> listproduct;
        List<Category> listcategory;
       
        
        String action = request.getParameter("action"); // lấy hành động
        int product_id = Integer.parseInt(request.getParameter("id")); // lấy id của mặt hàng
        
        Integer cart_id = (Integer) request.getSession().getAttribute("CartId"); // có thể null nên phải dùng Integer làm kiểu dữ liệu
        
        if(cart_id == null){
            response.sendRedirect("Login"); // nếu mà đăng nhập chưa có cart thì bắt đăng nhập lại
            return;
        }
        if("add".equals(action)){
            Product product = productDao.getProductById(product_id).get(0);
            Database.getCartDao().AddItem(cart_id, product, 1);
        }
        
        //int product_iD = Integer.parseInt(request.getParameter("product_id"));

        if("delete".equals(action) ||"decrease".equals(action) ||"increase".equals(action)){
            Database.getCartDao().UpdateCartProduct(product_id, action, cart_id);
        }   
        response.sendRedirect("Cart");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
