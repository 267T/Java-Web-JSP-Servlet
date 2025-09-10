<%-- 
    Document   : _Login
    Created on : Aug 15, 2025, 11:20:43 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng ký tài khoản</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <main class="flex-1 flex items-center justify-center py-10">
        <div class="bg-white p-8 rounded-2xl shadow-lg w-full max-w-md border border-gray-100">
            <h2 class="text-2xl font-bold text-center text-blue-600 mb-6">Đăng ký tài khoản</h2>

            <form action="Register" method="post" class="space-y-4">
                <div>
                    <label for="fullname" class="block text-gray-700 font-semibold mb-1">Họ và tên</label>
                    <input type="text" id="fullname" name="fullname" required
                           class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-200 outline-none">
                </div>

                <div>
                    <label for="email" class="block text-gray-700 font-semibold mb-1">Email</label>
                    <input type="email" id="email" name="email" required
                           class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-200 outline-none">
                </div>
                <div>
                    <label for="address" class="block text-gray-700 font-semibold mb-1">Địa chỉ</label>
                    <input type="text" id="address" name="address" required
                           class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-200 outline-none">
                </div>

                <div>
                    <label for="phone" class="block text-gray-700 font-semibold mb-1">Số điện thoại</label>
                    <input type="tel" id="phone" name="phone" pattern="[0-9]{10,11}" required
                           class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-200 outline-none">
                </div>

                <div>
                    <label for="password" class="block text-gray-700 font-semibold mb-1">Mật khẩu</label>
                    <input type="password" id="password" name="password" required
                           class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-200 outline-none">
                </div>

                <div>
                    <label for="confirm" class="block text-gray-700 font-semibold mb-1">Nhập lại mật khẩu</label>
                    <input type="password" id="confirm" name="confirm" required
                           class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-200 outline-none">
                </div>

                <button type="submit" 
                        class="w-full bg-blue-500 text-white py-2 rounded-lg font-semibold hover:bg-blue-600 hover:scale-[1.02] transition">
                    Đăng ký
                </button>

                <% String error = (String) request.getAttribute("error"); %>
                <% if (error != null) {%>
                <div class="bg-red-100 text-red-700 p-2 rounded mb-4 text-center">
                    <%= error%>
                </div>
                <% }%>



                <%
                    String success = (String) request.getAttribute("success");
                    if (success != null) {
                %>
                <div class="bg-green-100 text-green-700 p-2 rounded mb-4 text-center">
                    <%= success%>
                </div>
                <%
                    }
                %>
            </form>

            <p class="mt-4 text-center text-sm text-gray-600">
                Đã có tài khoản?
                <a href="Login" class="text-pink-500 hover:underline">Đăng nhập</a>
            </p>
        </div>
    </main>

</html>
