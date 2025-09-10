<%-- 
    Document   : User
    Created on : Aug 17, 2025, 3:28:34 PM
    Author     : Admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý khách hàng</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>
    <body class="bg-gray-100 min-h-screen flex">
        
        <!-- Sidebar -->
        <c:import url="/inc_admin/Sidebar.jsp"/>
        
        <!-- Main Content -->
        <div class="flex-1 p-8">
            <h2 class="text-2xl font-bold mb-6 text-gray-800">Danh sách khách hàng</h2>
            
            <div class="bg-white shadow-md rounded-lg overflow-hidden">
                <table class="w-full border-collapse">
                    <thead>
                        <tr class="bg-gray-200 text-gray-700">
                            <th class="py-3 px-4 text-left">ID</th>
                            <th class="py-3 px-4 text-left">Họ tên</th>
                            <th class="py-3 px-4 text-left">Email</th>
                            <th class="py-3 px-4 text-left">Số điện thoại</th>
                            <th class="py-3 px-4 text-left">Địa chỉ</th>
                            <th class="py-3 px-4 text-left">Chức vụ</th>
                            <th class="py-3 px-4 text-center">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${ListUser}">
                            <tr class="border-b hover:bg-gray-100 transition">
                                <td class="py-3 px-4">${c.getId()}</td>
                                <td class="py-3 px-4">${c.getName()}</td>
                                <td class="py-3 px-4">${c.getEmail()}</td>
                                <td class="py-3 px-4">${c.getPhone()}</td>
                                <td class="py-3 px-4">${c.getAddress()}</td>
                                <td class="py-3 px-4">
                                    <c:if test="${c.getRole() == 1}"> <p style="color: red">Quản trị viên</p></c:if>
                                    <c:if test="${c.getRole() == 2}"> Người dùng</c:if>
                                </td>
                                
                                <td class="py-3 px-4 text-center space-x-2">
                                    <a href="#" class="text-blue-500 hover:text-blue-700">
                                        <i class="fa-solid fa-pen"></i>
                                    </a>
                                    <a href="#" 
                                       class="text-red-500 hover:text-red-700"
                                      
                                        <i class="fa-solid fa-trash">xóa</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
