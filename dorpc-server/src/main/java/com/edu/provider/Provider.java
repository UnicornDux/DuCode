package com.edu.provider;

import com.edu.framework.model.URL;
import com.edu.framework.protocol.HttpServer;
import com.edu.framework.register.LocalRegister;
import com.edu.framework.register.RemoteMapRegister;
import com.edu.provider.api.HelloService;
import com.edu.provider.impl.HelloServiceImpl;
import com.edu.util.FileUtils;

public class Provider {

  // 服务提供端启动一个服务器，托管我们的服务
  public static void main(String[] args) {

    // 注册本地服务 :: 实现本地服务接口与实现类的映射
    // 同一个接口如果有多个实现的时候，需要引入版本号来实现
    LocalRegister.regist(HelloService.class.getName(), "1.0", HelloServiceImpl.class);

    URL url = new URL("localhost", 8080);
    RemoteMapRegister.regist(HelloServiceImpl.class.getName(), url);

    //
    // 接收网络请求 (Tomcat, Jetty, Netty, ServerSocket)
    HttpServer http = new HttpServer();
    http.start("localhost", 8080);

  }
}
