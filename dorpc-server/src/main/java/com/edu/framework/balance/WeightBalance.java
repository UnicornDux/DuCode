package com.edu.framework.balance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.framework.model.URL;

/**
 * 实现一个简单的平滑加权轮询算法
 */
public class WeightBalance {

  // 用于存储对应的服务与权重之间的对应关系
  private static final Map<String, Weight> weightMap = new HashMap<>();

  // 用于存储所有服务器的权重之和
  private static final int totalWeight;

  // 
  static  {
    ServerIps.WEIGHT_LIST.forEach((key, value) -> {
      weightMap.put(key, new Weight(key, value, 0));
    });

    // 权重之和
    totalWeight = ServerIps.WEIGHT_LIST.values().stream().reduce(0, Integer::sum);
  }


  public static String getServer() {

    // 每次获取的实现，需要先在上一次的基础之上加上自己对应的权重
    for (Weight weight : weightMap.values()) {
      weight.setCurrentWeight(weight.getCurrentWeight() + weight.getWeight());
    }

    // 计算当权权重最大的服务器，
    Weight maxCurrentWeight = null;
    for (Weight weight : weightMap.values()) {
      if (maxCurrentWeight == null || weight.getCurrentWeight() > maxCurrentWeight.getCurrentWeight()) {
        maxCurrentWeight = weight;
      }
    }

    // 将 maxCurrentWeight 减去权重之和
    maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight() - totalWeight);

    // 返回结果
    return maxCurrentWeight.getIp();
  }


  public URL load(List<URL> list) {
    return null;
  }
}


// 权重配置对象
@Data
@AllArgsConstructor
@NoArgsConstructor
class Weight {

  private String ip;
  private Integer weight;
  private Integer currentWeight;

}

// 加载到的所有服务对应的初始化配置的权重
class ServerIps {
  public static final Map<String, Integer> WEIGHT_LIST = new HashMap<>();

  static {
    WEIGHT_LIST.put("A", 5);
    WEIGHT_LIST.put("B", 1);
    WEIGHT_LIST.put("C", 1);
  }
}
