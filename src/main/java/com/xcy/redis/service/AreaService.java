package com.xcy.redis.service;

import com.xcy.redis.pojo.Area;

import java.util.List;

public interface AreaService {
  List<Area> selectAllArea();

  Area selectIdByName(String name);

  List<Area> selectRegionById(int id);

  List<Area> selectCityById(int id);

  Area selectRegionIdByName(String name);
}
