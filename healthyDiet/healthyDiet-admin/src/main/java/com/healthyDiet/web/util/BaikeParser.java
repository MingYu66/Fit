package com.healthyDiet.web.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
public class BaikeParser {
    public static String parseBaike(String word) {
        String url = "https://baike.baidu.com/item/" + word;
        String userAgent = "Mozilla/5.0 (Linux; Android 8.0.0; SM-G955U Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36";
        try {
            Document doc = Jsoup.connect(url).userAgent(userAgent).get();

            StringBuilder subTitleBuilder = new StringBuilder();
            // 解析 lemma
            Element lemma = doc.selectFirst("li.extra-list-item.extra-lemma-desc");
            if (lemma != null) {
                subTitleBuilder.append(lemma.text());
            }

            // 解析 描述
            Element meta = doc.selectFirst("meta[name=description]");
            String description = meta != null ? meta.attr("content") : "";

            // 解析配料应用
            Elements basicInfoList = doc.select("li.basicInfo-hide");
            for (Element basicInfo : basicInfoList) {
                Element titleElement = basicInfo.selectFirst("div.info-title");
                Element contentElement = basicInfo.selectFirst("div.info-content");

                if (titleElement != null && contentElement != null) {
                    String title = titleElement.text().trim();
                    String content = contentElement.text().trim();

                    if ("应用".equals(title) || "属性".equals(title)) {
                        subTitleBuilder.append(", ").append(content);
                    }
                }
            }

            return subTitleBuilder.toString() + ", " + description;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        String word = "苯甲酸";
        String result = parseBaike(word);
        System.out.println(result);
    }
}
