package com.ubisam.exam.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "example_station")
public class Station {

  @Id
  @GeneratedValue
  private Long id;

  private String stationName;
  private String stationLocation;
  private String stationCode;

  // 1개의 역은 여러개의 호선에 소속될 수 있음 (환승역)
  // 1개의 호선에는 여러개의 역이 포함될 수 있음 (1호선, 2호선 등)
  // @ManyToMany
  // private List<Line> lines;


  
}
