package com.edu.framework.handler;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.edu.framework.model.Invocation;
import com.edu.framework.register.LocalRegister;

public class HttpServerHandler {

  // 处理请求
  public void handle(ServletRequest req, ServletResponse res) {

    try {
      // 从请求的服务对象中获取对应的数据对象，
      // 这里我们使用默认的 java 内置的序列化方式，这里有一个扩展点
      // 实际框架设计的时候需要支持多种的序列化方式 Hession, protobuf, Json ....
      Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
      String interfaceName = invocation.getInterfaceName();
      // 得到接口的实现类
      Class<?> impl = LocalRegister.get(interfaceName);

      // 获取方法
      Method method = impl.getMethod(invocation.getMethodName(), invocation.getParamType());

      // 执行方法完成调用
      Object result = method.invoke(Class.forName(impl.getName()), invocation.getParams());

      // 输出结果
      res.getOutputStream().write(result.toString().getBytes());
      res.flushBuffer();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
