package com.ubisam.exam.api.lines;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.Line;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class LineDocs extends MockMvcRestDocs{

  public Line newEnity(String... entity){
    Line body = new Line();
    body.setLineName(entity.length > 0 ? entity[0] : super.randomText("lineName"));
    body.setLineColor(entity.length > 1 ? entity[1] : super.randomText("lineColor"));
    return body;
  }

  public Map<String, Object> updateEntity(Map<String, Object> body, String entity){
    body.put("lineName", entity);
    return body;
  }

  public Map<String, Object> setSearchName(String search){
    Map<String, Object> entity = new HashMap<>();
    entity.put("searchName", search);
    return entity;
  }
  
  public Map<String, Object> setSearchColor(String search){
    Map<String, Object> entity = new HashMap<>();
    entity.put("searchColor", search);
    return entity;
  }
  
}
