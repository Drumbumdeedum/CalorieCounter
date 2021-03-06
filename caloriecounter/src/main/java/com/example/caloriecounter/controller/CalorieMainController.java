package com.example.caloriecounter.controller;

import com.example.caloriecounter.datatransfer.MealStats;
import com.example.caloriecounter.repository.MealRepository;
import com.example.caloriecounter.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalorieMainController {

  MealRepository mealRepo;
  MealStats mealStats;
  MealService mealService;

  @Autowired
  public CalorieMainController(MealRepository mealRepo,
      MealStats mealStats, MealService mealService) {
    this.mealRepo = mealRepo;
    this.mealStats = mealStats;
    this.mealService = mealService;
  }

  @RequestMapping("/")
  public String index(Model model) {
    mealService.showMealStats(model);
    mealService.showAllMeals(model);
    return "index";
  }

  @RequestMapping("/addmeal")
  public String addMeal(@RequestParam("date") String date,
                        @RequestParam("description") String description,
                        @RequestParam("type") String type,
                        @RequestParam("calories") int calories) {
    mealService.addMeal(date, description, type, calories);
    return "redirect:/";
  }

  @GetMapping("/{id}/delete")
  public String delete(@PathVariable long id, Model model) {
    mealService.deleteMeal(model, id);
    return"redirect:/";
  }

  @GetMapping("/{id}/edit")
  public String editElement(@PathVariable long id, Model model) {
    mealService.showOneMealByID(model, id);
    mealService.showMealStats(model);
    return "edit";
  }

  @PostMapping("/save")
  public String save(Model model,
                      @RequestParam long id,
                      @RequestParam("date") String date,
                      @RequestParam("description") String description,
                      @RequestParam("type") String type,
                      @RequestParam("calories") int calories) {
    mealService.saveUpdatedMeal(model, id, date, description, type, calories);
    return("redirect:/");
  }
}
