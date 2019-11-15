package com.innogames.tdd_example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TimeController {

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/api/time"
  )
  ResponseEntity getTime() {
    return new ResponseEntity(HttpStatus.OK);
  }

}
