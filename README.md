# Java-Web-JSP-Servlet

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JSP](https://img.shields.io/badge/JSP-007396?style=for-the-badge&logo=java&logoColor=white)
![Servlet](https://img.shields.io/badge/Servlet-6DB33F?style=for-the-badge&logo=apachetomcat&logoColor=white)

## 📝 Mô tả

Đây là dự án mẫu về **Java Web sử dụng JSP và Servlet**, phù hợp cho các bạn mới học hoặc muốn tìm hiểu về lập trình web với Java. Dự án bao gồm các chức năng cơ bản như quản lý người dùng, đăng nhập, đăng ký, và xử lý dữ liệu với cơ sở dữ liệu MySQL.

## 🚀 Tính năng nổi bật

- Đăng ký, đăng nhập người dùng
- Quản lý thông tin người dùng
- Hiển thị danh sách, tìm kiếm, chỉnh sửa, xoá dữ liệu
- Kết nối và thao tác với cơ sở dữ liệu MySQL
- Áp dụng mô hình MVC

## 🛠️ Công nghệ sử dụng

| Thành phần        | Phiên bản đề xuất      |
|-------------------|-----------------------|
| Java              | 8 trở lên (11/17 khuyến nghị) |
| JSP & Servlet API | 2.3+ / 3.1+           |
| Apache Tomcat     | 8.5, 9.x, 10.x        |
| MySQL             | 5.7 hoặc 8.x          |
| JDBC              | -                     |
| Bootstrap         | 4.x/5.x (frontend UI) |
| IDE               | Eclipse/IntelliJ/NetBeans |

## 📦 Thư viện sử dụng

- **Servlet API** (javax.servlet: có sẵn trong Tomcat)
- **JSP API** (javax.servlet.jsp)
- **JSTL** (`javax.servlet:jstl:1.2`)
- **MySQL Connector/J** (`mysql:mysql-connector-java:8.x`)
- **Bootstrap** (CDN hoặc tải về)
- **jQuery** (nếu dùng JS nâng cao)

> 🎯 Nếu dùng Maven/Gradle, các thư viện sẽ tự động tải về. Nếu không, hãy tải file JAR và đặt vào thư mục `WEB-INF/lib`.

## 🧩 Yêu cầu phiên bản

- **Java:** 8 trở lên (khuyến nghị Java 11 hoặc 17)
- **Apache Tomcat:** 8.5, 9.x hoặc 10.x
- **MySQL:** 5.7 hoặc 8.x
- **IDE:** Eclipse, IntelliJ IDEA hoặc NetBeans bản mới nhất

## ⚡ Cài đặt & Hướng dẫn sử dụng

1. **Clone Repository**
   ```bash
   git clone https://github.com/267T/Java-Web-JSP-Servlet.git
   ```

2. **Import vào IDE**  
   Mở dự án bằng Eclipse, IntelliJ IDEA hoặc NetBeans.

3. **Thêm thư viện**  
   - Dùng Maven: kiểm tra file `pom.xml` đã đủ dependencies JSTL, MySQL Connector chưa.
   - Không dùng Maven: tải các file .jar và bỏ vào `WEB-INF/lib`.

4. **Cấu hình Database**  
   - Tạo cơ sở dữ liệu MySQL theo file `database.sql` trong thư mục dự án.
   - Cập nhật thông tin kết nối DB trong file `DBUtil.java`:
     ```java
     private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ten_database";
     private static final String JDBC_USERNAME = "root";
     private static final String JDBC_PASSWORD = "your_password";
     ```

5. **Chạy project trên Tomcat**

## 📁 Cấu trúc thư mục

```
Java-Web-JSP-Servlet/
├── src/
│   └── main/
│       ├── java/
│       │   └── ... (Servlets, Models, DAO)
│       └── webapp/
│           ├── WEB-INF/
│           │   └── lib/ (các file .jar nếu không dùng Maven)
│           └── ... (JSP files, resources)
├── database.sql
├── README.md
```

## 💡 Đóng góp

Nếu bạn muốn đóng góp, hãy **fork** dự án, tạo **branch** mới và gửi **pull request**.  
Rất hoan nghênh mọi ý kiến đóng góp!

## 📬 Liên hệ

- **Tác giả:** [267T](https://github.com/267T)
- **Email:** toannguyen260724@gmail.com


> Made with ❤️ by 267T
