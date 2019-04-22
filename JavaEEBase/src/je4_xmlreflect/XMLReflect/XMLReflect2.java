package je4_xmlreflect.XMLReflect;

import je4_xmlreflect.IMyServlet;

public class XMLReflect2 implements IMyServlet{
    @Override
    public void init() {
        System.out.println("XMLReflect2初始化");
    }

    @Override
    public void service() {
        System.out.println("XMLReflect2服务");
    }

    @Override
    public void destory() {
        System.out.println("XMLReflect2销毁");
    }
}
