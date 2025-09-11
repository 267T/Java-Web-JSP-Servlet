<%-- Products.jsp --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="p-6 space-y-12">
    <!-- Sản phẩm -->
    <section class="max-w-7xl mx-auto px-4 py-12">
        <h2 class="text-2xl font-bold mb-8 text-blue-600">Sản phẩm</h2>
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
        
    </section>

</div>
