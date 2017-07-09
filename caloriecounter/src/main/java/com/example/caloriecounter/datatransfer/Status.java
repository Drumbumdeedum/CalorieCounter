package com.example.caloriecounter.datatransfer;

import org.springframework.stereotype.Component;

@Component
public class Status {
  String status = "ok";

  public Status() {
  }

  public Status(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
