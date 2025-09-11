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
            <!-- Các số trang -->
            <c:forEach var="i" begin="1" end="${endpage}">
                <p>${i}</p>
            </c:forEach>
        </div>
        
        
        
        <jsp:include page="/inc/Footer.jsp"/>


    </body>
</html>
