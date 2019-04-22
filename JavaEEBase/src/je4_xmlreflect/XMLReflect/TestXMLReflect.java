package je4_xmlreflect.XMLReflect;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestXMLReflect {
    public static void main(String[] args) {
        try {
            //一.解析XML文件
            //1.创建解析器
            SAXReader saxReader = new SAXReader();
            //2.通过解析器加载web.xml文件得到document对象
            Document document = saxReader.read("src/je4_xmlreflect/XMLReflect/web.xml");
            //3.获取根元素节点
            Element rootElement = document.getRootElement();
            //4.根据根元素获取子元素节点
            Element servletElement = rootElement.element("servlet");
            //5.根据元素名称获取servlet-class文本
            String servletClass = servletElement.element("servlet-class").getText();
            //6.通过类全名获取字节码
            Class clazz = Class.forName(servletClass);
            //7.创建实例对象
            XMLReflect1 xr1 = (XMLReflect1) clazz.newInstance();
            xr1.init();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
