package com.xcy.redis.service.impl;

import com.xcy.redis.mapper.AreaMapper;
import com.xcy.redis.pojo.Area;
import com.xcy.redis.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

  @Autowired AreaMapper areaMapper;

  @Override
  public List<Area> selectAllArea() {
    return areaMapper.selectAllArea();
  }

  @Override
  public Area selectIdByName(String name) {
    return areaMapper.selectIdByName(name);
  }

  @Override
  public List<Area> selectRegionById(int id) {
    return areaMapper.selectRegionById(id);
  }

  @Override
  public List<Area> selectCityById(int id) {
    return areaMapper.selectCityById(id);
  }

  @Override
  public Area selectRegionIdByName(String name) {
    return areaMapper.selectRegionIdByName(name);
  }
}
