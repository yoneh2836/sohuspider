package com.yyh;

import com.yyh.entity.Url;
import com.yyh.util.DataBaseUtil;
import com.yyh.util.MD5Util;
import com.yyh.util.RedisCacheUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.Reader;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void redisCheckTest() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        if (jedis.exists(MD5Util.MD5("http://www.sohu.com/"))) {
            System.out.println("yes");
            ;
        } else {
            System.out.println("no");
        }
    }

    @Test
    public void setn() {
        System.out.println("006F592C1345635B1504C189ADDDE6B2".charAt(31) % 10);
    }

    @Test
    public void urlinsert() {
        Url url = new Url();
        url.setUrl("http://www.sohu.com/");
        url.setUrlId(MD5Util.MD5(url.getUrl()));
        String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession session;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (
                Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        session = sqlMapper.openSession();
        try {
            session.insert("insertUrl", url);
            session.commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void selectUrlTest() {
        if (DataBaseUtil.selectByUrl("http://pay.sohu.com/")) {
            System.out.println("flag = null");
        } else
            System.out.println("url has in musql");
    }

    @Test
    public void updateStatus() {
        String urlId = (MD5Util.MD5("http://www.sohu.com/"));
        System.out.println(urlId);
    }

    @Test
    public void selectUrls() {
        List<String> urls = DataBaseUtil.selectUrls();
        System.out.println(urls);
    }

    @Test
    public void redisLinkTest() {
        System.out.println(RedisCacheUtil.urlCheck("http://www.sohu.com/a/356749689_111151?sec=wd"));
    }

}