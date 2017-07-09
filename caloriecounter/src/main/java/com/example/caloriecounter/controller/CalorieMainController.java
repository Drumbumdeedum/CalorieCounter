package com.example.caloriecounter.controller;

import com.example.caloriecounter.model.Meal;
import com.example.caloriecounter.datatransfer.MealStats;
import com.example.caloriecounter.repository.MealRepository;
import com.example.caloriecounter.service.MealService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    mealRepo.delete(id);
    model.addAttribute("mealRepo", mealRepo.findAll());
    return"redirect:/";
  }

  @GetMapping("/{id}/edit")
  public String editElement(@PathVariable long id, Model model) {
    model.addAttribute("meal", mealRepo.findOne(id));
    mealStats.setNrOfMeals(mealRepo.count());
    model.addAttribute("mealStats", mealStats);
    return "edit";
  }

  @PostMapping("/save")
  public String save(Model model,
                      @RequestParam long id,
                      @RequestParam("date") String date,
                      @RequestParam("description") String description,
                      @RequestParam("type") String type,
                      @RequestParam("calories") int calories) {

    model.addAttribute("meal", mealRepo.findOne(id));

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date dateFormatted = null;
    try {
      dateFormatted = formatter.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    Meal currentMeal = mealRepo.findOne(id);
    currentMeal.setDate(dateFormatted);
    currentMeal.setDescription(description);
    currentMeal.setType(type);
    currentMeal.setCalories(calories);
    mealRepo.save(currentMeal);
    return("redirect:/");
  }
}
