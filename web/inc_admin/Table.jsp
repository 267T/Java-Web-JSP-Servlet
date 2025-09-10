<%-- 
    Document   : Table
    Created on : Aug 17, 2025, 2:22:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <div class="bg-white shadow rounded-lg overflow-hidden p-4">
        
        <label>Danh mục: </label>
        <form method="get" action="Admin" >
            <!-- Lặp qua danh mục cha -->
            <c:forEach var="cate" items="${listCategory}">
                <c:if test="${empty cate.parent_id}">
                    <button type="submit" name="categoryId" class="px-4 py-2 rounded-lg border" value="${cate.category_id}">
                        ${cate.category_name}  <!--hiển thị danh mục cha-->
                    </button>
                </c:if>
            </c:forEach>
            <!-- Nút xem tất cả sản phẩm -->
            <button type="submit" name="categoryId" value="0" class="px-4 py-2 rounded-lg border">
                Tất cả
            </button>
        </form>


        <table class="w-full text-left border-collapse">
            <thead class="bg-gray-200">
                <tr>
                    <th class="p-3">Mã SP</th>
                    <th class="p-3">Tên sản phẩm</th>
                    <th class="p-3">Giá</th>
                    <th class="p-3">Danh mục</th>
                    <th class="p-3">Ảnh</th>
                    <th class="p-3">Tồn kho</th>
                    <th class="p-3">Mô tả</th>

                    <th class="p-3 text-center">Hành động</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${findProductbyCategory}">
                    <tr class="border-b hover:bg-gray-50">
                        <td class="p-3">${p.getProduct_id()}</td>
                        <td class="p-3">${p.getProduct_name()}</td>
                        <td class="p-3 text-red-500 font-bold">${p.getPrice()}₫</td>
                        <td class="p-3">
                            <c:forEach var="c" items="${listCategory}">
                                <c:if test="${c.getCategory_id() == p.getCategory_id()}">
                                    ${c.getCategory_name()}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td class="p-3">
                            <img src="Assets/images/${p.getImage()}" alt="${p.getProduct_name()}" class="w-20 h-20 object-cover rounded-lg">
                        </td> 
                        <td class="p-3">${p.getQuantity()}</td> 
                        <td class="p-3">${p.getDescription()}</td>

                        <td class="p-3 text-center space-x-2">
                            <button class="text-blue-500 hover:text-blue-700">
                                <i class="fa-solid fa-pen"></i> <!-- sửa-->
                            </button>
                            <button class="text-red-500 hover:text-red-700">
                                <i class="fa-solid fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</html>
