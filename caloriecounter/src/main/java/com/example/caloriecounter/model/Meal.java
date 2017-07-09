package com.example.caloriecounter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meal")
public class Meal {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "meal_id")
  @JsonIgnore
  private long id;
  @Column(name = "date_of_consumption")
  private Date date;
  @Column(name = "meal_type")
  private String type;
  @Column(name = "description_of_meal")
  private String description;
  @Column(name = "meal_calories")
  private int calories;

  public Meal() {
  }

  public Meal(Date date, String type, String description, int calories) {
    this.date = date;
    this.type = type;
    this.description = description;
    this.calories = calories;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public String formatedDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(this.date);
  }
}
