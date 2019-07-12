package com.xcy.redis.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Area {
  private int id;
  private String code;
  private int right;
  private int left;
  private String name;
  private int parentId;
  private int level;
}
