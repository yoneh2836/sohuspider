package com.yyh.entity;

/**
 * @author yihui.yan
 * @date 2019/11/27/027
 */
public class Url {
    /**
     * url MD5
     */
    private String urlId;
    /**
     * url
     */
    private String url;

    public Url() {
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
