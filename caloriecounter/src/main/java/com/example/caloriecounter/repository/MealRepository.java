package com.example.caloriecounter.repository;

import com.example.caloriecounter.model.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {

}
