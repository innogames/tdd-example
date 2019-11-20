package com.innogames.tdd_example.service.response;

import com.innogames.tdd_example.data.exception.InvalidApiVersionException;
import com.innogames.tdd_example.data.response.TimeResponse;
import com.innogames.tdd_example.data.response.TimeResponseV100;
import com.innogames.tdd_example.data.response.TimeResponseV110;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class TimeResponseBuilder {
  public TimeResponse buildGetTimeResponse(final String apiVersion) {
    switch (apiVersion) {
      case "1.0.0":
        return new TimeResponseV100(LocalDateTime.now());
      case "1.1.0":
        return new TimeResponseV110(LocalDateTime.now());
      default:
        throw new InvalidApiVersionException("Unknown API Version");
    }
  }
}
