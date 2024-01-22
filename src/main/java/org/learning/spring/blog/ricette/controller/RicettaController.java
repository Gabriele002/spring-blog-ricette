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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String index(@RequestParam(name = "keyword", required = false) String searchKeyword, Model model) {
        List<Ricetta> ricettaList;
        if (searchKeyword != null) {
            ricettaList = ricettaRepository.findByNameContaining(searchKeyword);
        } else {
            ricettaList = ricettaRepository.findAll();
        }
        model.addAttribute("ricettaList", ricettaList);
        model.addAttribute("preloadSearch", searchKeyword);
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

    @GetMapping("/create")
    public String create(Model model) {
        Ricetta ricetta = new Ricetta();
        model.addAttribute("ricetta", ricetta);
        model.addAttribute("ricettaTypeList", ricettaTypeRepository.findAll());
        return "ricette/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ricetta") Ricetta formRicetta, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ricettaTypeList", ricettaTypeRepository.findAll());
            return "ricette/form";
        } else {
            Ricetta savePizza = ricettaRepository.save(formRicetta);
            return "redirect:/ricette";
        }

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("ricetta", result.get());
            model.addAttribute("ricettaTypeList", ricettaTypeRepository.findAll());
            return "ricette/form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String updatericetta (@PathVariable Integer id, @Valid @ModelAttribute("ricetta") Ricetta formRicetta,  BindingResult bindingResult, Model model ) {
        Optional<Ricetta> ricetta = ricettaRepository.findById(formRicetta.getId());
        if (ricetta.isPresent()) {
            Ricetta ricettaedit = ricetta.get();
            if (bindingResult.hasErrors()) {
                model.addAttribute("ricettaTypeList", ricettaTypeRepository.findAll());
                return "ricetta/form";
            }
            formRicetta.setImageUrl(ricettaedit.getImageUrl());
            Ricetta savedricetta = ricettaRepository.save(formRicetta);
            return "redirect:/ricette";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()){
            ricettaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage","Pizza" + result.get().getName() + "deleted!");
            return "redirect:/ricette";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }
}
