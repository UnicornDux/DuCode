package com.edu.framework.register;

import java.util.HashMap;
import java.util.Map;

// 本地接口与实现类的注册
public class LocalRegister {

  private static Map<String, Class<?>> service = new HashMap<>();

  public static void regist(String interfaceName, String version, Class<?> ImplClass) {
    service.put(interfaceName + version, ImplClass);
  }

  public static Class<?> get(String interfaceName) {
    return service.get(interfaceName);
  }
}
