package je4_xmlreflect;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * Dom4j解析XML
 */
public class Dom4jDemo {
    public static void main(String[] args) {
        testDom4j();
    }
    public static void testDom4j(){

        try {
            //1.获取解析器
            SAXReader saxReader = new SAXReader();
            //2.获得Document文档对象
            Document doc = saxReader.read("src/je4_xmlreflect/schema.xml");
            //3.获取根元素
            Element rootElement = doc.getRootElement();
           // System.out.println(rootElement.getName());//获取根元素名称
           // System.out.println(rootElement.attributeValue("version"));//获取根元素属性
            //4.获取子元素
            List<Element> list = rootElement.elements();

            for (Element element:list){
                if ("servlet".equals(element.getName())){
                    Element el1 = element.element("servlet-name");
                    Element el2 = element.element("servlet-class");
                     System.out.println(el1.getText());
                    System.out.println(el2.getText());
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
