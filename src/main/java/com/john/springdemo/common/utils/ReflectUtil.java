package com.john.springdemo.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {
    public static  <T> T copyValue(Object old, T target, String[] ignoreFileds){
        if (ignoreFileds!=null&&ignoreFileds.length>0){
            List<String> listIgnore=new ArrayList<>();
            for (String item:ignoreFileds) {
                listIgnore.add(item);
            }
            return copyValueIgnore(old,target,listIgnore);
        }
        return copyValueIgnore(old,target,null);
    }
    public static  <T> T copyValue(Object old,T target){
        return copyValueIgnore(old,target,null);
    }
    /**
     * 通过反射赋值
     * @param old 原对象
     * @param target 新对象
     * @param ignoreFields 忽略的属性
     * @param <T>
     * @return
     */
    private static  <T> T copyValueIgnore(Object old,T target,List<String> ignoreFields){
        if(old==null||target==null)
            return null;
        try {
            Class<?>clazzTarget=target.getClass();
            Class<?>clazzO=old.getClass();
            for (Field f:clazzO.getDeclaredFields()) {
                String fName=f.getName();
                if(ignoreFields!=null&& ignoreFields.contains(fName)) {
                    continue;
                }
                f.setAccessible(true);
                Field fO = clazzTarget.getDeclaredField(fName);
                if(fO!=null) {
                     fO.setAccessible(true);
                     fO.set(target, f.get(old));
                }
            }
            return target;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
