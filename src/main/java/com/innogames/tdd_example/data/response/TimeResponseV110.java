package com.innogames.tdd_example.data.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.innogames.tdd_example.service.serializer.time.ZonelessDateTimeSerializer;
import com.innogames.tdd_example.service.serializer.time.ZuluDateTimeSerializer;
import java.time.LocalDateTime;

public class TimeResponseV110 extends TimeResponse {

  @JsonSerialize(using = ZuluDateTimeSerializer.class)
  private LocalDateTime time;

  public TimeResponseV110(LocalDateTime time) {
    this.time = time;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }
}
