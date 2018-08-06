package com.lonely.beetl.util;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.IOException;

/**
 * Created by 15072 on 2018/8/7.
 */
public class BeetlUtil {


    /**
     * 加载beetl文件
     * @param path
     */
    public static GroupTemplate loadBeetl(String path) throws IOException {
        String root = System.getProperty("user.dir")+ File.separator+ path;
        System.out.println(root);
        FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        return gt;
    }

}
