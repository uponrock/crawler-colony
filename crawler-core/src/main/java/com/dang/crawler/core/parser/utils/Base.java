package com.dang.crawler.core.parser.utils;

import com.dang.crawler.resources.utils.DataTypeUtils;
import org.htmlcleaner.TagNode;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by duang on 2017/4/26.
 */
public class Base {
    private List<String> stringList = new ArrayList<>();
    private Elements elements = new Elements();
    private TagNode[] xpathTagNodes=new TagNode[]{};
    private Status status = Status.string;

    public Base(Element e) {
        status=Status.jsoup;
        elements.add(e);
    }

    public Base(TagNode tagNode) {
        status = Status.xpath;
        xpathTagNodes = new TagNode[]{tagNode};
    }

    public static enum Status{string,jsoup,xpath};

    public Base(String page){
        status = Status.string;
        stringList.add(page);
    }
    public Base(List<String> list){
        status = Status.string;
        stringList = list;
    }
    public Base(Elements elements){
        status = Status.jsoup;
        this.elements = elements;
    }
    public Base(TagNode[] objects) {
        xpathTagNodes = objects;
        status = Status.xpath;
    }

    public Base xpath(String xpath){
        switch (this.status){
            case string:return XpathUtils.xpath(xpath,this.stringList);
            case jsoup:return XpathUtils.xpath(xpath,this.stringList);
            case xpath:return XpathUtils.xpath(xpath,this.xpathTagNodes);
            default:return new Base("");
        }
    }
    public Base jsoup(String jsoup){
        List<Base> list = new ArrayList<>();
        switch (status) {
            case string:return JsoupUtils.jsoup(jsoup, stringList);
            case jsoup:return JsoupUtils.jsoup(jsoup, elements);
            case xpath:return JsoupUtils.jsoup(jsoup, xpathTagNodes);
            default:
                return new Base("");
        }
    }
    public String attribute(String attr) {
        switch (status) {
            case string:return DataTypeUtils.listToString(stringList,";");
            case jsoup:{
                StringBuffer stringBuffer = new StringBuffer();
                for(int index=0;index<elements.size()-1;index++){
                    stringBuffer.append(elements.get(index).attr(attr));
                }
                if(elements.size()>0){
                    stringBuffer.append(elements.get(elements.size()-1).attr(attr));
                }
                return stringBuffer.toString();
            }
            case xpath:return DataTypeUtils.arrayToString(xpathTagNodes,":");
            default:
                return "";
        }
    }
    public List<Base> list() {
        List<Base> baseList = new ArrayList<>();
        switch (status) {
            case string:{
                for(String str :stringList){
                    baseList.add(new Base(str));
                }
                return baseList;
            }
            case jsoup:{
                for(Element e:elements){
                    baseList.add(new Base(e));
                }
                return baseList;
            }case xpath:{
                for(TagNode tagNode:xpathTagNodes){
                    baseList.add(new Base(tagNode));
                }
                return baseList;
            }
            default:
                return baseList;
        }
    }
    public Base regex(String regex){
        switch (status) {
            case string:return RegexUtils.regex(regex, stringList);
            case jsoup:return RegexUtils.regex(regex, elements);
            case xpath:return RegexUtils.regex(regex, xpathTagNodes);
            default:
                return new Base("");
        }
    }
    public String string() {
        switch (status) {
            case string:return DataTypeUtils.listToString(stringList,";");
            case jsoup:{
                StringBuffer stringBuffer = new StringBuffer();
                for(Element e :elements){
                    stringBuffer.append(e.text()+";");
                }
                return stringBuffer.toString();
            }
            case xpath:return DataTypeUtils.arrayToString(xpathTagNodes,":");
            default:
                return "";
        }
    }
    public int size(){
        switch (status) {
            case string:return stringList.size();
            case jsoup:return elements.size();
            case xpath:return xpathTagNodes.length;
            default:
                return 0;
        }
    }
    //////////////////////////////////////////////////////////////////////////////

    public List<String> getStringList() {
        return stringList;
    }
    public Elements getElements() {
        return elements;
    }
    public TagNode[] getXpathTagNodes() {
        return xpathTagNodes;
    }
    public Status getStatus() {
        return status;
    }

}
