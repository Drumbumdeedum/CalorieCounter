package com.example.caloriecounter.controller;

import com.example.caloriecounter.model.Meal;
import com.example.caloriecounter.datatransfer.MealStats;
import com.example.caloriecounter.repository.MealRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

  @Autowired
  MealRepository mealRepo;

  @Autowired
  MealStats mealStats;

  @RequestMapping("/")
  public String index(Model model) {

    List<Meal> mealList = (List<Meal>)mealRepo.findAll();
    long sum = 0;

    for (int i=0; i < mealList.size(); i++) {
      sum += mealList.get(i).getCalories();
    }

    mealStats.setNrOfMeals(mealRepo.count());
    mealStats.setTotalCalories(sum);

    model.addAttribute("mealStats", mealStats);
    model.addAttribute("mealRepo", mealRepo.findAll());
    return "index";
  }

  @RequestMapping("/addmeal")
  public String addMeal(@RequestParam("date") String date,
                        @RequestParam("description") String description,
                        @RequestParam("type") String type,
                        @RequestParam("calories") int calories) {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date dateFormatted = null;
    try {
      dateFormatted = formatter.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    mealRepo.save(new Meal(dateFormatted, type, description, calories));
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
