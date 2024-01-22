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
    private String totalTime;
    private int servings;
    private String instructions;
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Ingredient> ingredients;

    @ManyToOne
    private RicettaType type;
}
