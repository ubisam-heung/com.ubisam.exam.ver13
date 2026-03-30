package com.ubisam.exam.api.stations;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.Station;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class StationDocs extends MockMvcRestDocs{
  
  public Station newEntity(String... entity){
    Station body = new Station();
    body.setStationName(entity.length > 0 ? entity[0] : super.randomText("stationName"));
    body.setStationCode(entity.length > 1 ? entity[1] : super.randomText("stationCode"));
    body.setStationLocation(super.randomText("stationLocation"));
    return body;
  }

  public Map<String, Object> updateEntity(Map<String, Object> body, String entity){
    body.put("stationName", entity);
    return body;
  }
}
