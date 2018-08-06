package com.lonely.beetl.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 15072 on 2018/8/7.
 */
public class ObjectUtil {

    public static Map<String,Object> buildParamsMap(Object object){
        if(object==null){
            return null;
        }
        Map<String,Object> paramsMap = new HashMap<>();

        Class<?> clazz = object.getClass();
        Field[] fs = clazz.getDeclaredFields();
        for(int i=0;i<fs.length;i++){
            Field field = fs[i];
            field.setAccessible(true);
            Object val;
            try {
                val = field.get(object);
                // 得到此属性的值
                paramsMap.put(field.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return paramsMap;
    }
}
