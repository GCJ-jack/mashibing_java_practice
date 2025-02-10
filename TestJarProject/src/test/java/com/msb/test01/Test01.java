package com.msb.test01;

import java.sql.*;

public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        System.out.println(System.getProperty("java.class.path"));

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
            System.out.println("图书编号 " + rs.getInt("id")+ " 图书名称" + rs.getString("name") + " 图书作者" + rs.getString("author"));
        }

    }
}
