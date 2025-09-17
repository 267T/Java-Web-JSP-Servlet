<%-- 
    Document   : _Reviews
    Created on : Sep 17, 2025, 10:01:13 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            
            <!-- Form đánh giá -->
            <form action="#" method="post" class="mb-4">
                <input type="hidden" name="productId" value="#">

                <!-- Chọn sao -->
                <div class="mb-3">
                    <label class="form-label">Chọn số sao:</label>
                    <div class="d-flex gap-1 text-warning fs-4">
                        <input type="radio" name="rating" value="1" required> <i class="fa-etch fa-solid fa-star"></i>
                        <input type="radio" name="rating" value="2"><c:forEach begin="1" end="2" step="1"> <i class="fa-etch fa-solid fa-star"></i> </c:forEach>
                        <input type="radio" name="rating" value="3"> <c:forEach begin="1" end="3" step="1"> <i class="fa-etch fa-solid fa-star"></i> </c:forEach>
                        <input type="radio" name="rating" value="4"> <c:forEach begin="1" end="4" step="1"> <i class="fa-etch fa-solid fa-star"></i> </c:forEach>
                        <input type="radio" name="rating" value="5"> <c:forEach begin="1" end="5" step="1"> <i class="fa-etch fa-solid fa-star"></i> </c:forEach>
                    </div>
                </div>

                <!-- Nội dung -->
                <div class="mb-3">
                    <label class="form-label">Nhận xét:</label>
                    <textarea name="comment" class="form-control" rows="3" placeholder="Viết nhận xét..." required></textarea>
                </div>

                <!-- Nút gửi -->
                <div class="text-center">
                    <button type="submit" class="btn btn-success">
                        <i class="fa-solid fa-paper-plane"></i> Gửi đánh giá
                    </button>
                </div>
            </form>

            <!-- Danh sách đánh giá -->
            <div class="border-top pt-3">
                <h5 class="mb-3 text-center">Các đánh giá gần đây:</h5>
                <c:if test="${not empty reviews}">
                    <c:forEach var="r" items="${reviews}">
                        <div class="p-3 mb-2 bg-light rounded shadow-sm">
                            <p class="text-warning mb-1">
                                <c:forEach begin="1" end="${r.rating}">⭐</c:forEach>
                            </p>
                            <p class="mb-1"><strong>${r.userName}</strong></p>
                            <p class="text-muted">${r.comment}</p>
                            <small class="text-secondary">Ngày: ${r.createdAt}</small>
                        </div>
                    </c:forEach>
                </c:if>

                <c:if test="${empty reviews}">
                    <p class="text-muted text-center">Chưa có đánh giá nào. Hãy là người đầu tiên!</p>
                </c:if>
            </div>
        </div>
    </div>
</div>
