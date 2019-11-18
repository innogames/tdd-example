package com.innogames.tdd_example.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class TimeControllerTest {

  private static final String API_VERSION_HEADER = "X-API-VERSION";

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getTime_successV100() throws Exception {
    getTime_success("1.0.0", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  @Test
  void getTime_successV110() throws Exception {
    getTime_success("1.1.0", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
  }

  void getTime_success(final String apiVersion, final DateTimeFormatter expectedFormatter)
      throws Exception {
    final LocalDateTime testStartTime = LocalDateTime.now();

    final ResultActions resultActions = mockMvc.perform(
        get("/api/time")
            .header(API_VERSION_HEADER, apiVersion)
    );

    resultActions
        .andExpect(status().isOk())
        .andExpect(header().string("Content-Type", "application/json"))
        .andExpect(header().string(API_VERSION_HEADER, apiVersion))
        .andExpect(jsonPath("$.time").exists())
        .andExpect(jsonPath("$.*", hasSize(1)));

    final MvcResult mvcResult = resultActions.andReturn();
    final String plainResponseContent = mvcResult.getResponse().getContentAsString();
    final String responseDateString = JsonPath.read(plainResponseContent, "$.time");
    assertNotNull(responseDateString);
    final LocalDateTime responseDateTime = LocalDateTime
        .parse(responseDateString, expectedFormatter);
    // Given that there is a slight delay between test execution and response
    // and that the response date is built from system time, it is good enough
    // if the response date is within 2 seconds of the start of this test
    assertTrue(responseDateTime.isAfter(testStartTime.minusSeconds(1)));
    assertTrue(responseDateTime.isBefore(testStartTime.plusSeconds(2)));
  }
}