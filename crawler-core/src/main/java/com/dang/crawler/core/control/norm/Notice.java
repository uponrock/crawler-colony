package com.dang.crawler.core.control.norm;


/**
 * Created by dang on 17-5-11.
 * 公示器 随机get
 */
public interface Notice<KEY,VALUE> {
    VALUE notice();
    boolean put(KEY key, VALUE value);
    VALUE remove(KEY key);
}
