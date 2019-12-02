package com.yyh.util;

import com.yyh.entity.Page;
import com.yyh.service.serviceimpl.IDownLoadServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 页面下载工具类
 * @author yihui.yan
 * Date: 2019/11/13/013
 * Time: 21:43
 */
public class PageDownLoadUtil {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

    public static String getPageContent(String url) {
        //解决cookie rejected警告
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();

        HttpGet request = new HttpGet(url);
        String content = null;
        try {
            //模拟浏览器请求url
            request.setHeader("User-Agent", USER_AGENT);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
        } catch (IOException e) {
            System.out.println("当前url请求失败");
            //e.printStackTrace();
        }
        return content;
    }
}
