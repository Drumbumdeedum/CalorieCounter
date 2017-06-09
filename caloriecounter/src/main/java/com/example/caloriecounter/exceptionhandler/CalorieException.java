package com.example.caloriecounter.exceptionhandler;

public class CalorieException {
  String errorMessage;

  public CalorieException() {
  }

  public CalorieException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
