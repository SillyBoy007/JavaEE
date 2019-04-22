package com.wang.enhance;

/**
 * 获得字节码文件的类加载器
 */
public class GetClassLoader {

    public static void main(String[] args) {
        Class clazz = GetClassLoader.class; //获得字节码对象
        ClassLoader classLoader = clazz.getClassLoader(); //获得类加载器
        //getResource的参数路径相对classes(src)
        String string = classLoader.getResource("db.properties").getPath(); //获得classes(src)下的任何资源

        System.out.println(string);
    }
}
