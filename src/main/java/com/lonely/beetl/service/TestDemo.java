package com.lonely.beetl.service;

import com.lonely.beetl.bean.User;
import com.lonely.beetl.util.BeetlUtil;
import com.lonely.beetl.util.ObjectUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 15072 on 2018/8/6.
 */
public class TestDemo {

    public static void main(String[] args) throws IOException {
        //test1();
        test2();
        //testToXml();
    }


    public static XStream getXStream(Object obj){
        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.processAnnotations(obj.getClass());
        return xStream;
    }


    public static void testToXml(){
        User user = new User();
        user.setName("独孤");
        user.setAge("25");
        user.setAddress("湖北");

        XStream xStream = getXStream(user);
        //xStream.alias("User",User.class);
        String xml = xStream.toXML(user);
        System.out.println(xml);

    }

    public static void test2() throws IOException {
        //加载xml 解析成bean
        XStream xStream = getXStream(new User());
        //xStream.processAnnotations(User.class);
        String root = System.getProperty("user.dir")+ File.separator+"src/main/resources/templates/User.xml";
        User user = (User) xStream.fromXML(new File(root));
        System.out.println(user);

        GroupTemplate groupTemplate = BeetlUtil.loadBeetl("src/main/resources/templates/");
        Template template = groupTemplate.getTemplate("ConvertUser.xml");

        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        template.binding(map);
        //Map<String,Object> paramsMap = ObjectUtil.buildParamsMap(user);
        //template.binding(paramsMap);
        String console =  template.render();
        System.out.println(console);
    }



    public static void test1() throws IOException {
        String root = System.getProperty("user.dir")+ File.separator+"src/main/resources/templates/";
        System.out.println(root);
        FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

        Template t = gt.getTemplate("hello.btl");
        t.binding("name","dugu");
        String str = t.render();
        System.out.println(str);
    }
}
