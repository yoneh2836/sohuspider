package com.yyh.service;

import com.yyh.entity.Page;

/**
 * 页面解析接口
 * @author yihui.yan
 * Date: 2019/11/13/013
 * Time: 22:34
 */
public interface IProcessService {
    /**
     * 解析页面
     * @param page:要解析的页面
     */
    void process(Page page);
}
