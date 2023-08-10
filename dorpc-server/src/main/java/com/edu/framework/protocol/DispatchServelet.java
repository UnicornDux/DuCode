package com.edu.framework.protocol;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.edu.framework.handler.HttpServerHandler;

public class DispatchServelet extends HttpServlet {

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    // 这里的服务对象可能是多样化的
    //  > 对应健康检查有健康检查的处理器
    //  > 对应回声检测有回声检测的处理器
    //  > 对应的
    new HttpServerHandler().handle(req, res);
  }
}

