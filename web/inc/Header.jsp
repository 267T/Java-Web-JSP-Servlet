<%-- 
    Document   : Header
    Created on : Aug 15, 2025, 11:06:05 AM
    Author     : Admin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Header -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <header class="bg-white shadow-md sticky top-0 z-50">
        <div class="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
            <!-- Logo -->
            <a href="Home" class="flex items-center space-x-2 hover:opacity-90 transition">
                <img src="Assets/Logo/Logo.png" alt="Logo" class="h-10 w-auto">
                <span class="text-2xl font-extrabold text-blue-600">Văn Phòng Phẩm</span>
            </a>

            <!-- Menu -->
            <nav class="space-x-6 hidden md:block">
                <a href="Home" class="hover:text-blue-600 transition">Trang chủ</a>
                <a href="#" class="hover:text-blue-600 transition">Khuyến mãi</a>
                <a href="#" class="hover:text-blue-600 transition">Liên hệ</a>
            </nav>

            <!-- Ô tìm kiếm + user -->
            <form action="Home" method="get" class="flex items-center space-x-3">
                <input type="text" name="keyword" 
                       value="${keyword}" 
                       placeholder="Tìm kiếm..." 
                       class="px-3 py-1 border rounded-lg focus:ring focus:ring-blue-200 outline-none"/>
                <button type="submit" class="px-3 py-1 bg-blue-500 text-white rounded-lg hover:bg-blue-600">
                    <i class="fa fa-search"></i>
                </button>
            </form>     
                       
            <!-- Nếu chưa đăng nhập -->
            <c:if test="${empty sessionScope.user}">
                <div class="flex items-center space-x-2">
                    <a href="Login" class="bg-blue-500 text-white px-3 py-1 rounded-lg hover:bg-blue-600 transition">
                        Đăng nhập
                    </a>
                    <a href="Register" class="bg-green-500 text-white px-3 py-1 rounded-lg hover:bg-green-600 transition">
                        Đăng ký
                    </a>
                </div>
            </c:if>

            <!-- Nếu đã đăng nhập -->
            <c:if test="${not empty user}">
                <span class="font-semibold text-gray-700">
                    👋 Xin chào, ${splitname}
                </span>

                <!-- Nút giỏ hàng -->
                <a href="Cart" class="relative ml-4 text-gray-700 hover:text-blue-600 transition">
                    <!-- Icon giỏ hàng (SVG) -->
                    <i class="fa-solid fa-cart-shopping"></i>
                </a>

                <a href="Logout" class="ml-4 bg-red-500 text-white px-3 py-1 rounded-lg hover:bg-red-600 transition">
                    Đăng xuất
                </a>
            </c:if>

        </div>
    </div>
</header>

</html>
