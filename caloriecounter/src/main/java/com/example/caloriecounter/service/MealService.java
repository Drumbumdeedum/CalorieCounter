package com.example.caloriecounter.service;

import com.example.caloriecounter.datatransfer.MealStats;
import com.example.caloriecounter.model.Meal;
import com.example.caloriecounter.repository.MealRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MealService {

  MealRepository mealRepository;
  MealStats mealStats;

  @Autowired
  public MealService(MealRepository mealRepository,
      MealStats mealStats) {
    this.mealRepository = mealRepository;
    this.mealStats = mealStats;
  }

  public void showAllMeals(Model model) {
    model.addAttribute("mealRepo", mealRepository.findAll());
  }

  public void showMealStats(Model model) {
    List<Meal> mealList = (List<Meal>)mealRepository.findAll();
    long sumOfCalories = 0;
    for (int i=0; i < mealList.size(); i++) {
      sumOfCalories += mealList.get(i).getCalories();
    }
    mealStats.setNrOfMeals(mealRepository.count());
    mealStats.setTotalCalories(sumOfCalories);
    model.addAttribute("mealStats", mealStats);
  }
}
