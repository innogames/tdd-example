package com.innogames.tdd_example.service.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.innogames.tdd_example.data.exception.InvalidApiVersionException;
import com.innogames.tdd_example.data.response.TimeResponse;
import com.innogames.tdd_example.data.response.TimeResponseV100;
import com.innogames.tdd_example.data.response.TimeResponseV110;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeResponseBuilderTest {

  private TimeResponseBuilder timeResponseBuilder;

  @BeforeEach
  void setUp() {
    timeResponseBuilder = new TimeResponseBuilder();
  }

  @Test
  void buildGetTimeResponse_successWithApiVersion100() {
    final LocalDateTime testStartTime = LocalDateTime.now();
    final TimeResponse timeResponse = timeResponseBuilder.buildGetTimeResponse("1.0.0");
    assertNotNull(timeResponse);
    assertEquals(timeResponse.getClass(), TimeResponseV100.class);
    final TimeResponseV100 versionedTimeResponse = (TimeResponseV100) timeResponse;
    assertNotNull(versionedTimeResponse.getTime());
    assertTimeIsInExpectedRange(testStartTime, versionedTimeResponse.getTime());
  }

  @Test
  void buildGetTimeResponse_successWithApiVersion110() {
    final LocalDateTime testStartTime = LocalDateTime.now();
    final TimeResponse timeResponse = timeResponseBuilder.buildGetTimeResponse("1.1.0");
    assertNotNull(timeResponse);
    assertEquals(timeResponse.getClass(), TimeResponseV110.class);
    final TimeResponseV110 versionedTimeResponse = (TimeResponseV110) timeResponse;
    assertNotNull(versionedTimeResponse.getTime());
    assertTimeIsInExpectedRange(testStartTime, versionedTimeResponse.getTime());
  }

  @Test
  void buildGetTimeResponse_failureWithUnknownApiVersion() {
    assertThrows(InvalidApiVersionException.class,
        () -> timeResponseBuilder.buildGetTimeResponse("---invalid###")
    );
  }

  private void assertTimeIsInExpectedRange(final LocalDateTime testStartTime,
      final LocalDateTime responseTime) {
    // Given that there is a slight delay between test execution and response
    // and that the response date is built from system time, it is good enough
    // if the response date is within 2 seconds of the start of this test
    assertTrue(responseTime.isAfter(testStartTime.minusSeconds(1)));
    assertTrue(responseTime.isBefore(testStartTime.plusSeconds(1)));
  }
}