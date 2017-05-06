/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : crawler-colony

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2017-05-04 12:23:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for job_code
-- ----------------------------
DROP TABLE IF EXISTS `job_code`;
CREATE TABLE `job_code` (
  `taskId` varchar(63) NOT NULL,
  `name` varchar(255) NOT NULL,
  `code` text,
  `class` blob,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`taskId`,`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_code
-- ----------------------------
INSERT INTO `job_code` VALUES ('task_test_1', 'com.dang.crawler.core.script.Page', '', null, '2017-05-03 17:44:35');
INSERT INTO `job_code` VALUES ('sina_weibo', 'Detail', 'package script.sina_weibo;\n\n\nimport com.dang.crawler.resources.bean.core.CrawlerJob;\nimport com.dang.crawler.resources.bean.core.CrawlerMQ;\nimport com.dang.crawler.resources.bean.core.Job;\n\nimport java.util.List;\n\n/**\n * Created by mi on 2017/5/3.\n */\npublic class Detail implements CrawlerJob {\n    @Override\n    public List<Job> work(CrawlerMQ crawlerMQ) throws Exception {\n       System.out.println(\"xxxxxxxxxxxxxxxxxxxxxxxxxxx\");\n       return null;\n    }\n}\n', 0xCAFEBABE0000003400240A0006001409001500160800170A0018001907001A07001B07001C0100063C696E69743E010003282956010004436F646501000F4C696E654E756D6265725461626C65010004776F726B010042284C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724D513B294C6A6176612F7574696C2F4C6973743B01000A457863657074696F6E7307001D0100095369676E617475726501006E284C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724D513B294C6A6176612F7574696C2F4C6973743C4C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F4A6F623B3E3B01000A536F7572636546696C6501000B44657461696C2E6A6176610C0008000907001E0C001F002001001B7878787878787878787878787878787878787878787878787878780700210C002200230100187363726970742F73696E615F776569626F2F44657461696C0100106A6176612F6C616E672F4F626A65637401002F636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724A6F620100136A6176612F6C616E672F457863657074696F6E0100106A6176612F6C616E672F53797374656D0100036F75740100154C6A6176612F696F2F5072696E7453747265616D3B0100136A6176612F696F2F5072696E7453747265616D0100077072696E746C6E010015284C6A6176612F6C616E672F537472696E673B295600210005000600010007000000020001000800090001000A0000001D00010001000000052AB70001B100000001000B0000000600010000000D0001000C000D0003000A00000026000200020000000AB200021203B6000401B000000001000B0000000A00020000001000080011000E000000040001000F001000000002001100010012000000020013, '2017-05-04 11:13:22');
INSERT INTO `job_code` VALUES ('sina_weibo', 'Init', 'package   script.sina_weibo   ;\n\n\nimport com.dang.crawler.resources.bean.core.CrawlerJob;\nimport com.dang.crawler.resources.bean.core.CrawlerMQ;\nimport com.dang.crawler.resources.bean.core.Job;\n\nimport java.util.ArrayList;\nimport java.util.List;\n\n/**\n * Created by mi on 2017/5/3.\n */\npublic class Init implements CrawlerJob {\n    @Override\n    public List<Job> work(CrawlerMQ mq) throws Exception {\n        System.out.println(System.getProperty(\"user.dir\"));//user.dir指定了当前的路径\n        String jsonPath = System.getProperty(\"user.dir\") + \"\\\\crawler-core\\\\src\\\\main\\\\java\\\\com\\\\dang\\\\crawler\\\\core\\\\script\\\\\" + \"weibo_user.txt\";\n        //String json = FileUtils.getString(jsonPath);\n        //JSONArray array = JSONObject.parseArray(json);\n        List<CrawlerMQ> crawlerMQList = new ArrayList<>();\n        String []array = {\"5616413326\",\"5763755594\",\"2654037900\"};\n        for (int i = 0; i < array.length; i++) {\n            String userId = array[i];\n            System.out.println(\"===========================================================================\" + i);\n            CrawlerMQ crawlerMQ = new CrawlerMQ();\n            crawlerMQ.getRequest().setUrl(\"http://weibo.com/u/\" + userId + \"?topnav=1&wvr=6&topsug=1\");\n            crawlerMQ.getExtending().put(\"userId\", userId);\n            crawlerMQList.add(crawlerMQ);\n        }\n        ArrayList<Job> jobList = new ArrayList<Job>();\n        jobList.add(new Job(crawlerMQList, \"page\"));\n        return jobList;\n    }\n}\n', 0xCAFEBABE0000003400760A0022003609003700380800390A0037003A0A003B003C07003D0A000600360A0006003E08003F0A000600400700410A000B00360700420800430800440800450800460A000600470700480A001300360A0013004908004A08004B0A004C004D0A0013004E08004F0B005000510B005200530700540800550A001D00560A000B00530700570700580700590100063C696E69743E010003282956010004436F646501000F4C696E654E756D6265725461626C65010004776F726B010042284C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724D513B294C6A6176612F7574696C2F4C6973743B01000D537461636B4D61705461626C6507005707004807004207005A07005B01000A457863657074696F6E7307005C0100095369676E617475726501006E284C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724D513B294C6A6176612F7574696C2F4C6973743C4C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F4A6F623B3E3B01000A536F7572636546696C65010009496E69742E6A6176610C0024002507005D0C005E005F010008757365722E6469720C006000610700620C006300640100176A6176612F6C616E672F537472696E674275696C6465720C006500660100475C637261776C65722D636F72655C7372635C6D61696E5C6A6176615C636F6D5C64616E675C637261776C65725C636F72655C7363726970745C776569626F5F757365722E7478740C006700680100136A6176612F7574696C2F41727261794C6973740100106A6176612F6C616E672F537472696E6701000A3536313634313333323601000A3537363337353535393401000A3236353430333739303001004B3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D0C0065006901002E636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724D510C006A006B010013687474703A2F2F776569626F2E636F6D2F752F0100183F746F706E61763D31267776723D3626746F707375673D3107006C0C006D00640C006E006F0100067573657249640700700C0071007207005A0C00730074010028636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F4A6F62010004706167650C002400750100167363726970742F73696E615F776569626F2F496E69740100106A6176612F6C616E672F4F626A65637401002F636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724A6F6201000E6A6176612F7574696C2F4C6973740100135B4C6A6176612F6C616E672F537472696E673B0100136A6176612F6C616E672F457863657074696F6E0100106A6176612F6C616E672F53797374656D0100036F75740100154C6A6176612F696F2F5072696E7453747265616D3B01000B67657450726F7065727479010026284C6A6176612F6C616E672F537472696E673B294C6A6176612F6C616E672F537472696E673B0100136A6176612F696F2F5072696E7453747265616D0100077072696E746C6E010015284C6A6176612F6C616E672F537472696E673B2956010006617070656E6401002D284C6A6176612F6C616E672F537472696E673B294C6A6176612F6C616E672F537472696E674275696C6465723B010008746F537472696E6701001428294C6A6176612F6C616E672F537472696E673B01001C2849294C6A6176612F6C616E672F537472696E674275696C6465723B01000A6765745265717565737401003028294C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F526571756573743B01002C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F5265717565737401000673657455726C01000C676574457874656E64696E6701001128294C6A6176612F7574696C2F4D61703B01000D6A6176612F7574696C2F4D6170010003707574010038284C6A6176612F6C616E672F4F626A6563743B4C6A6176612F6C616E672F4F626A6563743B294C6A6176612F6C616E672F4F626A6563743B010003616464010015284C6A6176612F6C616E672F4F626A6563743B295A010025284C6A6176612F7574696C2F4C6973743B4C6A6176612F6C616E672F537472696E673B29560021002100220001002300000002000100240025000100260000001D00010001000000052AB70001B10000000100270000000600010000000E000100280029000300260000014200050008000000D0B200021203B80004B60005BB000659B700071203B80004B600081209B60008B6000A4DBB000B59B7000C4E06BD000D5903120E535904120F5359051210533A0403360515051904BEA2006C19041505323A06B20002BB000659B700071211B600081505B60012B6000AB60005BB001359B700143A071907B60015BB000659B700071216B600081906B600081217B60008B6000AB600181907B60019121A1906B9001B0300572D1907B9001C020057840501A7FF92BB000B59B7000C3A051905BB001D592D121EB7001FB60020571905B00000000200270000003E000F00000011000B001200230015002B001600400017004B001800520019006C001A0075001B0096001C00A5001D00AE001700B4001F00BD002000CD0021002A0000001C0002FF0043000607002B07002C07002D07002E07002F010000FA007000300000000400010031003200000002003300010034000000020035, '2017-05-04 11:13:23');
INSERT INTO `job_code` VALUES ('sina_weibo', 'Page', 'package script.sina_weibo;\n\nimport com.dang.crawler.core.bean.Base;\nimport com.dang.crawler.core.fetcher.file.Downloader;\nimport com.dang.crawler.core.fetcher.service.WebDriverFactory;\nimport com.dang.crawler.resources.bean.core.CrawlerJob;\nimport com.dang.crawler.resources.bean.core.CrawlerMQ;\nimport com.dang.crawler.resources.bean.core.Job;\nimport com.dang.crawler.resources.mongodb.MongoDB;\nimport com.dang.crawler.resources.utils.DateUtils;\nimport org.bson.Document;\nimport org.openqa.selenium.JavascriptExecutor;\nimport org.openqa.selenium.WebDriver;\nimport java.util.ArrayList;\nimport java.util.Date;\nimport java.util.List;\n/**\n * Created by mi on 2017/5/3.\n */\npublic class Page implements CrawlerJob {\n    public List<Job> work(CrawlerMQ crawlerMQ) throws Exception {\n        WebDriver web = WebDriverFactory.getwebDriver(WebDriverFactory.Driver.phantomjs);\n        web.get(crawlerMQ.getRequest().getUrl());\n//        WebElement input = web.findElement(By.cssSelector(\".gn_search_v2 .W_input\"));\n//        input.sendKeys(\"\");\n        for(int i = 0;i<5;i++) {\n            ((JavascriptExecutor) web).executeScript(\"document .body .scrollTop+=10000\");\n            Thread.sleep(5000);\n        }\n        Base base = new Base(web.getPageSource());\n        web.close();\n        List<Document> dbList = new ArrayList<>();\n        for(Base item:base.jsoup(\".WB_detail\").list()){\n            Document map = new Document();\n            map.put(\"userName\",item.jsoup(\".W_f14.W_fb.S_txt1\").string());\n            map.put(\"time\",item.jsoup(\"a.S_txt2[title]\").string());\n            map.put(\"content\",item.jsoup(\".WB_text\").string());\n            List<String> imageURLList = new ArrayList<>();\n            for(Base li : item.jsoup(\"li img\").list()){\n                String url = li.attribute(\"src\");\n                imageURLList.add(url);\n                Downloader.getInstance().addURL(url);\n            }\n            map.put(\"imageList\",imageURLList);\n            map.put(\"_crawler_date\", DateUtils.dateConvertToString(new Date(),\"yyyy-MM-dd HH:mm:ss\"));\n            map.put(\"userID\", crawlerMQ.getExtending().get(\"userId\").toString());\n            map.put(\"userName\", crawlerMQ.getExtending().get(\"userName\").toString());\n            dbList.add(map);\n        }\n        MongoDB.insert(dbList,\"sina_weibo\");\n        return null;\n    }\n}\n', 0xCAFEBABE0000003400C70A0009004D09004E004F0A005000510A005200530A005400550B0056005707005808005907005A0B0007005B0500000000000013880A005C005D07005E0B0056005F0A000E00600B005600610700620A0012004D0800630A000E00640A000E00650B006600670B006800690B0068006A07006B0A001A004D08006C08006D0A000E006E0A001A006F0800700800710800720800730800740800750A000E00760B006600770A007800790A0078007A08007B08007C07007D0A002C004D08007E0A007F00800800810A005200820800830B008400850A000900860800870A0088008907008A07008B0100063C696E69743E010003282956010004436F646501000F4C696E654E756D6265725461626C65010004776F726B010042284C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724D513B294C6A6176612F7574696C2F4C6973743B01000D537461636B4D61705461626C6507008C07005E07008D07008E07008A07008F07006B01000A457863657074696F6E730700900100095369676E617475726501006E284C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724D513B294C6A6176612F7574696C2F4C6973743C4C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F4A6F623B3E3B01000A536F7572636546696C65010009506167652E6A6176610C0039003A0700910C009400950700960C0097009807008F0C0099009A07009B0C009C009D07008C0C009E009F0100266F72672F6F70656E71612F73656C656E69756D2F4A6176617363726970744578656375746F72010020646F63756D656E74202E626F6479202E7363726F6C6C546F702B3D31303030300100106A6176612F6C616E672F4F626A6563740C00A000A10700A20C00A300A401001F636F6D2F64616E672F637261776C65722F636F72652F6265616E2F426173650C00A5009D0C0039009F0C00A6003A0100136A6176612F7574696C2F41727261794C69737401000A2E57425F64657461696C0C00A700A80C00A900AA07008D0C00AB00AC07008E0C00AD00AE0C00AF00B00100116F72672F62736F6E2F446F63756D656E74010008757365724E616D650100122E575F6631342E575F66622E535F747874310C00B1009D0C00B200B301000474696D6501000F612E535F747874325B7469746C655D010007636F6E74656E740100082E57425F746578740100066C6920696D670100037372630C00B400B50C00B600B70700B80C00B900BA0C00BB009F010009696D6167654C69737401000D5F637261776C65725F6461746501000E6A6176612F7574696C2F44617465010013797979792D4D4D2D64642048483A6D6D3A73730700BC0C00BD00BE0100067573657249440C00BF00C00100067573657249640700C10C009E00C20C00C3009D01000A73696E615F776569626F0700C40C00C500C60100167363726970742F73696E615F776569626F2F5061676501002F636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724A6F6201001D6F72672F6F70656E71612F73656C656E69756D2F57656244726976657201000E6A6176612F7574696C2F4C6973740100126A6176612F7574696C2F4974657261746F7201002E636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F437261776C65724D510100136A6176612F6C616E672F457863657074696F6E01003D636F6D2F64616E672F637261776C65722F636F72652F666574636865722F736572766963652F576562447269766572466163746F72792444726976657201000644726976657201000C496E6E6572436C61737365730100097068616E746F6D6A7301003F4C636F6D2F64616E672F637261776C65722F636F72652F666574636865722F736572766963652F576562447269766572466163746F7279244472697665723B010036636F6D2F64616E672F637261776C65722F636F72652F666574636865722F736572766963652F576562447269766572466163746F727901000C676574776562447269766572010060284C636F6D2F64616E672F637261776C65722F636F72652F666574636865722F736572766963652F576562447269766572466163746F7279244472697665723B294C6F72672F6F70656E71612F73656C656E69756D2F5765624472697665723B01000A6765745265717565737401003028294C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F526571756573743B01002C636F6D2F64616E672F637261776C65722F7265736F75726365732F6265616E2F636F72652F5265717565737401000667657455726C01001428294C6A6176612F6C616E672F537472696E673B010003676574010015284C6A6176612F6C616E672F537472696E673B295601000D65786563757465536372697074010039284C6A6176612F6C616E672F537472696E673B5B4C6A6176612F6C616E672F4F626A6563743B294C6A6176612F6C616E672F4F626A6563743B0100106A6176612F6C616E672F546872656164010005736C656570010004284A295601000D67657450616765536F75726365010005636C6F73650100056A736F7570010035284C6A6176612F6C616E672F537472696E673B294C636F6D2F64616E672F637261776C65722F636F72652F6265616E2F426173653B0100046C69737401001228294C6A6176612F7574696C2F4C6973743B0100086974657261746F7201001628294C6A6176612F7574696C2F4974657261746F723B0100076861734E65787401000328295A0100046E65787401001428294C6A6176612F6C616E672F4F626A6563743B010006737472696E67010003707574010038284C6A6176612F6C616E672F537472696E673B4C6A6176612F6C616E672F4F626A6563743B294C6A6176612F6C616E672F4F626A6563743B010009617474726962757465010026284C6A6176612F6C616E672F537472696E673B294C6A6176612F6C616E672F537472696E673B010003616464010015284C6A6176612F6C616E672F4F626A6563743B295A01002D636F6D2F64616E672F637261776C65722F636F72652F666574636865722F66696C652F446F776E6C6F6164657201000B676574496E7374616E636501003128294C636F6D2F64616E672F637261776C65722F636F72652F666574636865722F66696C652F446F776E6C6F616465723B01000661646455524C01002A636F6D2F64616E672F637261776C65722F7265736F75726365732F7574696C732F446174655574696C7301001364617465436F6E76657274546F537472696E67010036284C6A6176612F7574696C2F446174653B4C6A6176612F6C616E672F537472696E673B294C6A6176612F6C616E672F537472696E673B01000C676574457874656E64696E6701001128294C6A6176612F7574696C2F4D61703B01000D6A6176612F7574696C2F4D6170010026284C6A6176612F6C616E672F4F626A6563743B294C6A6176612F6C616E672F4F626A6563743B010008746F537472696E6701002A636F6D2F64616E672F637261776C65722F7265736F75726365732F6D6F6E676F64622F4D6F6E676F4442010006696E73657274010025284C6A6176612F7574696C2F4C6973743B4C6A6176612F6C616E672F537472696E673B2956002100370009000100380000000200010039003A0001003B0000001D00010001000000052AB70001B100000001003C000000060001000000140001003D003E0003003B000002470004000C00000167B20002B800034D2C2BB60004B60005B900060200033E1D08A2001F2CC00007120803BD0009B9000A03005714000BB8000D840301A7FFE2BB000E592CB9000F0100B700104E2CB900110100BB001259B700133A042D1214B60015B60016B9001701003A051905B9001801009900F31905B900190100C0000E3A06BB001A59B7001B3A071907121C1906121DB60015B6001EB6001F571907122019061221B60015B6001EB6001F571907122219061223B60015B6001EB6001F57BB001259B700133A0819061224B60015B60016B9001701003A091909B90018010099002D1909B900190100C0000E3A0A190A1225B600263A0B1908190BB90027020057B80028190BB60029A7FFCF1907122A1908B6001F571907122BBB002C59B7002D122EB8002FB6001F57190712302BB600311232B900330200B60034B6001F571907121C2BB60031121CB900330200B60034B6001F5719041907B90027020057A7FF0919041235B8003601B000000002003C00000072001C00000016000700170014001A001B001B002B001C0031001A0037001E0045001F004B002000540021007A0022008300230095002400A7002500B9002600C2002700E9002800F2002900FC002A0104002B0107002C0111002D0125002E013B002F01510030015B0031015E003201650033003F000000560006FD001607004001FA0020FE002C070041070042070043FF006E000A0700440700450700400700410700420700430700410700460700420700430000FA0033FF00560005070044070045070040070041070042000000470000000400010048004900000002004A0002004B00000002004C00930000000A0001004E005000924019, '2017-05-04 11:13:23');
