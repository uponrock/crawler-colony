package com.dang.crawler.core.fetcher.service;

import java.util.*;

/**
 * Created by dang on 17-5-10.
 * 资源轮循池
 */
public abstract class RevolPool<T> {
    private final int minPoolSize;
    private final int maxPoolSize;
    private final int timeout;
    private int size = 0;
    private Queue<T> freeQueue;
    private Queue<T> usingQueue;
    private Map<T,Date> dateMap;
    private Map<T,Integer> countMap;

    public RevolPool(int minPoolSize,int maxPoolSize,int timeout){
        this.minPoolSize = minPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.timeout = timeout;
        freeQueue = new ArrayDeque<T>(maxPoolSize);
        usingQueue = new ArrayDeque<T>(maxPoolSize);
        dateMap = new HashMap<>(maxPoolSize);
        countMap = new HashMap<>(maxPoolSize);
    }

    public synchronized T getFree() {
        size++;
        T result;
        if (freeQueue.size() > 0) {//返回释放的
            result =freeQueue.poll();
        } else if (size < maxPoolSize) {//创建一个新的
            result = make();
            countMap.put(result,0);
        } else {//没有手动释放的了   返回超时的
            long now = new Date().getTime();
            result = usingQueue.poll();
            if (dateMap.get(result).getTime()+timeout<now){//还未失效
                try {
                    Thread.sleep(now-(dateMap.get(result).getTime()+timeout));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dateMap.put(result,new Date());
        countMap.put(result,countMap.get(result)+1);
        usingQueue.add(result);
        return result;

    }
    public synchronized void toFree(T t){
        size--;
        usingQueue.remove(t);
        if(freeQueue.size()<minPoolSize&&size<maxPoolSize&&countMap.get(t)<50){//进行回收
            dateMap.remove(t);
            freeQueue.add(t);
        }else {//抛弃
            dateMap.remove(t);
            countMap.remove(t);
            destroy(t);
            Set<String> strings = new HashSet<>();
        }
    }

    protected abstract void destroy(T t);

    protected abstract T make();

}
