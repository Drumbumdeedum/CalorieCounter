package com.example.caloriecounter.controller;

import com.example.caloriecounter.datatransfer.DeleteId;
import com.example.caloriecounter.model.Meal;
import com.example.caloriecounter.datatransfer.MealStats;
import com.example.caloriecounter.datatransfer.Status;
import com.example.caloriecounter.repository.MealRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalorieRestController {

  @Autowired
  MealRepository mealRepo;

  @Autowired
  MealStats mealStats;

  @GetMapping("/getmeals")
  public String getMeals() {
    ObjectMapper objectMapper = new ObjectMapper();
    List<Meal> mealList = (List<Meal>)mealRepo.findAll();

    String jsonFormat = null;
    try {
      jsonFormat = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mealList);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return jsonFormat;
  }

  @GetMapping("/getstats")
  public String getStats() {

    ObjectMapper objectMapper = new ObjectMapper();
    List<Meal> mealList = (List<Meal>)mealRepo.findAll();
    long sum = 0;

    for (int i=0; i < mealList.size(); i++) {
      sum += mealList.get(i).getCalories();
    }

    mealStats.setNrOfMeals(mealRepo.count());
    mealStats.setTotalCalories(sum);

    String jsonFormat = null;
    try {
      jsonFormat = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mealStats);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return jsonFormat;
  }

  @PostMapping("/meal")
  public Status postStatus(@RequestBody Meal meal) {
    Meal mealReceived = new Meal();
    mealReceived.setDate(meal.getDate());
    mealReceived.setDescription(meal.getDescription());
    mealReceived.setType(meal.getType());
    mealReceived.setCalories(meal.getCalories());
    mealRepo.save(mealReceived);
    return new Status();
  }

  @PutMapping("/meal")
  public Status updateStatus(@RequestBody Meal meal) {
    Meal mealUpdated = mealRepo.findOne(meal.getId());
    mealUpdated.setDate(meal.getDate());
    mealUpdated.setDescription(meal.getDescription());
    mealUpdated.setType(meal.getType());
    mealUpdated.setCalories(meal.getCalories());
    mealRepo.save(mealUpdated);
    return new Status();
  }

  @DeleteMapping("/meal")
  public Status deleteStatus(@RequestBody DeleteId id) {
    mealRepo.delete(mealRepo.findOne(id.getId()));
    return new Status();
  }
}
