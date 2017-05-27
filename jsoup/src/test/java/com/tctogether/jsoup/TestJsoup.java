package com.tctogether.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 文 件 名: TestJsoup
 * 创 建 人: CXP
 * 创建日期: 2017-01-14 16:03
 * 描    述: Jsoup用法
 * 修 改 人:
 * 修改时：
 * 修改备注：
 */
public class TestJsoup {

    //将部分html代码转换为完整的html
    @Test
    public void demo1() {
        String html = "<p>CXP";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.toString());
    }

    //将部分html代码转换为完整的html
    @Test
    public void demo2() {
        String html = "<div><p>哈哈哈</p>";
        //用body包起来 上面的也可以做到
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        System.out.println(body.toString());
    }

    //解析一个外链的html
    @Test
    public void demo3() {
        try {
            Document doc = Jsoup.connect("http://123.56.75.125:18080/index.html").get();
            System.out.println(doc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //解析本地html
    @Test
    public void demo4() {
        try {
            File input = new File("G:/index.html");
            Document doc = Jsoup.parse(input, "UTF-8");
            System.out.println(doc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
