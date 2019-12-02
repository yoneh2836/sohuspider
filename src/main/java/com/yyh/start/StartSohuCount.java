package com.yyh.start;

import com.yyh.entity.Page;
import com.yyh.service.IDownLoadService;
import com.yyh.service.IProcessService;
import com.yyh.service.serviceimpl.IDownLoadServiceImpl;
import com.yyh.service.serviceimpl.SohuProcessServiceImpl;
import com.yyh.util.DataBaseUtil;
import com.yyh.util.MD5Util;
import com.yyh.util.RedisCacheUtil;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CSDN爬虫执行入口类
 * @author yihui.yan
 * Date: 2019/11/13/013
 * Time: 22:13
 */
public class StartSohuCount {

    private IDownLoadService downLoadService;
    private IProcessService processService;
    private Queue<String> urlQueue = new ConcurrentLinkedDeque<>();
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 8, 120, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(8), new ThreadPoolExecutor.DiscardOldestPolicy());

    private void setProcessService(IProcessService processService) {
        this.processService = processService;
    }

    private void setDownLoadService(IDownLoadService downLoadService) {
        this.downLoadService = downLoadService;
    }

    /**
     * 下载页面
     */
    private Page downLoadPage(String url) {
        return this.downLoadService.download(url);
    }

    /**
     * 解析
     */
    private void processPage(Page page) {
        this.processService.process(page);
    }

    /**
     * 开启一个爬虫入口
     */
    private void startSpider() {
        //从队列中提取需解析的url
        String urlInner = urlQueue.poll();
        if (!RedisCacheUtil.urlCheck(urlInner)) {
            //下载
            Page pageInner = StartSohuCount.this.downLoadPage(urlInner);
            pageInner.setUrl(urlInner);
            //解析
            StartSohuCount.this.processPage(pageInner);
            //已解析url存入redis缓存
            RedisCacheUtil.urlCache(MD5Util.MD5(urlInner), urlInner);
        }

        while (true) {
            if (urlQueue.isEmpty()) {
                urlQueue.addAll(DataBaseUtil.selectUrls());
            } else {
                executor.execute(() -> {
                    //从队列中提取需解析的url
                    String url = urlQueue.poll();
                    //下载
                    Page page = StartSohuCount.this.downLoadPage(url);
                    //设置参数
                    page.setUrl(url);
                    page.setUrlId(MD5Util.MD5(url));
                    page.setn(MD5Util.setn(page.getUrlId()));
                    //修改status字段
                    DataBaseUtil.updateStatus(page.getUrlId());
                    //已解析的url存入Redis缓存
                    RedisCacheUtil.urlCache(page.getUrlId(), url);
                    //解析
                    StartSohuCount.this.processPage(page);
                });
            }
        }
    }

    public static void main(String[] args) {

        StartSohuCount sohu = new StartSohuCount();
        sohu.setDownLoadService(new IDownLoadServiceImpl());
        sohu.setProcessService(new SohuProcessServiceImpl());

        String url = "http://www.sohu.com/";

        sohu.urlQueue.add(url);
        //启动spider
        sohu.startSpider();
    }
}
