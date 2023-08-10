package com.edu.util;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.edu.framework.model.Invocation;

public class HttpClient {

  /**
   * > 发送请求的方法
   * 
   * @param hostname   : 需要请求的主机信息
   * @param port       : 需要请求的主机端口信息
   * @param invocation : 需要请求的主机信息
   */
  public String send(String hostname, Integer port, Invocation invocation) {
    try {
      URL url = new URL("http", hostname, port, "/");
      HttpURLConnection connct = (HttpURLConnection) url.openConnection();
      connct.setRequestMethod("POST");
      connct.setDoOutput(true);

      // 配置
      OutputStream outputStream = connct.getOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(outputStream);

      // 发送请求数据
      oos.writeObject(invocation);
      oos.flush();
      oos.close();

      InputStream inputStream = connct.getInputStream();
      String result = IOUtils.toString(inputStream);
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
