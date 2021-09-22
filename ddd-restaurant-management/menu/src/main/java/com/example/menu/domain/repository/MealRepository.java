package com.example.menu.domain.repository;

import com.example.menu.domain.models.Meal;
import com.example.menu.domain.models.MealId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, MealId> {
}
