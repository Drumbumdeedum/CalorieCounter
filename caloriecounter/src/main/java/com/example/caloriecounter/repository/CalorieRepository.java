package com.example.caloriecounter.repository;

import com.example.caloriecounter.model.Meal;
import org.springframework.data.repository.CrudRepository;

public interface CalorieRepository extends CrudRepository<Meal, Long> {

}
