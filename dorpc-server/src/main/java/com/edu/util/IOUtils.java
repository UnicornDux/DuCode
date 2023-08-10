package com.edu.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {

  public static String toString(InputStream inputStream) {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    try {
      StringBuilder builder = new StringBuilder();
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        builder.append(line);
      }
      return builder.toString();
    } catch (Exception e) {
      try {
        inputStream.close();
        bufferedReader.close();
      } catch (Exception ex) {
        ex.printStackTrace();
        inputStream = null;
        bufferedReader = null;
      }
      return null;
    }
  }
}
