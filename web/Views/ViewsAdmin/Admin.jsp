<%-- 
    Document   : Admin
    Created on : Aug 16, 2025, 11:15:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Quản lý sản phẩm</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <!-- thêm thư viện font -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>

    <body class="bg-gray-100 min-h-screen flex">
        <c:import url="/inc_admin/Sidebar.jsp"/>
        <main class="flex-1 p-6">
            <c:import url="/inc_admin/Header.jsp"/>
            <c:import url="/inc_admin/Table.jsp"/>
        </main>
    </body>
</html>
