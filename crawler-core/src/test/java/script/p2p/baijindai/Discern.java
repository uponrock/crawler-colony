package script.p2p.baijindai;

import com.dang.crawler.core.control.bean.Crawler;
import com.dang.crawler.core.control.bean.Job;
import com.dang.crawler.core.fetcher.bean.Page;
import com.dang.crawler.core.script.norm.Script;
import com.dang.crawler.core.script.norm.Task;
import com.dang.crawler.core.script.tools.DB;
import com.dang.crawler.core.script.tools.Fetch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mi on 2017/5/17.
 */
public class Discern implements Script {
    @Override
    public List<Task> work(Crawler crawler, Job job) throws Exception {
        Page page = Fetch.fetch(crawler);
        Map map = new HashMap();
        map.put("phone",crawler.get("phone"));
        map.put("source",job.getJobId());
       if(page.getContent().trim().equals("{\"ok\":\" \"}")){
           map.put("registered", "1");
       }else if(page.getContent().trim().equals("{\"error\":\"手机号不存在\"}")){
           map.put("registered", "0");
       }else  {
           log.error(page.getContent());
       }
       DB.insert("p2pblack_list","phone",map);
        return null;
    }
}
