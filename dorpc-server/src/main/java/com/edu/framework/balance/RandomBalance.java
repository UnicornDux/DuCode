package com.edu.framework.balance;

import java.util.List;
import java.util.Random;

import com.edu.framework.model.URL;

/**
 * 提供一种随机选择的算法
 */
public class RandomBalance {

  public static URL random(List<URL> urls) {
    Random random = new Random();
    int n = random.nextInt(urls.size());
    return urls.get(n);
  }

}
