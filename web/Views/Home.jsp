<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cửa hàng Văn Phòng Phẩm</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <!-- thêm thư viện Font Awesome CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>
    <body class="bg-gray-50 text-gray-800 flex flex-col min-h-screen">
        <jsp:include page="/inc/Header.jsp"/>
        <jsp:include page="/inc/Banner.jsp"/>
        <jsp:include page="/inc/Category.jsp"/>
        <jsp:include page="/inc/Products.jsp"/>

        <div class="flex justify-center items-center space-x-2 mt-8">
            <c:forEach var="i" begin="1" end="${endpage}">
                <!-- Nếu có selectedCategory thì giữ categoryId -->
                <c:if test="${not empty selectedCategory}">
                    <a class="px-3 py-1 border rounded hover:bg-gray-200"
                       href="Home?categoryId=${selectedCategory}&index=${i}">
                        ${i}
                    </a>
                </c:if>
                <!-- Nếu không có selectedCategory thì chỉ truyền index -->
                <c:if test="${empty selectedCategory}">
                    <a class="px-3 py-1 border rounded hover:bg-gray-200"
                       href="Home?index=${i}">
                        ${i}
                    </a>
                </c:if>
            </c:forEach>
        </div>
        <jsp:include page="/inc/Footer.jsp"/>
    </body>
</html>
