package com.innogames.tdd_example.data.exception;

public class InvalidApiVersionException extends RuntimeException {

  public InvalidApiVersionException(String message) {
    super(message);
  }
}
