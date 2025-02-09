package com.msb.test02;

import java.sql.*;
import java.util.Scanner;

public class Test {

    public static void findBookByNum(int bno)throws SQLException,ClassNotFoundException {
        // 加载 MySQL 驱动（可选）
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取链接
        String url="jdbc:mysql://127.0.0.1:3306/msbDB?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";

        String username = "root";

        String password = "";

        Connection con = DriverManager.getConnection(url, username, password);

        //创建会话
        Statement stmt = con.createStatement();

        //发送sql请求
        ResultSet rs = stmt.executeQuery("select * from books where id = " + bno);

        //处理结果
        if (rs.next()) {
            System.out.println("图书编号 " + rs.getInt("id")+ " 图书名称" + rs.getString("name") + " 图书作者" + rs.getString("author") + " 书本价格 " + rs.getDouble("price"));
        }

        stmt.close();
        con.close();
    }

    public static void showAllBooks() throws ClassNotFoundException, SQLException {
        // 加载 MySQL 驱动（可选）
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取链接
        String url="jdbc:mysql://127.0.0.1:3306/msbDB?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";

        String username = "root";

        String password = "";

        Connection con = DriverManager.getConnection(url, username, password);

        //创建会话
        Statement stmt = con.createStatement();

        //发送sql请求
        ResultSet rs = stmt.executeQuery("select * from books");

        //处理结果
        while (rs.next()) {
            System.out.println("图书编号 " + rs.getInt("id")+ " 图书名称" + rs.getString("name") + " 图书作者" + rs.getString("author") + " 书本价格 " + rs.getDouble("price"));
        }

        stmt.close();
        con.close();
    }

    public static void createBook(Book book) throws ClassNotFoundException, SQLException {
        // 加载 MySQL 驱动（可选）
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取链接
        String url="jdbc:mysql://127.0.0.1:3306/msbDB?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";

        String username = "root";

        String password = "";
        // 建立数据库连接
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            // SQL 插入语句，使用 `?` 作为参数占位符
            String sql = "INSERT INTO books (id, name, author, price) VALUES (?, ?, ?, ?)";

            // 预编译 SQL 语句
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, book.getbNo());
                pstmt.setString(2, book.getBookName());
                pstmt.setString(3, book.getAuthor());
                pstmt.setDouble(4, book.getPrice());

                // 执行插入
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("✅ 书籍插入成功！");
                } else {
                    System.out.println("⚠️ 书籍插入失败！");
                }
            }
        }


    }


    public static void deleteBookByNum(Integer number) throws SQLException, ClassNotFoundException {
        // 加载 MySQL 驱动（可选）
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取链接
        String url="jdbc:mysql://127.0.0.1:3306/msbDB?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";

        String username = "root";

        String password = "";

        Connection con = DriverManager.getConnection(url, username, password);

        //创建会话
        Statement stmt = con.createStatement();

        //发送sql请求
        int result = stmt.executeUpdate("DELETE FROM books WHERE id = "+ number);

        stmt.close();
        con.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("输入一下指令来进行您想要的操作");
        System.out.println("输入数字: 1, 查询系统中已经登录的书籍");
        System.out.println("输入数字：2, 添加书籍");
        System.out.println("输入数字: 3, 删除书籍");
        System.out.println("输入数字: 4, 退出图书管理系统");

        while (true){
            System.out.println();
            System.out.println("<-----欢迎来到超军书城----->");

            Scanner scanner = new Scanner(System.in);

            Integer number = scanner.nextInt();

            if(number==1){
                showAllBooks();
            }else if (number==2){
                System.out.println("执行功能 "+ 2 + " 添加书籍");

                Scanner scanner1 = new Scanner(System.in);

                System.out.println("请输入你要添加的书籍的编号");

                Integer bookNumber = scanner1.nextInt();
                scanner1.nextLine();

                System.out.println("请输入你要添加的书籍的名称");

                String bookName = scanner1.nextLine();

                System.out.println("请输入书籍的作者");

                String author = scanner1.nextLine();

                System.out.println("请输入书本的价格");

                Double price = (Double)scanner1.nextDouble();

                Book book = new Book(bookNumber,bookName,author,price);

                createBook(book);
            }else if(number==3){

                System.out.println("请输入你想删除的书籍的编号");
                Scanner scanner1 = new Scanner(System.in);
                Integer bookNumber = scanner1.nextInt();
                deleteBookByNum(bookNumber);
            } else if (number==4) {
                System.out.println("退出图书管理系统");
                break;
            }
        }
    }
}
