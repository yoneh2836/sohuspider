package com.yyh.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储页面信息实体类
 *
 * @author yihui.yan
 * Date: 2019/11/13/013
 * Time: 21:56
 */
public class Page {
    /**
     * 页面内容
     */
    private String content;
    /**
     * url MD5数据库唯一识别码
     */
    private String urlId;
    /**
     * 标题
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 日期
     */
    private String date;
    /**
     * 页面url
     */
    private String url;
    /**
     * 页面对应的数据库参数
     */
    private int n;
    /**
     * 页面包含的url
     */
    private List<String> urlList = new ArrayList<String>();

    public Page() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getn() {
        return n;
    }

    public void setn(int n) {
        this.n = n;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    /**
     * 添加url
     */
    public void addUrl(String url) {
        this.urlList.add(url);
    }
}
