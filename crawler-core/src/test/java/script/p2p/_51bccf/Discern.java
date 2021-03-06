package script.p2p._51bccf;

import com.dang.crawler.core.control.bean.Crawler;
import com.dang.crawler.core.control.bean.Job;
import com.dang.crawler.core.script.norm.Script;
import com.dang.crawler.core.script.norm.Task;
import com.dang.crawler.core.script.tools.DB;
import com.dang.crawler.core.script.tools.Fetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mi on 2017/5/17.
 */
public class Discern implements Script {
    @Override
    public List<Task> work(Crawler crawler, Job job) throws Exception {
        WebDriver web = Fetch.getWebDriver();
        web.get("https://www.51bccf.com/regist.page");
        Thread.sleep(5000);
        web.findElement(By.xpath(".//*[@name='txtUserId']")).sendKeys(crawler.get("phone").toString());
        web.findElement(By.id("inputVerifycode")).click();
        Thread.sleep(2000);
        String html = web.getPageSource();
        Fetch.freeDriver(web);
        Map map = new HashMap();
        map.put("phone",crawler.get("phone"));
        map.put("source",job.getJobId());
       if(html.contains("手机号码已被注")){
           map.put("registered","1");
       }else {
           map.put("registered","0");
       }
       DB.insert("p2pblack_list","phone",map);
        return null;
    }
}
