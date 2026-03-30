package com.ubisam.exam.api.stations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ubisam.exam.domain.Station;
import java.util.List;


public interface StationRepository extends JpaRepository<Station, Long>, JpaSpecificationExecutor<Station>{

  List<Station> findByStationName(String stationName);
  List<Station> findByStationCode(String stationCode);
  
}
