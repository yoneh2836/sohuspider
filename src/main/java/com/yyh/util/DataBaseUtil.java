package com.yyh.util;

import com.yyh.entity.Page;
import com.yyh.entity.Url;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * 添加数据工具类
 *
 * @author yihui.yan
 * Date: 2019/11/17/017
 * Time: 0:27
 */
public class DataBaseUtil {

    private static Reader reader;

    static {
        try {
            String resource = "mybatis-config.xml";
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

    /**
     * 添加解析数据
     */
    public static void datainsert(Page page) {
        SqlSession session = sqlMapper.openSession();
        session.insert("insert", page);
        session.commit();
        session.close();
    }

    /**
     * 添加页面解析出的url
     */
    public static void urlinsert(Url url) {
        SqlSession session = sqlMapper.openSession();
        session.insert("insertUrl", url);
        session.commit();
        session.close();
    }

    /**
     * 查询数据库中url是否存在
     *
     * @return true 不存在 false 存在
     */
    public static boolean selectByUrl(String url) {
        SqlSession session = sqlMapper.openSession();
        String flag = null;
        flag = session.selectOne("selectByUrl", url);
        session.close();
        return flag == null;
    }

    /**
     * 解析url后修改status字段
     */
    public static void updateStatus(String urlId) {

        SqlSession session = sqlMapper.openSession();
        session.update("updateStatus", urlId);
        session.commit();
        session.close();
    }

    /**
     * 从DB中获取未解析的url
     */
    public static List<String> selectUrls() {
        List<String> urls = new LinkedList<>();
        SqlSession session = sqlMapper.openSession();
        urls = session.selectList("selectUrls");
        session.close();
        return urls;
    }
}
