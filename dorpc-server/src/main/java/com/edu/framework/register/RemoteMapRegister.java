package com.edu.framework.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.framework.model.URL;
import com.edu.util.FileUtils;

// 将服务注册到远程
public class RemoteMapRegister {

  private static Map<String, List<URL>> REGISTER = new HashMap<>();

  public static void regist(String interfaceName, URL url) {
    List<URL> list = REGISTER.get(interfaceName);
    if (list == null) {
      list = new ArrayList<>();
    }
    list.add(url);
    REGISTER.put(interfaceName, list);
    // 将数据存储到本地文件中，模仿远程存储数据，否则服务的提供者与服务的消费者没有
    // 第三方的组件没有办法实现数据的共享
    FileUtils.saveFile(REGISTER);
  }

  public static List<URL> get(String interfaceName) {
    REGISTER = FileUtils.getFile();
    return REGISTER.get(interfaceName);
  }
}
