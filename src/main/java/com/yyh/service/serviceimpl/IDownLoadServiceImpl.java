package com.yyh.service.serviceimpl;

import com.yyh.entity.Page;
import com.yyh.service.IDownLoadService;
import com.yyh.util.PageDownLoadUtil;

/**
 * 页面下载
 * @author yihui.yan
 * Date: 2019/11/13/013
 * Time: 22:07
 */
public class IDownLoadServiceImpl implements IDownLoadService {

    @Override
    public Page download(String url) {
        Page page = new Page();
        page.setContent(PageDownLoadUtil.getPageContent(url));
        return page;
    }
}
