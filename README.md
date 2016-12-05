民信微服务项目项目 

原有的mvc框架，controller、service、dao层相互间耦合紧密。
微服务框架主要目标是将service层位置透明化，使controller层与service层解耦。
这样页面应用和后台应用可以选择分开部署，实现性能水平扩展。
service层也可以向不同页面应用提供服务，实现服务逻辑复用。


dicMvc、dicService、dicServiceBean为demo工程
（demo仅供演示，实际项目使用其他mvc框架并嵌入micro-executor相关jar包）

dicMvc表示页面应用、dicService后台应用。
dicServiceBean为后台逻辑可以嵌入到页面应用中或后台应用中提供服务。

消费者调用服务时使用IMicroExecutor执行器的实现类的方法进行触发
NhCmdResult execNhCmd(NhCmdRequest nhCmdRequest)

IMicroExecutor执行器通过ExecutorFactory工厂创建
ExecutorFactory.getExecutor("")

IMicroExecutor执行器的实现类有
SimpleExecutor，通过反射本地调用
CXFExecutor，通过webserice远程调用
AkkaExecutor，通过akka本地调用
SpringExecutor，通过spring本地调用

micro-executor-core,微服务core工程，包含IMicroExecutor接口和SimpleExecutor实现
micro-executor-cxf,微服务cxfExecutor实现工程
micro-executor-akka,微服务akkaExecutor实现工程
micro-executor-spring,微服务springExecutor实现工程
（不同的executor实现依赖jar包需自行添加）


服务实现类需要继承接口INhCmdHandler
public void execHandler(NhCmdRequest request, NhCmdResult result)
按照指定规范命名Ws+命令名称+CmdHandler(支持spring时springbean的id为ws+命令名称+CmdHandler)
例如WsListDicCmdHandler

调用输入参数中填写命令名称、目标系统名称
			NhCmdRequest nhCmdRequest = new NhCmdRequest();
			nhCmdRequest.setCmdName("ListDic");
			nhCmdRequest.setCmdData("some json string");
			nhCmdRequest.setToSysId("dicService");


客户端配置
执行器工厂配置
可以选择注入不同执行器实例
ExecutorFactory.getExecutor("")参数与配置时key值对应，空字符串时表示default
	<bean class="com.mx.micro.executor.ExecutorFactory">
	<property name="executorMap">
			<map key-type="java.lang.String">  
                <entry>  
                    <key><value>default</value></key>  
                    <ref bean="simpleExecutor"></ref>  --> 
                    <!-- <ref bean="cxfExecutor"></ref> --> 
                    <!-- <ref bean="akkaExecutor"></ref>  -->
                </entry>  

            </map> 
	</property>
	</bean>

简单执行器配置
rootPackage为加载handler实现类的根包名。
	<bean id="simpleExecutor" class="com.mx.micro.executor.SimpleExecutor">
	<property name="rootPackage" value="com.mx.dic.handler"></property>
	</bean>

cxf执行器配置
内部需要依赖NhEsbClientFactory和cxf框架
NhEsbClientFactory可以给不同sysid配置远程访问地址
	<bean id="cxfExecutor" class="com.mx.micro.executor.cxf.CXFExecutor"></bean>
	<bean class="com.nh.esb.ws.NhEsbClientFactory" init-method="init">
	<property name="addressMap4Bean" ref="addressSysB"></property>
	<property name="addressMap4Bean" ref="addressSysC"></property>
	</bean>
	
	<bean id="addressSysB" class="com.nh.esb.core.NhEsbAddress">
	<property name="sysid" value="dicService"></property>
	<property name="ip" value="localhost"></property>
	<property name="port" value="8080"></property>
	<property name="url" value="http://localhost:8080/dicService/webservice/nhCmdService"></property>

	<bean id="addressSysC" class="com.nh.esb.core.NhEsbAddress">
	<property name="sysid" value="dicServiceC"></property>
	<property name="ip" value="localhost"></property>
	<property name="port" value="8180"></property>
	<property name="url" value="http://localhost:8180/dicService/webservice/nhCmdService"></property>
	</bean>

spring执行器配置
<bean id="springExecutor" class="com.mx.micro.executor.spring.SpringExecutor"></bean>

akka本地执行器配置
成员变量microExecutor需要配置simpleExecutor或springExecutor实例
	<bean id="akkaExecutor" class="com.mx.micro.executor.akka.AkkaExecutor">
	<property name="microExecutor" ref="simpleExecutor"></property>
	</bean>

cxf远程服务端配置
在远程服务端容器中需配置暴露cxf接口监听服务和cxf框架
NhCmdServiceImpl负责转发调用指定的WsxxxCmdHandler实现（不基于IMicroExecutor执行器，直接使用springbean方式）
远程服务内部没有微服务调用时不必配置IMicroExecutor执行器
	<bean id="nhCmdServiceImpl" class="com.nh.esb.service.ws.NhCmdServiceImpl">
	</bean>
	<!-- 通用服务端接口 -->
	<jaxws:endpoint id="nhCmdService" address="/nhCmdService"
			implementor="#nhCmdServiceImpl">
	</jaxws:endpoint>

