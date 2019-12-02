package com.yyh.service;

import com.yyh.entity.Page;

/**
 * 页面下载接口
 * @author yihui.yan
 * Date: 2019/11/13/013
 * Time: 22:05
 */
public interface IDownLoadService {
    /**
     *下载页面
     * @param url:页面URL
     * @return 返回页面
     */
    Page download(String url);
}
