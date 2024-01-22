package org.learning.spring.blog.ricette.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
public class Ricetta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be blank")
    private String name;
    private String imageUrl;
    private String preparationTime;
    private String numberOfPortions;
    private String instructions;

    private String ingredients;

    @ManyToOne
    private RicettaType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }


    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public RicettaType getType() {
        return type;
    }

    public void setType(RicettaType type) {
        this.type = type;
    }

    public String getNumberOfPortions() {
        return numberOfPortions;
    }

    public void setNumberOfPortions(String numberOfPortions) {
        this.numberOfPortions = numberOfPortions;
    }
}
