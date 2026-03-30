package com.ubisam.exam.api.stations;

import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.get;
import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.print;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam.domain.Station;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class StationTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private StationDocs docs;

  @Autowired
  private StationRepository stationRepository;

  @Test
  void contextLoads() throws Exception{

    // Crud - C
    mvc.perform(post("/api/stations").content(docs::newEntity, "가산역")).andDo(print())
    .andExpect(is2xx()).andDo(result(docs::context, "entity1"));

    // Crud - R
    String uri = docs.context("entity1", "$._links.self.href");
    mvc.perform(get(uri)).andExpect(is2xx());

    // Crud - U
    Map<String, Object> body = docs.context("entity1", "$");
    mvc.perform(put(uri).content(docs::updateEntity, body, "남구로역")).andExpect(is2xx());

    // Crud - D
    mvc.perform(delete(uri)).andExpect(is2xx());

  }
  @Test
  void contextLoads2() throws Exception{

    List<Station> result;
    boolean hasResult;

    // 40개의 역 추가
    List<Station> stationLists = new ArrayList<>();
    for(int i=1; i<=40; i++){
      stationLists.add(docs.newEntity("가산"+i+"역", "ST0"+i));
    }
    stationRepository.saveAll(stationLists);

    JpaSpecificationBuilder<Station> nameQuery = JpaSpecificationBuilder.of(Station.class);
    nameQuery.where().and().eq("stationName", "가산5역");
    result = stationRepository.findAll(nameQuery.build());
    hasResult = result.stream().anyMatch(u -> "가산5역".equals(u.getStationName()));
    assertEquals(true, hasResult);

    JpaSpecificationBuilder<Station> codeQuery = JpaSpecificationBuilder.of(Station.class);
    codeQuery.where().and().eq("stationCode", "ST02").build();
    result = stationRepository.findAll(codeQuery.build());
    hasResult = result.stream().anyMatch(u -> "ST02".equals(u.getStationCode()));
    assertEquals(true, hasResult);
  }

  @Test
  void contextLoads3 () throws Exception{

    // 40개의 역 추가
    List<Station> stationLists = new ArrayList<>();
    for(int i=1; i<=40; i++){
      stationLists.add(docs.newEntity("가산"+i+"역", "ST0"+i));
    }
    stationRepository.saveAll(stationLists);

    // Search - 단일 검색
    mvc.perform(get("/api/stations/search/findByStationName").param("stationName", "가산7역")).andExpect(is2xx());
    mvc.perform(get("/api/stations/search/findByStationCode").param("stationCode", "ST05")).andExpect(is2xx());

    // Search - 페이지네이션 8개씩 5페이지
    mvc.perform(get("/api/stations").param("size", "8")).andExpect(is2xx());

    // Search - 정렬 stationCode,desc
    mvc.perform(get("/api/stations").param("sort", "stationCode,desc")).andExpect(is2xx());

  }
  
}
