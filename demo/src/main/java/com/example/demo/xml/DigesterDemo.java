package com.example.demo.xml;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DigesterDemo {
    public static void main(String[] args) throws IOException, SAXException {
        Digester digester = new Digester();

        // 设置要解析的XML文件路径
        URL url = DigesterDemo.class.getResource("/books.xml");
        File file = new File(url.getFile());

        // 添加规则
        digester.addObjectCreate("bookstore/book", Book.class);
        digester.addSetProperties("bookstore/book", "category", "category");
        digester.addCallMethod("bookstore/book/title", "setTitle", 1);
        digester.addCallMethod("bookstore/book/author", "setAuthor", 1);
        digester.addCallMethod("bookstore/book/year", "setYear", 1);
        digester.addCallMethod("bookstore/book/price", "setPrice", 1);
        digester.addSetNext("bookstore/book", "add");

        // 解析XML
        List<Book> books = (List<Book>) digester.parse(file);

        // 输出结果
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
