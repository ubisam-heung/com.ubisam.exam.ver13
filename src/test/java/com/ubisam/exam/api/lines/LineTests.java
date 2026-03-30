package com.ubisam.exam.api.lines;

import static io.u2ware.common.docs.MockMvcRestDocs.delete;
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
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam.domain.Line;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class LineTests {

  @Autowired
  private LineDocs docs;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private LineRepository lineRepository;

  @Test
  void contextLoads() throws Exception{

    // Crud - C
    mvc.perform(post("/api/lines").content(docs::newEnity, "1호선")).andDo(print())
    .andExpect(is2xx()).andDo(result(docs::context, "entity1"));

    // Crud - R
    String uri = docs.context("entity1", "$._links.self.href");
    mvc.perform(post(uri)).andExpect(is2xx());

    // Crud - U
    Map<String, Object> body = docs.context("entity1", "$");
    mvc.perform(put(uri).content(docs::updateEntity, body, "2호선")).andExpect(is2xx());

    // Crud - D
    mvc.perform(delete(uri)).andExpect(is2xx());

  }

  @Test
  void contextLoads2 () throws Exception{

    List<Line> result;
    boolean hasResult;

    // 20개의 호선 추가
    List<Line> lineLists = new ArrayList<>();
    for(int i=0; i<=20; i++){
      lineLists.add(docs.newEnity(i+"호선", i+"색"));
    }
    lineRepository.saveAll(lineLists);

    JpaSpecificationBuilder<Line> nameQuery = JpaSpecificationBuilder.of(Line.class);
    nameQuery.where().and().eq("lineName", "6호선");
    result = lineRepository.findAll(nameQuery.build());
    hasResult = result.stream().anyMatch(u -> "6호선".equals(u.getLineName()));
    assertEquals(true, hasResult);

    JpaSpecificationBuilder<Line> colorQuery = JpaSpecificationBuilder.of(Line.class);
    colorQuery.where().and().eq("lineColor", "3색");
    result = lineRepository.findAll(colorQuery.build());
    hasResult = result.stream().anyMatch(u -> "3색".equals(u.getLineColor()));
    assertEquals(true, hasResult);
  }

  @Test
  void contextLoads3 () throws Exception{
    // 20개의 호선 추가
    List<Line> lineLists = new ArrayList<>();
    for(int i=1; i<=20; i++){
      lineLists.add(docs.newEnity(i+"호선", i+"색"));
    }
    lineRepository.saveAll(lineLists);

    String uri = "/api/lines/search";
    // Search - 단일 검색
    mvc.perform(post(uri).content(docs::setSearchName, "8호선")).andExpect(is2xx());
    mvc.perform(post(uri).content(docs::setSearchColor, "4색")).andExpect(is2xx());

    // Search - 페이지네이션 10씩 2페이지
    mvc.perform(post(uri).param("size", "10")).andExpect(is2xx());

    // Search - 정렬 lineName,desc
    mvc.perform(post(uri).param("sort", "lineColor,desc")).andExpect(is2xx());
  }
  
}
