package com.innogames.tdd_example.data.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.innogames.tdd_example.service.serializer.time.ZonelessDateTimeSerializer;
import java.time.LocalDateTime;

public class TimeResponseV100 extends TimeResponse {

  @JsonSerialize(using = ZonelessDateTimeSerializer.class)
  private LocalDateTime time;

  public TimeResponseV100(LocalDateTime time) {
    this.time = time;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }
}
