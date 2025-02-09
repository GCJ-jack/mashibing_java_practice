package com.msb.test02;

import java.io.Serializable;

public class Book implements Serializable {

    private Integer bNo;

    private String bookName;

    private String author;

    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getbNo() {
        return bNo;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setbNo(Integer bNo) {
        this.bNo = bNo;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book(Integer bNo, String bookName, String author, Double price) {
        this.bNo = bNo;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }

    public Book(){
    }

    public String toString() {
        return "———————————————\n" +
                "📖 书籍编号：" + bNo + "\n" +
                "📚 书籍名称：" + (bookName.isEmpty() ? "无" : bookName) + "\n" +
                "✍️  作者：" + (author.isEmpty() ? "无" : author) + "\n" +
                "———————————————";
    }
}