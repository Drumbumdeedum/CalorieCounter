package com.example.caloriecounter.datatransfer;

import org.springframework.stereotype.Component;

@Component
public class DeleteId {
  long id;

  public DeleteId() {
  }

  public DeleteId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
