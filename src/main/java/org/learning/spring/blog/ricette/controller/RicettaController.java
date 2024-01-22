package org.learning.spring.blog.ricette.controller;

import jakarta.validation.Valid;
import org.learning.spring.blog.ricette.interfaccie.IngredientRepository;
import org.learning.spring.blog.ricette.interfaccie.RicettaRepository;
import org.learning.spring.blog.ricette.interfaccie.RicettaTypeRepository;
import org.learning.spring.blog.ricette.model.Ricetta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ricette")
public class RicettaController {
    @Autowired
    private RicettaTypeRepository ricettaTypeRepository;

    @Autowired
    private RicettaRepository ricettaRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model) {
        List<Ricetta> ricettaList = ricettaRepository.findAll();
        model.addAttribute("ricettaList", ricettaList);
        return "ricette/home";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            Ricetta ricetta = result.get();
            model.addAttribute("ricetta", ricetta);
            return "ricette/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza " + id + " not found");
        }

    }

    @GetMapping("/newRicetta")
    public String create(Model model) {
        Ricetta ricetta = new Ricetta();
        model.addAttribute("ricetta", ricetta);
        model.addAttribute("ricettaTypeList", ricettaTypeRepository.findAll());
        return "ricette/newRicetta";
    }

    @PostMapping("/newRicetta")
    public String store(@Valid @ModelAttribute("ricetta") Ricetta formRicetta, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ricettaTypeList", ricettaTypeRepository.findAll());
            return "ricette/newRicetta";
        } else {
            Ricetta savePizza = ricettaRepository.save(formRicetta);
            return "redirect:/ricette";
        }

    }
}
