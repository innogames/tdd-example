package com.innogames.tdd_example.data.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.innogames.tdd_example.service.serializer.time.ZonelessDateTimeSerializer;
import java.time.LocalDateTime;

/**
 * Represents response data to a call to a TimeController endpoint
 * @see com.innogames.tdd_example.controller.TimeController
 */
public class TimeResponse {
  @JsonSerialize(using = ZonelessDateTimeSerializer.class)
  private LocalDateTime time;

  public TimeResponse(LocalDateTime time) {
    this.time = time;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }
}
