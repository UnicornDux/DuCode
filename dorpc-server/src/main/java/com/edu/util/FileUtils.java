package com.edu.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.framework.model.URL;

public class FileUtils {

  public static void saveFile(Map<String, List<URL>> map) {
    try {
      FileOutputStream fos = new FileOutputStream("/temp.txt");
      ObjectOutputStream objos = new ObjectOutputStream(fos);
      objos.writeObject(map);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Map<String, List<URL>> getFile() {
    try {
      FileInputStream fin = new FileInputStream("/temp.txt");
      ObjectInputStream ois = new ObjectInputStream(fin);
      return (Map<String, List<URL>>) ois.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new HashMap<String, List<URL>>();
  }
}
