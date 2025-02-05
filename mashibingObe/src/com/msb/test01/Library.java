package com.msb.test01;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {

        System.out.println("输入一下指令来进行您想要的操作");
        System.out.println("输入数字: 1, 查询系统中已经登录的书籍");
        System.out.println("输入数字：2, 添加书籍");
        System.out.println("输入数字: 3, 删除书籍");
        System.out.println("输入数字: 4, 退出图书管理系统");

        //创造集合来收集书本
        ArrayList<Book> books = new ArrayList<>();
        //存入文件
        File file = new File("/Users/guochaojun/Desktop/mashibing/图书列表.txt");

        while (true){
            System.out.println();
            System.out.println("<-----欢迎来到超军书城----->");
            try {
                //使用scanner来读取键盘的输入

                Scanner scanner = new Scanner(System.in);
                //获取scanner读取到的收入
                Integer number = scanner.nextInt();
                if (number == 1){
                    if (file.exists() && file.length()>0){
                        try {
                            FileInputStream fis = new FileInputStream(file);
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            //读取文件中存入的书籍
                            books = (ArrayList<Book>) ois.readObject();
                            ois.close();
                            if (books.isEmpty()){
                                System.out.println("请先添加书籍");
                            }else {
                                System.out.println("执行功能 "+ 1 + " 展示书籍");
                                for(Book book:books){
                                    System.out.println(book.toString());
                                }
                            }
                        }catch (EOFException e) {
                            System.out.println("文件为空，无法读取书籍！");}
                    }else{
                        System.out.println("文件为空或不存在，未能加载书籍");
                    }
                }else if (number == 2){//新增加书籍
                    try {
                        System.out.println("执行功能 "+ 2 + " 添加书籍");

                        Scanner scanner1 = new Scanner(System.in);

                        System.out.println("请输入你要添加的书籍的编号");

                        Integer bookNumber = scanner1.nextInt();
                        scanner1.nextLine();

                        System.out.println("请输入你要添加的书籍的名称");

                        String bookName = scanner1.nextLine();

                        System.out.println("请输入书籍的作者");

                        String author = scanner1.nextLine();

                        Book book = new Book(bookNumber,bookName,author);
                        //将对象存入文件中

                        FileOutputStream fop = new FileOutputStream(file);
                        ObjectOutputStream oop = new ObjectOutputStream(fop);

                        books.add(book);
                        //写入文件中
                        oop.writeObject(books);
                        oop.close();

                        if (books.contains(book)){
                            System.out.println("该书籍已经存在");
                        }else {
                            books.add(book);
                        }
                    }catch (Exception e){
                        System.out.println("请输入书籍名称");
                    }
                }else if (number == 3){
                    try {
                        System.out.println("执行功能 "+ 3 + " 删除书籍");
                        System.out.println("请输入你要删除的书籍的名称");

                        Scanner scanner1 = new Scanner(System.in);

                        String bookName = scanner1.nextLine();

                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);

                        //读取文件中存入的书籍
                        books = (ArrayList<Book>) ois.readObject();
                        ois.close();
                        Iterator<Book> iterator = books.iterator();
                        while (iterator.hasNext()) {
                            Book book = iterator.next();
                            if (book.getBookName().equals(bookName)) {
                                iterator.remove();  // ✅ 直接删除
                                System.out.println("书籍 \"" + bookName + "\" 已删除");
                                break;  // ✅ 只删除一本书后退出
                            }
                        }

                        // 重新写入文件
                        FileOutputStream fos = new FileOutputStream(file);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(books);  // ✅ 存入修改后的书籍列表
                        oos.close();  // ✅ 关闭流
                    }catch (Exception e) {
                        System.out.println("删除书籍时发生错误：" + e.getMessage());
                    }
                }else if (number == 4){
                    System.out.println("退出图书管理系统");
                    break;
                }

            }catch (Exception e){
                System.out.println("请输入数字1，2，3，4");
            }
        }
    }
}
