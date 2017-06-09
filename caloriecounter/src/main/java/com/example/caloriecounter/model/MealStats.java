package com.example.caloriecounter.model;

import org.springframework.stereotype.Component;

@Component
public class MealStats {
  long nrOfMeals, totalCalories;

  public MealStats() {
  }

  public MealStats(long nrOfMeals, long totalCalories) {
    this.nrOfMeals = nrOfMeals;
    this.totalCalories = totalCalories;
  }

  public long getNrOfMeals() {
    return nrOfMeals;
  }

  public void setNrOfMeals(long nrOfMeals) {
    this.nrOfMeals = nrOfMeals;
  }

  public long getTotalCalories() {
    return totalCalories;
  }

  public void setTotalCalories(long totalCalories) {
    this.totalCalories = totalCalories;
  }
}
