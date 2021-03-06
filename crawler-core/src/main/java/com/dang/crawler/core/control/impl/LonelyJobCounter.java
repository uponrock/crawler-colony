package com.dang.crawler.core.control.impl;

import com.dang.crawler.core.control.bean.Job;
import com.dang.crawler.core.control.norm.JobCounter;
import com.dang.crawler.core.serivce.ApplicationContext;
import com.dang.crawler.resources.mysql.dao.CrawlerLogMapper;
import com.dang.crawler.resources.mysql.model.CrawlerLog;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by dang on 2017/5/12.
 * 单机Job计数器，负责存储运行的job统计信息
 */
public class LonelyJobCounter implements JobCounter {
    private CrawlerLogMapper crawlerLogMapper;
    private Map<String,Map<String,Integer>> map = new HashMap<>();
    private volatile int sun = 0;
    @Override
    public synchronized Integer update(Job job, String name, Integer count) {
        int result = 0;
        Map<String, Integer> v = map.get(getKey(job));
        if(v ==null){
            Map<String, Integer> newValue = new HashMap<>();
            newValue.put(name,count);
            map.put(getKey(job),newValue);
            result = count;
        }else {
            if(v.containsKey(name)){
                int value = v.get(name);
                v.put(name,value+count);
                result = value+count;
            }else {
                v.put(name,count);
                result = count;
            }
        }

            if (sun++ % 1000 == 0) {
                flush(null);
            }

        return result;
    }
    public synchronized void flush(Job job){
        for (Map.Entry<String,Map<String,Integer>> entry : map.entrySet()) {
            //Job key = entry.getKey();
            Map<String,CrawlerLog> logMap = new HashMap<>();
            for(Map.Entry<String,Integer> values : entry.getValue().entrySet()){
                if(values.getKey().contains(":")){
                    String[] attr = values.getKey().split(":");
                    String type = attr[0];
                    String taskName = attr[1];
                    CrawlerLog log = logMap.get(taskName);
                    if(log == null) {
                        String[] key = entry.getKey().split(":");
                        log = new CrawlerLog(key[0],taskName,Long.parseLong(key[1]));
                    }
                    switch (type){
                        case "taskToDo":{log.setToDoCount(values.getValue());break;}
                        case "taskFail":{log.setFailCount(values.getValue());break;}
                        case "taskSuccess":{log.setSuccessCount(values.getValue());break;}
                    }
                    logMap.put(taskName,log);
                }
            }
            for(Map.Entry<String,CrawlerLog> logEntry : logMap.entrySet()){
                if(crawlerLogMapper==null){
                    crawlerLogMapper = (CrawlerLogMapper) ApplicationContext.getBean("crawlerLogMapper");
                }
                crawlerLogMapper.update(logEntry.getValue());
            }
        }
    }

    @Override
    public synchronized Integer get(Job job, String name) {
        Map<String, Integer> v = map.get(getKey(job));
        if(v!=null){
            if(v.get(name)!=null){
                return v.get(name);
            }
        }
        return 0;
    }

    @Override
    public boolean remove(Job job) {
        map.remove(getKey(job));
        return false;
    }
    private String getKey(Job job){
        return job.getJobId()+":"+job.getFlag();
    }

}
