package com.dang.crawler.core.fetcher;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dang.crawler.core.fetcher.utils.HttpClientUtil;
import org.junit.Test;

public class HttpClientUtilTest {

    @Test
    public void testSendHttpPost1() {
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost("https://www.ibank365.cn/banks/member/member/checkMobile.json?mobile=17060910209");
        System.out.println("reponse content:" + responseContent);
    }

    @Test
    public void testSendHttpPost2() {
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost("https://www.caifupad.com/user/isMobileNoToSendMsg", "mobileNo=18310977759");
        System.out.println("reponse content:" + responseContent);
    }

    @Test
    public void testSendHttpPost3() {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("username", "test01");
        maps.put("password", "123456");
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost("http://localhost:8089/test/send", maps);
        System.out.println("reponse content:" + responseContent);
    }
    @Test
    public void testSendHttpPost4() {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("username", "test01");
        maps.put("password", "123456");
        List<File> fileLists = new ArrayList<File>();
        fileLists.add(new File("D://test//httpclient//1.png"));
        fileLists.add(new File("D://test//httpclient//1.txt"));
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost("http://localhost:8089/test/sendpost/file", maps, fileLists);
        System.out.println("reponse content:" + responseContent);
    }

    @Test
    public void testSendHttpGet() {
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpGet("http://localhost:8089/test/send?username=test01&password=123456");
        System.out.println("reponse content:" + responseContent);
    }

    @Test
    public void testSendHttpsGet() {
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpsGet("https://www.baidu.com");
        System.out.println("reponse content:" + responseContent);
    }

}
