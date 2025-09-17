<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Chi tiết sản phẩm</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>
    <body class="flex flex-col min-h-screen">
        <jsp:include page="/inc/Header.jsp"/>
        <jsp:include page="/inc/Banner.jsp"/>

        <div class="container py-5">
            <h2 class="text-center mb-5 font-bold text-2xl text-gray-800">Chi tiết sản phẩm</h2>

            <c:if test="${not empty listproduct and not empty category}">
                <c:forEach var="p" items="${listproduct}">
                    <div class="max-w-4xl mx-auto bg-white rounded-2xl shadow-lg overflow-hidden">
                        <div class="row g-0">
                            <!-- Cột ảnh -->
                            <div class="col-md-5 bg-gray-50 flex items-center justify-center">
                                <img src="Assets/images/${p.getImage()}" 
                                     class="img-fluid p-3 rounded-xl" 
                                     alt="${p.getProduct_name()}">
                            </div>

                            <!-- Cột thông tin -->
                            <div class="col-md-7">
                                <div class="p-4">
                                    <h3 class="text-2xl font-semibold text-gray-900 mb-3">
                                        ${p.getProduct_name()}
                                    </h3>
                                    <p class="text-lg text-red-600 font-bold mb-2">
                                        Giá: 
                                        <fmt:formatNumber value="${p.getPrice()}" type="currency" currencySymbol="₫"/>
                                    </p>
                                    <p class="text-gray-700 mb-3">${p.getDescription()}</p>
                                    <p class="text-sm text-gray-500 mb-4">
                                        <i class="fa-solid fa-tags"></i> Danh mục: ${category}
                                    </p>

                                    <!-- Nút -->
                                    <div class="flex gap-3">
                                        <a href="Home" 
                                           class="btn btn-outline-secondary">
                                            <i class="fa-solid fa-arrow-left"></i> Quay lại
                                        </a>
                                        <form action="Cart" method="post">
                                            <input type="hidden" name="action" value="add">
                                            <input type="hidden" name="id" value="${p.product_id}"/>
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fa-solid fa-cart-plus"></i> Thêm vào giỏ
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${empty listproduct}">
                <div class="alert alert-warning text-center mt-5">
                    Không tìm thấy sản phẩm.
                </div>
            </c:if>
            
        </div>
        <jsp:include page="/inc/Rate.jsp"/>
        <jsp:include page="/inc/Footer.jsp"/>
    </body>
</html>
