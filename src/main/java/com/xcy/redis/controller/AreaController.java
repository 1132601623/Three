package com.xcy.redis.controller;

import com.xcy.redis.pojo.Area;
import com.xcy.redis.service.AreaService;
import com.xcy.redis.utils.JedisClient;
import com.xcy.redis.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AreaController {

  @Autowired AreaService areaService;
  @Autowired JedisClient jedisClient;

  @RequestMapping("provinceServlet")
  @ResponseBody
  public List<Area> show() {
    String allCityJson = jedisClient.get("areaList");
    List<Area> areaList = null;
    if (null == allCityJson || allCityJson.equals("")) {
      areaList = areaService.selectAllArea();

      String jsonData = JsonUtils.objectToJson(areaList);
      jedisClient.set("areaList", jsonData);
      System.out.println("我是从数据库取得值");
    } else {
      areaList = JsonUtils.jsonToList(allCityJson, Area.class);
      System.out.println("我是从redis缓存取得值");
    }
    return areaList;
  }

  @RequestMapping("/regionServlet")
  @ResponseBody
  public List<Area> showRegion(String name) {
    /** 根据前端页面传过来的name属性，拿到所属的id */
    Area area = areaService.selectIdByName(name);
    int id = area.getId();
    System.out.println(id);

    String allRegionJson = jedisClient.get("allRegion:" + id);
    List<Area> regionList = null;
    if (null == allRegionJson || allRegionJson.equals("")) {
      regionList = areaService.selectRegionById(id);
      String jsonData = JsonUtils.objectToJson(regionList);
      jedisClient.set("allRegion:" + id, jsonData);
      System.out.println("我是从数据库取得值");
    } else {
      regionList = JsonUtils.jsonToList(allRegionJson, Area.class);
      System.out.println("我是从redis缓存取得值");
    }

    return regionList;
  }

  @RequestMapping("/cityServlet")
  @ResponseBody
  public List<Area> showCity(String name) {
    /** 根据前端页面传过来的name属性，拿到所属的id */
    Area area = areaService.selectRegionIdByName(name);
    int id = area.getId();
    System.out.println(id);
    String allCityJson = jedisClient.get("allCity:" + id);
    List<Area> cityList = null;
    if (null == allCityJson || allCityJson.equals("")) {
      cityList = areaService.selectCityById(id);
      String jsonData = JsonUtils.objectToJson(cityList);
      jedisClient.set("allCity:" + id, jsonData);
      System.out.println("我是从数据库取得值");
    } else {
      cityList = JsonUtils.jsonToList(allCityJson, Area.class);
      System.out.println("我是从redis缓存取得值");
    }
    return cityList;
  }
}
