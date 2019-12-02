package com.yyh.service.serviceimpl;

import com.yyh.entity.Page;
import com.yyh.entity.Url;
import com.yyh.service.IProcessService;
import com.yyh.util.*;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * CSDN页面解析实现类
 *
 * @author yihui.yan
 * @date 2019/11/13/013
 */
public class SohuProcessServiceImpl implements IProcessService {

    private static final String PARSE_TITLE = "//*[@id=\"article-container\"]/div[2]/div[1]/div[1]/div[1]/h1";
    private static final String PARSE_AUTHOR = "//*[@id=\"user-info\"]/h4/a";
    private static final String PARSE_DATE = "//*[@id=\"news-time\"]";

    @Override
    public void process(Page page) {

        String content = page.getContent();
        try {
            HtmlCleaner htmlCleaner = new HtmlCleaner();
            TagNode rootNode = htmlCleaner.clean(content);
            if (UrlRegexUtil.urlRegex(page.getUrl())) {
                //解析详情页
                this.processDetails(page, rootNode);
            } else {
                try {
                    getPageUrl(page);
                } catch (IOException e) {
                    //e.printStackTrace();
                    System.out.println("当前页面无法解析");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取页面中a标签的url地址并加入utlList中
     */
    private void getPageUrl(Page page) throws IOException {
        URL url = new URL(page.getUrl());
        Document doc = Jsoup.parse(url, 3000);
        Elements elements = doc.select("a");
        for (Element element : elements) {
            String aurl = element.attr("href").trim();
            if (aurl.startsWith("//")) {
                aurl = "http:" + aurl;
            }
            if (UrlRegexUtil.check(aurl) && !RedisCacheUtil.urlCheck(aurl) && DataBaseUtil.selectByUrl(aurl)) {
                Url url2 = new Url();
                url2.setUrlId(MD5Util.MD5(aurl));
                url2.setUrl(aurl);
                if (aurl.length() < 255) {
                    DataBaseUtil.urlinsert(url2);
                }
            }
        }
    }

    /**
     * 解析详情页,打印数据并将数据保存至数据库
     */
    private void processDetails(Page page, TagNode tagNode) {
        if (HtmlUtil.getFileByXpath(tagNode, PARSE_TITLE) != null) {
            //获取标题
            page.setTitle(HtmlUtil.getFileByXpath(tagNode, PARSE_TITLE));
            //获取作者
            page.setAuthor(HtmlUtil.getFileByXpath(tagNode, PARSE_AUTHOR));
            //获取时间
            page.setDate(HtmlUtil.getFileByXpath(tagNode, PARSE_DATE));
            //保存数据至数据库
            DataBaseUtil.datainsert(page);
        }
    }
}
