package com.edu.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

import com.edu.framework.balance.RandomBalance;
import com.edu.framework.model.Invocation;
import com.edu.framework.model.URL;
import com.edu.framework.register.RemoteMapRegister;
import com.edu.util.HttpClient;

import java.lang.reflect.Method;

public class ProxyFactory {

  public static <T> T getProxy(final Class<?> interfaceLClass) {
    Object proxyInstance = Proxy.newProxyInstance(
        ProxyFactory.class.getClassLoader(),
        new Class[] { interfaceLClass },
        new InvocationHandler() {
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            HttpClient client = new HttpClient();
            Invocation invovation = new Invocation(
                interfaceLClass.getName(),
                method.getName(),
                method.getParameterTypes(),
                args);

            // 远程获取服务提供者列表
            List<URL> providers = RemoteMapRegister.get(interfaceLClass.getName());

            // 负载均衡器获取一个服务提供者
            URL url = RandomBalance.random(providers);
            String result = client.send(url.getHostname(), url.getPort(), invovation);
            return result;
          }
        });
    return (T) proxyInstance;
  }
}
