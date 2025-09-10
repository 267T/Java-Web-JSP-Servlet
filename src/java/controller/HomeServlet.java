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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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

        ProductDao productDao = new ProductImpl();
        CategoryDao categoryDao = new CategoryImpl();

        // Lấy danh mục sản phẩm
        List<Category> listCategory = categoryDao.findAll();
        request.setAttribute("listCategory", listCategory);

        // Lấy categoryId để xử lí
        String categoryId = request.getParameter("categoryId");
        List<Product> listProduct;
        // xử lí dữ liệu tìm kiếm
        // lấy tham số để xử lí
        String keyword = request.getParameter("keyword");
        List<Product> listFind;

        if (keyword != null && !keyword.trim().isEmpty()) {
            listFind = productDao.find(keyword.trim());
            request.setAttribute("keyword", keyword);
            listProduct = listFind;   // gán kết quả tìm được vào listProduct
            if (listFind != null && !listFind.isEmpty()) {
                listProduct = listFind;   // có kết quả thì gán

            } else {
                request.setAttribute("error", "Không tìm thấy sản phẩm nào với từ khóa: " + keyword);
            }
        } else if (categoryId != null && !categoryId.isEmpty()) {
            try {
                int cid = Integer.parseInt(categoryId);
                listProduct = productDao.findByCategory(cid);
                request.setAttribute("selectedCategory", cid);
            } catch (NumberFormatException e) {
                listProduct = productDao.findAll();
            }
        } else {
            listProduct = productDao.findAll();
        }

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("title", "Home Page");
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
