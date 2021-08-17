package com.es.jd.esjd.utils;

import com.es.jd.esjd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
        List<Content> goods = parseJD("经济学");
        for (Content good : goods) {
            System.out.println(good);
        }
    }

    public static List<Content> parseJD(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keyword + "&enc=utf-8";

        Document document = Jsoup.connect(url)
                .cookies(convertCookies(readFile("cookie.txt")))
                .get();
        Element element = document.getElementById("J_goodsList");

        Elements lis = element.getElementsByTag("li");

        List<Content> goods = new ArrayList<>();
        for (Element li : lis) {
            Elements elementsByClass = li.getElementsByClass("gl-i-wrap");

            // 这里简单判断了一下。以后前端解析可能还会变，需要对应修改。
            if (elementsByClass.size() > 0) {
                Element e = elementsByClass.get(0);
                String title = e.getElementsByClass("p-name").eq(0).text();
                String img = e.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = e.getElementsByClass("p-price").eq(0).text();

                goods.add(new Content(title, "https:" + img, price));
            }
        }
        return goods;
    }

    public static Map<String, String> convertCookies(String cookie) {
        Map<String, String> cookiesMap = new HashMap<>();
        String[] items = cookie.trim().split(";");
        for (String item : items) {
            String[] split = item.split("=");
            cookiesMap.put(split[0], split[1]);
        }
        return cookiesMap;
    }

    public static String readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource(filePath).getInputStream()));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = reader.readLine()) != null) {
            sb.append(s + "\n");
        }
        reader.close();
        return sb.toString();
    }
}
