package com.innogames.tdd_example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class TimeControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void getTime_success() throws Exception {
    final ResultActions resultActions = mockMvc.perform(
        MockMvcRequestBuilders
            .get("/api/time")
    );

    resultActions.andExpect(MockMvcResultMatchers.status().isOk());
  }
}