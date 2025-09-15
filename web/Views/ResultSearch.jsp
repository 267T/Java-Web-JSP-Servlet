<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body class="bg-gray-50 text-gray-800 flex flex-col min-h-screen">

    <!-- Header / Banner -->
    <c:import url="/inc/Header.jsp"/>
    <c:import url="/inc/Banner.jsp"/>

    <div class="container mx-auto px-4 py-8 flex-grow">
        <h2 class="text-2xl font-bold mb-6">
            Kết quả tìm kiếm cho: 
            <span class="text-blue-600">"${keyword}"</span>
        </h2>

        <!-- Nếu có sản phẩm -->
        <c:if test="${not empty listProduct}">
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
                <c:forEach var="p" items="${listProduct}">
                    <div class="bg-white shadow-lg rounded-xl p-4 hover:shadow-2xl transition">
                        <img src="Assets/images/${p.image}" alt="${p.product_name}" 
                             class="mx-auto mb-3 h-32 object-contain"/>
                        <h3 class="font-semibold text-lg mb-1">${p.product_name}</h3>
                        <p class="text-sm text-gray-500 mb-2">${p.description}</p>
                        <p class="text-red-500 font-bold text-lg mb-1">
                            <fmt:formatNumber value="${p.price}" type="currency"/>
                        </p>
                        <p class="text-gray-600 text-sm">Còn lại: ${p.quantity}</p>

                        <!-- Nút thêm vào giỏ hàng -->
                        <form action="CartServlet" method="post">
                            <input type="hidden" name="action" value="add"/>
                            <input type="hidden" name="id" value="${p.product_id}"/>
                            <button type="submit" 
                                    class="mt-3 w-full bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition">
                                Thêm vào giỏ hàng
                            </button>
                        </form>
                    </div>
                </c:forEach>
                
            </div>
            <c:import url="/inc/Page.jsp"/>
        </c:if>

        <!-- Nếu có lỗi -->
        <c:if test="${not empty error_find}">
            <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-6 mt-6">
                <i class="fa-solid fa-triangle-exclamation mr-2"></i>${error_find}
            </div>
        </c:if>

        <!-- Nếu không có dữ liệu -->
        <c:if test="${empty listProduct and empty error_find}">
            <div class="bg-blue-100 border border-blue-400 text-blue-700 px-4 py-3 rounded mt-6">
                <i class="fa-solid fa-circle-info mr-2"></i>
                Vui lòng nhập từ khóa để tìm kiếm sản phẩm.
            </div>
        </c:if>
    </div>

    <c:import url="/inc/Footer.jsp"/>
</body>
</html>
