package org.learning.spring.blog.ricette.interfaccie;

import org.learning.spring.blog.ricette.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
