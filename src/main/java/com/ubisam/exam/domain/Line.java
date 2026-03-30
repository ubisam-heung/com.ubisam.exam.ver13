package com.ubisam.exam.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "example_line")
public class Line {

  @Id
  @GeneratedValue
  private Long id;

  private String lineName;
  private String lineColor;

  // 1개의 호선에는 여러개의 역이 포함될 수 있음 (1호선, 2호선 등)
  // 1개의 역은 여러개의 호선을 가질 수 있음 (환승역)
  // @ManyToMany(mappedBy = "lines")
  // private List<Station> stations;

  @Transient
  private String searchName;

  @Transient
  private String searchColor;

  
}
