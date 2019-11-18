package com.innogames.tdd_example.controller;

import com.innogames.tdd_example.data.response.TimeResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TimeController {

  private static final String API_VERSION_HEADER = "X-API-VERSION";

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/api/time",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<TimeResponse> getTime(
      @RequestHeader(name = API_VERSION_HEADER) final String apiVersion
  ) {
    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add(API_VERSION_HEADER, apiVersion);
    responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    final TimeResponse timeResponse = new TimeResponse(LocalDateTime.now());
    return new ResponseEntity<>(timeResponse, responseHeaders, HttpStatus.OK);
  }

}
