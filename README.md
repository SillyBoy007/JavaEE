# JavaEE学习笔记

## 1.JDBC基础知识

JDBC(Java Database Connective),Java数据库连接技术,是一组接口的组成,属于资源操作

**JDBC规定了四种Java数据库操作形式**:
1.JDBC-ODBC桥接技术(不用):由微软提供的数据库连接,简介操纵ODBC技术,性能最差,支持版本新
2.JDBC直接连接:数据库生产商提供指定的数据库连接驱动程序(实现了Java的操纵数据库标准的一群类)，性能最好,支持的版本不是最新的
3.JDBC网络连接:使用专门的数据库网络连接指令进行指定主机的数据库操作，使用方式最多
4.模拟指定数据库的通讯协议自己编写数据库操作

Java连接任何数据库性能都很高,出了SQLServer

java.util.sql包:
一个类:DriverManager类:
四个接口:Connection,Statement,ResultSet,PreparedStatement

**JDBC连接数据库操作步骤:**
1.加载数据库的驱动程序(向容器加载)
2.进行数据库连接（通过DriverManager类完成,Connection表示连接）
3.进行数据的CRUD(Statement,PreparedStatement,ResultSet)
4.关闭数据库操作与连接

**一.加载驱动程序**
加载驱动程序: https://blog.csdn.net/xiaojinlai123/article/details/79447727
需要配置CLASSPATH,而后需要设置驱动程序的类名称(包.类)
D:\app\wang\product\11.2.0\dbhome_1\jdbc\lib\ojdbc6.jar
类名称:oracle.jdbc.driver.OracleDriver
加载类: Class.forName("oracle.jdbc.driver.OracleDriver");

**二.连接数据库(记得服务要开启)**

**1.连接Oracle数据库**
数据库的连接地址:jdbc:oracle:连接方式:主机名称:端口名称:数据库的SID

驱动类driver: oracle.jdbc.driver.OracleDriver
连接本机的数据库url:jdbc:oracle:thin:@localhost:1521:orcl
数据库的用户名username: scott
数据库的密码password:tiger

连接数据库依靠DriverManager类完成,此类定义如下方法:
public static Connection getConnection(String url,String user,String password)

**2.连接mysql数据库**
驱动类driver: com.mysql.jdbc.Driver
连接本机的数据库url:  jdbc:mysql://localhost:3306/jdbc_mysql
数据库的用户名username: root
数据库的密码password:  123456

**四.关闭数据库**
Connection接口提供close方法:public void close()

注意:
1.JDBC操作之中,在驱动数据库连接对象时,采用的是工厂设计模式,而DriverManager就是一个工厂类,客户端调用时，会隐藏具体的实现子类。
2.每一个Connection接口对象就表示一个数据库连接

**连接不上数据库的原因**
1.监听出现问题(修改了计算机名字)
监听的主机名称不是主机名称,不要使用IP地址
解决:修改Oracle的俩个监听文件,重启Oracle监听服务
2.不能找到指定的SID
打开Oracle的网络管理工具，配置数据库



## 2.JDBC类,接口,连接池

**2.1 Statement**

1.取得Statement对象: public Statement createStatement() thorws SQLE
Statement对象的俩个操作:
数据更新,返回更新行数: public int executeUpdate(String sql) throws SQLE
数据查询,返回ResultSet:public ResultSet executeQuery(String sql) throws SQLE

在ResultSet接口里定义了如下方法:
向下移动指针并判断是否有数据行: public boolean next() throws SQLE
取出数据列的内容: getInt(),getDouble(),getString(),getDate()

总结:
开发一般不使用Satement,使用PrepareStatement

**2.2 PrepareSatetment**

**Statement的操作缺陷**
Statement的执行模式不适合处理一些敏感字符,会容易出错
**PrepareSatetment**
PrepareSatetment执行的是一个完整的具备特殊占位标记的SQL语句,可以动态地设置所需要的数据。
PrepareSatetment属于Satement的子接口,依然需要Connection接口提供的方法:
public PreparedStatement prepareStatement(String sql) throwsIOE

PreparedStatement 接口提供的方法:setInt(),setDouble()
更新操作: public int executeUpdate(String sql) throws SQLE
查询操作: public ResultSet executeQuery(String sql) throws SQLE

注意:将java.util.Date 变为java.sql.Date(Time,Timestamp)只能依靠将Date变为long,将long变为sql.date完成

**2.3 批处理与事务处理**

**批处理**:指的是一次性向数据库之中发出多条操作命令,一起执行
Statement接口定义的方法:
增加批处理语句: public void addBatch(String sql ) throws SQLE
执行批处理: public int[] executeBatch() throws SQLE
返回的数组包含了批处理的结果
**事务提交**
注意:在批处理操作的过程中,由于JDBC具备自动的事务提交,一旦中间的语句出现了错误,那么结果就是错误前的语句正常执行,错误后的语句不执行
使用JDBC提供的事务处理操作来进行手工的事务控制,所有的操作方法都在Connection定义:
事务提交: public void commit() throws SQLE
事务回滚: public void rollback() throws SQLE
设置是否自动提交: public void setAutoCommit(boolean autoCommit) throws SQLE

PreparedStatement接口定义的方法:
增加批处理语句: public void addBatch(String sql ) throws SQLE

**2.4 JDBC连接池**

**问题引出**:在实际开发中,获得连接与释放资源是一件十分消耗系统资源的俩个过程,所以我们采用连接池技术共享Connection,解决了性能问题
**原理**:首先创建连接池,程序执行时从连接池获取连接,用完后close归还连接,不需要销毁

**自定义连接池**
1.创建连接池(数据源),实现javax.sql.DataSource接口,只使用getConnection方法
2.创建容器,用于存储Connection对象,由于要频繁进行更新操作,采用LinkedList集合
3.创建5个连接,翻入容器
4.从池子里获取连接对象
5.将连接对象归还给连接池

增强close方法:装饰者设计模式

**C3P0连接池**
需要导入jar包:c3p0-0.9.1.2.jar
配置xml文件名称为:c3p0-config.xml
配置文件：默认配置
1.创建连接池对象
 DataSource dataSource = new ComboPooledDataSource();
ComboPooledDataSource可以传入xml里的配置名称,不传则采用默认配置

**DBCP连接池**
需要导入俩个jar包，配置文件名称:*.properties

**DBUtils**
DBUtils是数据库操作实用工具,封装了对JDBC的操作,简化JDBC操作，使用时需要导入一个jar包
核心功能:
QueryRunner中提供对sql语句操作的API
ResultSetHandler接口,用于定义select操作后，怎么样封装结果集
Dbutils类,工具类定义了关闭资源和数据处理

**JavaBean**
BeanListHandler: 将结果集中每条记录封装到指定的javabean中,将这些javabean封装到List集合中
BeanHandler: 返回结果集的第一条记录返回到javabean中

## 3.DAO设计模式

**3.1 程序分层思想**

**程序划分:显示层+控制层+ 业务层+ 数据层 + 数据库**
**业务层**:业务对象(Business Object),又叫服务层,是整个程序提供的操作功能,一个业务层的操作需要多个数据层的控制一起完成。
**数据层**:数据访问层(Data Access Object)，完成一个个原子性的数据库操作。

**3.2 项目规范**

**3.2.1创建新的项目,配置jdbc**

**3.2.2创建cn.wang.dbc包,创建数据库连接类DatabaseConnection**

**3.2.3开发ValueObject类(简单java类),与数据表的结构一致,数据层与业务层，控制层与业务层等的交互是由简单Java类完成的**
开发简单java类的要求:
1）考虑到以后的分布式问题,简单java类需要实现java.io.Serializable接口
2）简单java类的名称需要与表名称保持一致,studet_info->StudentInfo
3）类中的属性不允许使用基本数据类型,必须使用基本数据类型的包装类(基本数据类型数值默认0，包装类默认null)
4）类中可以定义多个构造方法,但必须保留一个无参构造
5）类中属性必须封装,提供getter,setter方法
6）类保存在cn.wang.vo包中
**注意**:有多少张表就建多少简单Java类

**3.2.4开发数据层**

数据层接口开发要求:
1):数据接口保存在cn.wang.dao包下
2):不同的数据表操作有可能使用不同的数据层开发，需要针对数据表进行命名:
如Emp表,数据层接口就定义为IEmpDAO
3):数据层开发分为俩类功能:
数据更新:以doXXX()的形式命名
数据查询:查询表的数据:以findXxx命名;统计表的数据:以getXxx命名

数据层的实现数据接口:
1）:保存在dao.impl子包下
使用数据层进行原子性的功能操作实现,必须提供Connection接口对象
开发中需要业务层调用数据层,数据库打开与关闭由业务层完成，子类构造方法一定要接收一个Connection接口对象

**数据层工厂类DAOFactory**
业务层要想进行数据层的调用,必须取得IEmpDAO的接口对象,但不同层之间想取得对象实例,需要采用工厂设计模式.
定义工厂类:
1):新建cn.wang.factory包和DAOFactory类
定义方法:public static IEmpDAO getIEmpDAOInstance(Connection conn)


**5.开发业务层**

业务层是留给外部调用的层,可能由控制层调用,也可能直接调用,业务层开发的首要任务是定义业务层的操作标准。
5.1 开发业务层标准---IEmpService
在cn.wang.service包下创建IEmpService接口

5.2 开发业务层实现类
1).业务层核心功能负责数据库的打开和关闭，业务层对象实例化之后需准备好数据库连接
2).根据DAOFactory调用getIEmpDAOInstance而后取得IEmp接口对象
3).保存在cn.wang.service.impl子包下

5.2 定义业务层工厂类---ServiceFactory
 业务层分为:
前台业务逻辑:保存在service.front包中,工厂类:ServiceFrontFactory
后台业务逻辑:保存在service.back包中,工厂类:ServiceBackFactory

在实际编写之中,子类都是不可见的，定义一个工厂类取得实例化对象

**6.业务测试**
6.1 调用测试
按照传统方式产生对象，而后调用里面的方式进行操作。
保存在test子包内
6.2 junit测试
先选择测试的类和接口,

**3.3 深入了解DAO**

1.几乎所有表都存在CRUD功能,在整个DAO接口定义过程中,不同表的区别在于VO类，主键类型,使用泛型实现基础操作：
1.1 cn.wang.dao包建立IDAO接口<K,V>,其中K表示主键类型，V表示vo类
1.2 修改DAOFactory，增加新的接口对象取得方法

## 4.XML和反射

### 4.1.XML

XML标签可以用户自定义,一般用来配置文件
空白标签必须闭合:如"<\/a>"
\<![CDATA[  这里可以放任何内容 ]]>

**DTD约束**
1.内部DTD,在XML内部嵌入，只对当前XML有效
2.DTD本地文档引入
3.公共DTD，在网络上引入DTD

**Schema约束**
Schema本身也是XML文档，后缀名为xsd,功能比DTD更加强大

**XML解析**
**1.DOM解析**
优点：
1、形成了树结构，有助于更好的理解、掌握，且代码容易编写。
2、解析过程中，树结构保存在内存中，方便修改。
缺点：
1、由于文件是一次性读取，所以对内存的耗费比较大。
2、如果XML文件比较大，容易影响解析性能且可能会造成内存溢出。
**DOM4j解析**
1、JDOM的一种智能分支，它合并了许多超出基本XML文档表示的功能。
2、它使用接口和抽象基本类方法。
3、具有性能优异、灵活性好、功能强大和极端易用的特点。
4、是一个开放源码的文件
注意:需要导入jar包.
常用API:
SAXReader对象：加载执行xml文档
Document对象:  getRootElement获取根元素
Element对象: getName获取元素名

解析步骤:
1).获取解析器
2.获得Document文档对象
3.获取根元素
4.获取子元素
5.取出子元素数据

**2.SAX解析**
优点：
1、采用事件驱动模式，对内存耗费比较小。
2、适用于只处理XML文件中的数据时。
缺点：
1、编码比较麻烦。
2、很难同时访问XML文件中的多处不同数据。
**3.PULL解析**



### 4.2.反射

测试程序MyServlet编程方式为硬编码,为了后期可扩展,通常使用反射加载字符串指定类,并通过反射创建实例。
1.认识反射
使用反射，可以在运行时对类class,构造方法Constructor,普通方法method,字段属性进行操作
动态地获取指定类.及类中的内容
2.获取class的三种方式



## 5.Http和Tomcat

**应用分层**
前端:html,css/js
服务端:
web层:jsp+servlet,struts2,SpringMVC
service层:Spring
dao层:JDBC,DBUtils,Hibernate,Mybatis
数据库:MySQL,Oracle

前端传输数据发送http请求,服务器端响应请求，基于http协议，服务器端访问数据库依靠jdbc+sql
### 5.1 http协议

1.http协议简介
http,是一种超文本传输协议。
2.http协议组成
1).http请求
2).http响应
302：页面重定向
304：缓存
404：找不到该资源
500：服务器端错误
### 5.2 web应用服务器---Tomcat

供向外部发布web资源的服务器软件
**web资源**
存在于web服务器可供外界访问的资源就是web资源
1.静态资源:html,css,js,图片,视频等
2.动态资源:jsp/servlet,php,asp
**请求和响应**

Tomcat目录结构：
bin:启动目录，conf:配置文件，lib:jar包类库，logs:日志，temp：临时目录，webapps：项目存放位置,work：jsp被翻译后的存储目录

TomCat启动:
找到bin目录下的startup.bat，cmd命令行运行或直接双击运行
Tomcat启动失败:
1.由于是解压的绿色版tomcat,需要配置JRE_HOME环境变量
2.端口被占用，更改conf目录下的server.xml中端口名称

**web应用目录结构**

mail
  |
  |-----html,js,csss文件等
  |
  |----WEB-INFO目录
  |--------- |
  |--------- |-----classes目录 （java类）
  |--------- |
  |--------- |-----lib目录 （java运行所需的jar包）
  |--------- |
  |--------- |-----web.xml文件(整个web应用的核心配置文件)
  |--------- |

注意:WEB-INFO目录是受保护的，外界不能直接访问

IDEA绑定tomcat: https://www.cnblogs.com/shindo/p/7272646.html
注意:工程发布到TomCat里只有web目录下的内容才会在webapps下。



## 6.Servlet 

### 6.1 Servlet简介
**1.什么是Servlet**
Servlet是运行在服务端的java小程序，是sun公司提供的一套接口,用来处理客户端请求,响应给浏览器的动态资源,实质是Java代码,通过java的api动态的向客户端输出内容。
**servlet规范**:
1).servlet技术
2).filter过滤器
3).listener监听器技术


2.快速入门
1).需要导入在tomcat里的lib下找到servlet-api.jar
2).创建类,实现Servlet接口
3).在service方法里写服务
4).配置web.xml
5).访问url-pattern路径

### 6.2 ServletAPI和生命周期
1.init(ServletConfig config): servlet创建的时候执行
ServletConfig : 该Servlet对象的配置的配置信息

2.service(ServletRequest req,ServletResponse res): 每次请求都会执行
ServletRequest : 代表请求,内部封装的是请求信息
ServletResponse : 代表响应,认为要封装的是响应的信息

3.destory(): servlet销毁时执行

**生命周期**
默认第一次访问servlet时创建该对象,调用init方法,每次访问时都会调用service方法,服务器关闭时销毁

**servlet的执行过程**
Tomcat引擎负责:
1.解析请求地址
2.创建servlet对象,调用init()
3.每次访问都会创建代表请求的request和代表响应的response对象,调用service()
4.在引擎关闭时负责调用destory()方法

### 6.3 servlet的配置
1.类的配置
servlet-class
2.虚拟路径配置
url-pattern:
1).完全匹配
2).目录匹配 :格式:/目录1.../*  
3).扩展名匹配: 格式: *.扩展名

**服务器启动实例化Sevlet配置**
当servlet加上<load-on-startup>时,servlet对象可以在服务器启动时就创建

**缺省Servlet**
将url-pattern配置一个/,代表该servlet是缺省的servlet,当所有的servlet都找不到地址时,就访问缺省的servlet

**欢迎页面**
不写任何资源时,默认访问index.html,可以在web.xml中配置

### 6.4 httpServlet
1.在Annocation里配置urlPattern



## 7.ServletContext

**简介**
ServletContext代表一个web应用环境(上下)对象,ServletContext对象内部封装的是该web应用的信息

**ServletContext对象生命周期**
创建:该web应用被加载（服务器启动或发布）
销毁:web应用被卸载（服务器关闭，移除该web应用）

**获得ServletContext对象**
1).通过init()里的ServletConfig实例化对象获得
2.this.getServletContext()

**ServletContext对象作用**
1). 获得初始化参数
在web.xml中配置全局的初始化参数,利用ServletContext的对象获得参数
getInitParameter()
**2). 获得web应用中任何资源的绝对路径(important)**
1.ServletContext对象:servletContext.getRealPath()
2.通过类加载器获取: ContextServlet.class.getClassLoader().getResource("../b.txt").getPath()

**ServletContext是一个域对象**
域对象:存储数据的区域
作用范围:整个web应用(所有的web资源的都可以随意地向ServletContext域中存取数据,数据可以共享)

**域对象的通用方法:**
setAttribute(String name, Object obj)
getAttribute(String name)
removeAttribute(String name)



## 8.Response&Request

### 8.1 Response

1．HttpServletResponse概述
2．response的运行流程
3．通过抓包工具抓取Http响应
4．通过response设置响应行
5．通过response设置响应头
6．通过response设置响应体
7．案例-完成文件下载
8.   生产验证码
     详细笔记:https://www.cnblogs.com/xieyupeng/p/6866688.html

### 8.2 Request

1．HttpServletRequest概述
2．request的运行流程
3．通过抓包工具抓取Http请求
4．通过request获得请求行
5．通过request获得请求头
6．通过request获得请求体
7．request的其他功能

8.   注册的基本实现
9.   用户登录失败返回信息

遇到问题:
c3p0的xml配置连接数据库的url没有设置编码格式,导致存入数据库的数据都是乱码
<property name="jdbcUrl">jdbc:mysql://localhost:3306/jdbc_mysql?useUnicode=true&amp;characterEncoding=UTF-8</property>

详细笔记:http://www.cnblogs.com/xieyupeng/p/6868246.html



## 9.Cookie和Session

**一、会话技术简介**
1．存储客户端的状态
2．会话技术
**二、Cookie技术**
1．服务器端向客户端发送一个Cookie
2．服务器端怎么接受客户端携带的Cookie
**三、Session技术**
1．获得Session对象
2．怎样向session中存取数据（session也是一个域对象）
3．Session对象的生命周期（面试题/笔试题）

详细笔记:http://www.cnblogs.com/xieyupeng/p/6870087.html



## 10.JSP

**一、JSP技术**
1．jsp脚本和注释
2．jsp运行原理-----jsp本质就是servlet（面试）
3．jsp指令（3个）
4．jsp内置/隐式对象（9个）----- 笔试
注意:要使用内置对象需要引入jsp.jar和tomcat.jar,servlet-api.jar
5．jsp标签（动作）
详细笔记:<http://www.cnblogs.com/xieyupeng/p/6872532.html>



## 11.el&jstl 

**一、EL技术**
1．EL 表达式概述
2．EL从域中取出数据(EL最重要的作用)
3．EL的内置对象11个
4．EL执行表达式

**二、jstl技术**
1．JSTL概述
2．JSTL下载与导入
3．JSTL核心库的常用标签
需要导入:jstl.jar和standard.jar

**三、javaEE的开发模式**
1．什么是模式
2．javaEE经历的模式
3．javaEE的三层架构

三层架构---服务器开发时,分为三层:
web层:与客户端交互,收集数据,封装数据,传递数据,指定响应jsp页面
service层:复杂业务逻辑处理
dao层: 与数据库进行交互

MVC与三层架构的关系
三层架构属于JavaEE的web层开发
详细笔记: <http://www.cnblogs.com/xieyupeng/p/6873967.html>



## 12.事务概述、操作、特性和隔离级别 

**一、事务概述**
1．什么是事务
2．mysql的事务

**二、JDBC事务操作**

**三、DBUtils事务操作**
例:转账操作

**四、使用ThreadLocal绑定连接资源**

**五、事务的特性和隔离级别（概念性问题---面试）**
1．事务的特性---ACID
2．并发访问问题----由隔离性引起
3．事务的隔离级别

详细笔记:<http://www.cnblogs.com/xieyupeng/p/6875770.html>



## 13.增删改查与分页操作

一、实现多条件查询
二、jquery中attr和prop的区别
三、分页的实现：
1、创建一个PageBean用于存储分页的数据
2、WEB层代码
3、Service层代码
4、DAO层代码
5、JSP页面代码
详细笔记：<http://www.cnblogs.com/xieyupeng/p/6877802.html>



## 14.ajax与json

ajax：异步校验用户名和站内查询

一、Ajax概述
二、js原生的Ajax技术（了解）
三、Json数据格式（重要）
1．Json的格式与解析
2．Json的转换插件
四、Jquery的Ajax技术（重点）
详细笔记: <http://www.cnblogs.com/xieyupeng/p/6879543.html>

java代码转换成json工具包



## 15.听器Listener和邮箱服务器

### Listener和邮箱服务器

**一、监听器Listener**
1．什么是监听器？

2．监听器有哪些？

3．监听三大域对象的创建与销毁的监听器
(1)监听ServletContext域的创建与销毁的监听器ServletContextListener
(2)监听Httpsession域的创建于销毁的监听器HttpSessionListener
jsp默认会创建一个session对象
session监听器的作用:
1).网站访问次数。
(3)监听ServletRequest域创建与销毁的监听器ServletRequestListener

4．监听三大域对象的属性变化的监听器
5．与session中的绑定的对象相关的监听器（对象感知监听器）
(1)即将要被绑定到session中的对象有几种状态
面试题：当用户很多时，怎样对服务器进行优化？
(2)绑定与解绑的监听器HttpSessionBindingListener
(3)钝化与活化的监听器HttpSessionActivationListener
**二、邮箱服务器**

1．邮箱服务器的基本概念

详细笔记:<http://www.cnblogs.com/xieyupeng/p/6880732.html>



## 16.过滤器filter实现自动登录和解决全局的编码问题

**一、过滤器Filter**
注意:listener和filter可以放在web层的包里，也可以独立开来建一个包
1．filter的简介
2．快速入门
3．Filter的API详解
(1)filter生命周期及其与生命周期相关的方法
(2)Filter的AP详解
4．Filter的配置
5.总结Filter的作用

**二、案例**
1．自动登录（核心代码）
2．解决全局的编码

详细笔记: <http://www.cnblogs.com/xieyupeng/p/6881765.html>



## 17.类加载器、注解 Anocation和动态代理

**一、类加载器**
1．什么是类加载器，作用是什么？
2．类加载器的种类
\3. 怎么获得类加载器？

**二、注解**
1．什么是注解，注解作用
2．jdk5提供的注解
3．自定义注解（了解）
(1)编写一个注解
(2)使用注解
(3)解析使用了注解的类

**三、动态代理**
1．什么是代理(中介)
2．动态代理
3．案例-全局的编码的解决

详细笔记:<http://www.cnblogs.com/xieyupeng/p/6883154.html>