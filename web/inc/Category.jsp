<%-- 
    Document   : Category
    Created on : Aug 15, 2025, 11:18:28 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <!-- Danh mục -->
    <section class="max-w-7xl mx-auto px-4 py-12">
        <h2 class="text-2xl font-bold mb-8">Danh mục</h2>
        <!-- Dùng flex + scroll ngang -->
        <div class="flex space-x-4 overflow-x-auto scrollbar-hide">
            <!-- Nút xem tất cả sản phẩm -->
            <a href="Home" 
               class="flex-shrink-0 min-w-[120px] bg-white p-5 rounded-lg shadow hover:shadow-xl hover:-translate-y-1 transition text-center
               <c:if test='${selectedCategory == null}'> border-2 border-blue-500</c:if>">
                   <p class="font-semibold">Tất cả</p>
               </a>
               <!-- Chỉ hiển thị danh mục cha -->
            <c:forEach var="cate" items="${listCategory}">
                <c:if test="${empty cate.parent_id}">
                    <a href="Home?categoryId=${cate.category_id}" 
                       class="flex-shrink-0 min-w-[120px] bg-white p-5 rounded-lg shadow hover:shadow-xl hover:-translate-y-1 transition text-center
                       <c:if test='${selectedCategory == cate.category_id}'> border-2 border-blue-500</c:if>">
                        <p class="font-semibold">${cate.category_name}</p>
                    </a>
                </c:if>
            </c:forEach>
        </div>
    </section>

</html>
