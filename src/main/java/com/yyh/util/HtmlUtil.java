package com.yyh.util;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

/**
 * 页面解析工具类
 * @author yihui.yan
 * Date: 2019/11/15/015
 * Time: 22:48
 */
public class HtmlUtil {

    public static String getFileByXpath(TagNode rootNode, String xpath) {
        String number = null;
        Object[] evaluateXpath = null;
        try {
            evaluateXpath = rootNode.evaluateXPath(xpath);
            if (evaluateXpath.length > 0) {
                TagNode node = (TagNode) evaluateXpath[0];
                number = node.getText().toString().trim();
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return number;
    }
}
