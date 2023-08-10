package com.edu.framework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invocation {
  private String interfaceName;
  private String methodName;
  private Class<?>[] paramType;
  private Object[] params;
}
