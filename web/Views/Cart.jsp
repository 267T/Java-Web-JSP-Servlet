<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <!-- thêm thư viện Font Awesome CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>
    <body>
        <section class="max-w-3xl mx-auto px-4 py-8">
            <h1 class="text-2xl font-bold mb-4">Giỏ hàng</h1>

            <ul role="list" class="-my-6 divide-y divide-gray-200">
                <c:forEach var="it" items="">
                    <li class="flex py-6">
                        <div class="size-24 shrink-0 overflow-hidden rounded-md border">
                            <img src="" alt="" class="size-full object-cover"/>
                        </div>
                        <div class="ml-4 flex flex-1 flex-col">
                            <div class="flex justify-between text-base font-medium">
                                <h3>${it.name}</h3>
                                <p class="ml-4">${it.unitPrice}</p>
                            </div>
                            <p class="mt-1 text-sm text-gray-500">SL: </p>
                            <div class="flex flex-1 items-end justify-between text-sm">
                                <form action="Cart" method="post">
                                    <input type="hidden" name="action" value="remove"/>
                                    <input type="hidden" name="id" value="${it.productId}"/>
                                    <button type="submit" class="font-medium text-indigo-600 hover:text-indigo-500">
                                        Xóa
                                    </button>
                                </form>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>

            <div class="border-t pt-4 flex justify-between text-lg font-semibold mt-6">
                <span>Tổng:</span>
                <span></span>
            </div>

            <div class="mt-6 flex justify-between">
                <a href="Home"class="text-indigo-600 hover:underline">← Tiếp tục mua</a>
                <a href="#" class="bg-indigo-600 text-white px-6 py-3 rounded-lg hover:bg-indigo-700">
                    Thanh toán
                </a>
            </div>
        </section>

    </body>
</html>
