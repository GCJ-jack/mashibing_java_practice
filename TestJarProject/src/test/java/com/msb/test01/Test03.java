package com.msb.test01;

import com.msb.mapper.BookMapper;
import com.msb.pojo.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class Test03 {

    public static void main(String[] args) throws IOException {

        // create and configure beans
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


        String resource = "mybatis.xml";
        //获取文件加载的输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //加载配置文件获取工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂类获得一个会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        //把对象从spring容器中取出来
        Book book = (Book) context.getBean("book");

        book.setAuthor("罗贯中");
        book.setBookName("三国演义");


        Book book2 = mapper.selectOneBook(book.getBookName(),book.getAuthor());

        System.out.println(book2.getBookName());
        sqlSession.close();
    }
}
