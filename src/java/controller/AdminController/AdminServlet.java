/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.AdminController;

import data.dao.CategoryDao;
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
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/Admin"})
public class AdminServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
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

        Boolean isLoginAdmin = (Boolean) request.getSession().getAttribute("isLoggedInAdmin");
        if (isLoginAdmin == null || !isLoginAdmin) {
            request.getSession().setAttribute("error", "Vui lòng đăng nhập với quyền admin để truy cập trang này!");
            response.sendRedirect("Login");
            return;
        }

        // Lấy danh sách sản phẩm
        ProductDao productDao = new ProductImpl();
        CategoryDao categoryDao = new CategoryImpl();

        List<Product> listProduct = productDao.findAll();
        request.setAttribute("listProduct", listProduct);

        // Lấy danh sách danh mục
        List<Category> listCategory = categoryDao.findAll();
        request.setAttribute("listCategory", listCategory);
        
        // lọc sản phẩm theo danh mục
        String categoryId = request.getParameter("categoryId");
        List<Product> findProductbyCategory;
        
        
        
        
        if(categoryId !=null && !categoryId.isEmpty() && !categoryId.equals("0")){
            // tìm sản phẩm theo id
            int cateid = Integer.parseInt(categoryId);
            findProductbyCategory = productDao.findByCategory(cateid);
            request.setAttribute("selectedCategory", cateid);
        }else{
            findProductbyCategory = productDao.findAll(); // trường hợp tìm tất cả
            request.setAttribute("selectedCategory", 0);
        }
        request.setAttribute("findProductbyCategory", findProductbyCategory);
        request.getRequestDispatcher("./Views/ViewsAdmin/Admin.jsp").forward(request, response);
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
        processRequest(request, response);
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
