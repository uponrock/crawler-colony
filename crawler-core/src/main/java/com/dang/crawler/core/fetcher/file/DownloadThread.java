package com.dang.crawler.core.fetcher.file;

import com.dang.colony.resources.utils.FileUtils;
import com.dang.crawler.core.bean.CrawlerMQ;
import com.dang.crawler.core.fetcher.PageService;
import com.dang.crawler.core.fetcher.bean.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by duang on 2017/4/30.
 */
class DownloadThread implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(DownloadThread.class);
    private Downloader downloader;
    public DownloadThread(Downloader downloader){
        this.downloader = downloader;
    }
    public void run() {
        CrawlerMQ mq = null;
        while ((mq=downloader.getURL())!=null){
            try {
                Page page = PageService.fetcher(mq.getRequest(), null);
                saveFile(getPath(mq),page);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        downloader.finish();
    }

    private String getPath(CrawlerMQ mq) {//http://wx1.sinaimg.cn/mw690/00685TFIgy1fexs9ewr17j30j30vwgpz.jpg
        String url = mq.getRequest().getUrl();
        String path ="D:\\Files\\MyCode\\outPut\\"+ url.substring(url.lastIndexOf("/"), url.length());
        return path;
    }

    private void saveFile(String path,Page page) {
        try {
            FileUtils.save(path,page.getResponseBytes());
        } catch (IOException e) {
            logger.error("file save error",e);
        }
    }
}