package com.msb.mapper;

import com.msb.pojo.Book;

import java.util.List;

public interface BookMapper {

    /*public abstract */List selectAllBooks();
    public abstract Book selectOneBook(String name,String author);
    public abstract Book selectOneBook2(Book book);
    public abstract Book selectOneBook3(String name,Book book);
    public abstract int insertBook(Book book);
}
