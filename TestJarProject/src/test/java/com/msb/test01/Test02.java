package com.msb.test01;

import com.msb.pojo.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Test02 {
    public static void main(String[] args) throws IOException {
        //制定核心配置文件
        String resource = "mybatis.xml";
        //获取文件加载的输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //加载配置文件获取工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂类获得一个会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行查询
        List list = sqlSession.selectList("Book.selectAllBooks");

        //遍历数据库中的书本
        for(int i = 0; i < list.size(); i++){
            Book book = (Book) list.get(i);
            System.out.println("书本编号: " + book.getbNo() + " 书本名称: " + book.getBookName() + " 书本作者: " + book.getAuthor() + " 书本价格: " + book.getPrice());
        }
        //资源关闭
        sqlSession.close();
    }
}
