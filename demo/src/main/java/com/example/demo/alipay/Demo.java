package com.example.demo.alipay;

/**
 * @author caozhixin
 * @date 2024/1/7 20:58
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        String url = "https://quote.eastmoney.com/center/hszs.html";

        try {
            // 使用Jsoup连接到URL并获取HTML文档
            Document document = Jsoup.connect(url).get();

            // 打印整个HTML文档
            System.out.println(document.outerHtml());

            // 如果你只想获取文本内容，可以使用以下代码
            // String textContent = document.text();
            // System.out.println(textContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

