package com.edu.consumer;

import com.edu.framework.ProxyFactory;
import com.edu.framework.model.Invocation;
import com.edu.provider.api.HelloService;
import com.edu.util.HttpClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer {

  public static void main(String... args) {
    proxyCall();
    utilCall();
  }

  public static void proxyCall() {
    HelloService service = ProxyFactory.getProxy(HelloService.class);
    String result = service.sayHello("Alex");
    log.debug("{}", result);
  }

  public static void utilCall() {
    HttpClient client = new HttpClient();
    Invocation invovation = new Invocation(
        HelloService.class.getName(),
        "sayHello",
        new Class[] { String.class },
        new Object[] { "Alex" });
    String result = client.send("localhost", 8080, invovation);
    log.debug("{}", result);
  }
}
