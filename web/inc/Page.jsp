<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="flex justify-center items-center space-x-2 mt-8">
    

    <!-- Các số trang -->
    <c:forEach var="i" begin="0" end="${end}">
       <p>${i}</p>
    </c:forEach>

    
</div>
