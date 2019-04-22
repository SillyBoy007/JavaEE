package je4_xmlreflect;

/**
 * 反射调用
 */
public class TestMyServlet {
    public static void main(String[] args) {
       /* IMyServletImpl myServlet = new IMyServletImpl();
        myServlet.init();*/
        String str = "je4_xmlreflect.IMyServletImpl";
        try {
            Class myServlet = Class.forName("je4_xmlreflect.IMyServletImpl");
            IMyServlet iMyServlet = (IMyServlet) myServlet.newInstance();
            iMyServlet.init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
