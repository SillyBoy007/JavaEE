package je4_xmlreflect.XMLReflect;

import je4_xmlreflect.IMyServlet;

/**
 * 读取配置文件利用反射执行不同的方法
 */
public class XMLReflect1 implements IMyServlet {

    @Override
    public void init() {
        System.out.println("XMLReflect1初始化");
    }

    @Override
    public void service() {
        System.out.println("XMLReflect1服务");
    }

    @Override
    public void destory() {
        System.out.println("XMLReflect1销毁");
    }
}
