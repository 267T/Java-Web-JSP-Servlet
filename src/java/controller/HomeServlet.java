/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
@WebServlet(name = "HomeServlet", urlPatterns = {"/Home"})
public class HomeServlet extends HttpServlet {

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
        // vì luôn dùng hàm này nên phải ghi ở processRequest

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

        ProductDao productDao = new ProductImpl();
        CategoryDao categoryDao = new CategoryImpl();
        // hiển thị số lượng trang
        int cout = productDao.cout();
        int endpage = cout / 3;
        if (cout % 3 != 0) {
            endpage++;
        }

        // Lấy danh mục sản phẩm
        List<Category> listCategory = categoryDao.findAll();
        request.setAttribute("listCategory", listCategory);

        // Lấy categoryId để xử lí
        String categoryId = request.getParameter("categoryId");
        List<Product> listProduct;

        if (categoryId != null && !categoryId.isEmpty()) {
            try {
                int cid = Integer.parseInt(categoryId);
                listProduct = productDao.findByCategory(cid);
                request.setAttribute("selectedCategory", cid);
            } catch (NumberFormatException e) {
                listProduct = productDao.findAll();
            }
        } else {
            // mặc định hiển thị tất cả sản phẩm
            listProduct = productDao.findAll();
        }

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("title", "Home Page");
        request.setAttribute("endpage", endpage); // đẩy số lượng trang lên lại trang jsp
        request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
