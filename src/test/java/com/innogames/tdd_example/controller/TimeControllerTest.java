package com.innogames.tdd_example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class TimeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getTime_success() throws Exception {
    final ResultActions resultActions = mockMvc.perform(
        get("/api/time")
    );

    resultActions
        .andExpect(status().isOk())
        .andExpect(header().string("Content-Type", "application/json"));
  }
}