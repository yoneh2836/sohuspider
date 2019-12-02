package com.yyh.util;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url匹配类
 * @author yihui.yan
 * Date: 2019/11/15/015
 * Time: 14:29
 */
public class UrlRegexUtil {

    private static final Pattern PATTERN_ARTICLE = Pattern.compile("http://www.sohu.com/a/");
    private static final Pattern PATTERN_SOHU = Pattern.compile("http[s]{0,1}://[a-z]{0,8}.sohu.com/");
    /**
     * 判断url地址是否为新闻主页
     */
    public static boolean urlRegex(String url) {

        Matcher matcher = PATTERN_ARTICLE.matcher(url);
        return matcher.find();
    }

    /**
     * 判断url是否为sohu的url
     */
    public static boolean check(String url) {
        if (url.endsWith("html")||url.startsWith("http://tv")||url.endsWith("jpg")
                ||url.startsWith("http://blog")||url.startsWith("http://auto")) {
            return false;
        }else {
            Matcher matcher = PATTERN_SOHU.matcher(url);
            return matcher.find();
        }
    }

    public static void main(String[] args) {
        System.out.println(UrlRegexUtil.check("http://www.sohu.com/shtml"));
    }
}
