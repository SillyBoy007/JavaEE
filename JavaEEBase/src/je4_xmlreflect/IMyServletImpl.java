package je4_xmlreflect;

public class IMyServletImpl implements IMyServlet {
    @Override
    public void init() {
        System.out.println("初始化");
    }

    @Override
    public void service() {
        System.out.println("服务");
    }

    @Override
    public void destory() {
        System.out.println("销毁");
    }
}
