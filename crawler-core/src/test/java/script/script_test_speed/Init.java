package script.script_test_speed;


import com.dang.crawler.core.control.bean.Crawler;
import com.dang.crawler.core.control.bean.Job;
import com.dang.crawler.core.script.norm.Script;
import com.dang.crawler.core.script.norm.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 2017/5/12.
 */
public class Init implements Script {
    @Override
    public List<Task> work(Crawler crawler,Job job) throws Exception {
        crawler.setUrl("https://www.hao123.com/");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(crawler,new FastCrawler()));
       return tasks;
    }


}
