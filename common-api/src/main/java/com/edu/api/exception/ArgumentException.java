package com.edu.api.exception;

public class ArgumentException extends RuntimeException {
  public String message;

  public ArgumentException(String message) {
    super(message);
  }

  public ArgumentException(String message, Throwable e) {
    super(message, e);
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
