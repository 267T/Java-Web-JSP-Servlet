<%-- 
    Document   : Sidebar
    Created on : Aug 17, 2025, 11:23:49 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
     <!-- Sidebar -->
        <aside class="w-64 bg-gray-800 text-white flex flex-col">
            <div class="p-6 text-2xl font-bold border-b border-gray-700">
                Admin Panel
            </div>
            <nav class="flex-1 p-4 space-y-3">
                <a href="Admin" class="block px-4 py-2 rounded hover:bg-gray-700">Trang chủ</a>
                <a href="Admin" class="block px-4 py-2 rounded hover:bg-gray-700">Quản lý sản phẩm</a>
<!--             <a href="#" class="block px-4 py-2 rounded hover:bg-gray-700">Đơn hàng</a>-->
                <a href="AdminUser" class="block px-4 py-2 rounded hover:bg-gray-700">Khách hàng</a>
                <a href="AdminLogout" class="block px-4 py-2 rounded hover:bg-gray-700"> Đăng xuất</a>
            </nav>
        </aside>
</html>
