package com.ubisam.exam.api.lines;

import java.io.Serializable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.Line;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;
import io.u2ware.common.data.rest.core.annotation.HandleAfterRead;
import io.u2ware.common.data.rest.core.annotation.HandleBeforeRead;

@Component
@RepositoryEventHandler
public class LineHandler {

  @HandleBeforeRead
  public void beforeRead(Line e, Specification<Line> spec){
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleBeforeRead] "+e);
    JpaSpecificationBuilder<Line> query = JpaSpecificationBuilder.of(Line.class);
    query
      .where()
      .and().eq("lineName", e.getSearchName())
      .and().eq("lineColor", e.getSearchColor())
      .build(spec);
  }

  @HandleAfterRead
  public void afterRead(Line e, Serializable r) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterRead] "+e);
    System.out.println("[HandleafterRead] "+r);
  }

  @HandleBeforeCreate
  public void beforeCreate(Line e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeCreate] "+e);
  }

  @HandleBeforeSave
  public void beforeSave(Line e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeSave] "+e);
  }
  
  @HandleBeforeDelete
  public void beforeDelete(Line e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeDelete] "+e);
  }

  @HandleAfterCreate
  public void afterCreate(Line e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterCreate] "+e);
  }

  @HandleAfterSave
  public void afterSave(Line e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterSave] "+e);
  }
  
  @HandleAfterDelete
  public void afterDelete(Line e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterDelete] "+e);
  }
  
}
