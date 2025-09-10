<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm danh mục</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="p-6">

        <h2 class="text-2xl font-semibold mb-6">Thêm danh mục mới</h2>

        <!-- Hiển thị lỗi nếu có -->
        <c:if test="${not empty error}">
            <p class="text-red-600 mb-4">${error}</p>
        </c:if>
        <c:if test="${not empty done_add}">
            <p class="text-red-600 mb-4">${done_add}</p>
        </c:if>

        <form action="AdminAddCategory" method="post" class="space-y-4">
            <div>
                <label for="name" class="block mb-1 font-medium">Tên danh mục:</label>
                <input type="text" name="name" id="name" required
                       class="w-full border border-gray-300 rounded px-3 py-2">
            </div>

            <div>
                <button type="submit"
                        class="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700">
                    Thêm danh mục
                </button>
                <a href="AdminCategory" 
                   class="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600">
                    Quay lại
                </a>
            </div>
        </form>

    </body>
</html>
