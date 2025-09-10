<%-- 
    Document   : _LoginForm
    Created on : Aug 15, 2025
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng nhập tài khoản</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="bg-gray-50 text-gray-800 flex flex-col min-h-screen">
        <main class="flex-1 flex items-center justify-center py-10">
            <div class="bg-white p-8 rounded-2xl shadow-lg w-full max-w-md border border-gray-100">
                <h2 class="text-2xl font-bold text-center text-blue-600 mb-6">Đăng nhập tài khoản</h2>
                <form action="Login" method="post" class="space-y-4">
                    <div>
                        <label for="email" class="block text-gray-700 font-semibold mb-1">Email</label>
                        <input type="email" id="email" name="email" required
                               class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-200 outline-none">
                    </div>
                    <div>
                        <label for="password" class="block text-gray-700 font-semibold mb-1">Mật khẩu</label>
                        <input type="password" id="password" name="password" required
                               class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-200 outline-none">
                    </div>
                    <button type="submit" 
                            class="w-full bg-blue-500 text-white py-2 rounded-lg font-semibold hover:bg-blue-600 hover:scale-[1.02] transition">
                        Đăng nhập
                    </button>

                    <%-- Hiển thị lỗi nếu có --%>
                    <c:if test="${not empty sessionScope.error}">
                        <div class="bg-red-100 text-red-700 p-2 rounded mb-4 text-center">
                            ${sessionScope.error}
                        </div>
                      <c:remove var="error" scope="session"/> <!--  khi reload thì mất cái session thông báo lỗi-->
                    </c:if>
                </form>

                <p class="mt-4 text-center text-sm text-gray-600">
                    Chưa có tài khoản?
                    <a href="Register" class="text-pink-500 hover:underline">Đăng ký</a>
                </p>
            </div>
        </main>
    </body>
</html>
