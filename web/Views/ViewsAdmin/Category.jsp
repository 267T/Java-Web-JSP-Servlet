<%-- 
    Document   : CategoryList
    Created on : Aug 20, 2025
    Author     : lab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý danh mục cha</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>
    <body class="p-6">

        <h2 class="text-2xl font-semibold mb-6">Danh sách danh mục cha</h2>

        <!-- Thông báo thêm thành công -->
        <c:if test="${not empty success}">
            <p class="text-green-600 mb-4">${success}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p class="text-red-600 mb-4">${error}</p>
        </c:if>


        <!-- Thông báo thêm thành công -->
        <c:if test="${not empty donedelete}">
            <p class="text-green-600 mb-4">${donedelete}</p>
        </c:if>
        <c:if test="${not empty faildelete}">
            <p class="text-red-600 mb-4">${faildelete}</p>
        </c:if>

        <!-- Nút thêm mới -->
        <a href="AdminAddCategory" 
           class="mb-4 inline-block px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700">
            <i class="fa-solid fa-plus"></i> Thêm danh mục cha
        </a>

        <a href="Admin" 
           class="mb-4 inline-block px-4 py-2 bg-red-600 text-white rounded hover:bg-blue-700">
            Trở về trang chủ
        </a>
        <!-- Bảng danh mục -->
        <table class="w-full border-collapse border border-gray-300">
            <thead class="bg-gray-200">
                <tr>
                    <th class="border border-gray-300 p-2">ID</th>
                    <th class="border border-gray-300 p-2">Tên danh mục</th>
                    <th class="border border-gray-300 p-2">Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cate" items="${listCategory}">
                    <tr>
                        <td class="border border-gray-300 p-2">${cate.getCategory_id()}</td>
                        <td class="border border-gray-300 p-2">${cate.category_name}</td>
                        <td class="border border-gray-300 p-2 flex gap-2">
                            <!-- Nút sửa -->
                            <form action="EditCategory" method="get">
                                <input type="hidden" name="id" value="${cate.category_id}">
                                <button type="submit" 
                                        class="px-3 py-1 bg-yellow-500 text-white rounded hover:bg-yellow-600">
                                    <i class="fa-solid fa-pen"></i> Sửa
                                </button>
                            </form>
                            <!-- Nút xóa -->
                            <form action="AdminDeleteCategory" method="post" 
                                  onsubmit="return confirm('Bạn có chắc muốn xóa không?')">
                                <input type="hidden" name="id" value="${cate.category_id}">
                                <button type="submit" 
                                        class="px-3 py-1 bg-red-600 text-white rounded hover:bg-red-700">
                                    <i class="fa-solid fa-trash"></i> Xóa
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
