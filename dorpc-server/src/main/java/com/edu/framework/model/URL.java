package com.edu.framework.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class URL implements Serializable {
  private String hostname;
  private Integer port;
}
