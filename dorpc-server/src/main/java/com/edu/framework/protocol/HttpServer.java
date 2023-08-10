package com.edu.framework.protocol;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

import com.edu.framework.protocol.DispatchServelet;

/**
 * 这是协议中的一种实现，这里启动的是一个 Http 服务器, 用于接收客户端的请求
 * > 启动 Tomcat
 * > 启动 Jetty
 * > 启动 Netty
 * 
 */

public class HttpServer {

  // 加载用户配置
  //
  public void start(String hostname, Integer port) {

    Tomcat tomcat = new Tomcat();

    Server server = tomcat.getServer();
    Service service = server.findService("Tomcat");

    // 设置监听的端口
    Connector connector = new Connector();
    connector.setPort(port);

    // 设置接收服务的主机名称
    Engine engine = new StandardEngine();
    engine.setDefaultHost(hostname);

    Host host = new StandardHost();
    host.setName(hostname);

    // 添加监听路径
    String contextPath = "";
    Context context = new StandardContext();
    context.setPath(contextPath);
    context.addLifecycleListener(new Tomcat.FixContextListener());

    host.addChild(context);
    engine.addChild(host);

    service.setContainer(engine);
    service.addConnector(connector);

    tomcat.addServlet(contextPath, "dispatcher", new DispatchServelet());
    context.addServletMappingDecoded("/*", "dispatcher");

    try {
      tomcat.start();
      tomcat.getServer().await();
    } catch (LifecycleException e) {
      e.printStackTrace();
    }
  }
}
