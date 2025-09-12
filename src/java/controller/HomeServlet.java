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

        // lấy dữ liệu index trong Home?index=${i}
        String indexpage = request.getParameter("index"); // lấy được chỉ số index trong link, nhưng lúc này nó là string ta phải chuyển nó về dạng int đã
        int index = 1; // mặc định trang đầu
        if (indexpage != null) {
            try {
                index = Integer.parseInt(indexpage); // chuển nó về dạng int
            } catch (NumberFormatException e) {
                index = 1;
            }
        }
        // có index rồi giờ truyền vào DAO để thực hiện truy vấn1
        ProductDao productDao = new ProductImpl();
        CategoryDao categoryDao = new CategoryImpl();

        // Lấy danh mục sản phẩm
        List<Category> listCategory = categoryDao.findAll();
        request.setAttribute("listCategory", listCategory);

        // Lấy categoryId để xử lí
        String categoryId = request.getParameter("categoryId");
        List<Product> listProduct;
        int endpage;

        if (categoryId != null && !categoryId.isEmpty()) {

            int cid = Integer.parseInt(categoryId);
            int count = productDao.CoutProductByCategory(cid);
            // hiển thị số lượng trang
            endpage = count / 3;
            if (count % 3 != 0) {
                endpage++;
            }
            listProduct = productDao.pagingCategoryByProduct(cid, index);
            
            request.setAttribute("selectedCategory", cid);

        } else {
            int count = productDao.cout(); // đếm tất cả sản phẩm
            endpage = count / 3;
            if (count % 3 != 0) {
                endpage++;
            }
            // mặc định hiển thị tất cả sản phẩm
            listProduct = productDao.pagingProduct(index);
        }

        request.setAttribute("listProduct", listProduct);
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
