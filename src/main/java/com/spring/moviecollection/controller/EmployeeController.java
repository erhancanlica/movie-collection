package com.spring.moviecollection.controller;

import com.spring.moviecollection.model.dto.*;
import com.spring.moviecollection.service.ActorService;
import com.spring.moviecollection.service.CategoryService;
import com.spring.moviecollection.service.LanguageOptionService;
import com.spring.moviecollection.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static java.util.Objects.nonNull;


@Controller
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private LanguageOptionService languageOptionsService;

    @GetMapping("/")
    public ModelAndView dashboard(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("movie/dashboard");
        modelAndView.addObject("filterInput", new FilterInput());
        modelAndView.addObject("movies", movieService.findAll());
        return modelAndView;
    }

    @GetMapping("/movie")
    public ModelAndView getCreateMovie(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView("movie/createMovie");
        if (nonNull(id))
            modelAndView.addObject("movies", movieService.findById(id));
        else
            modelAndView.addObject("movies", new MovieDto());

        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("languageOptions", languageOptionsService.findAll());

        return modelAndView;
    }

    @PostMapping("/movie/sort")
    public ModelAndView sortMovie(@ModelAttribute FilterInput filterInput, ModelAndView modelAndView) {
        modelAndView.setViewName("movie/dashboard");
        if (filterInput.getValue().equals("default"))
            modelAndView.addObject("movies", movieService.findAll());
        else
            modelAndView.addObject("movies", movieService.sortByMovieOrYear(filterInput.getValue()));
        return modelAndView;
    }

    @PostMapping("/movie/search")
    public ModelAndView searchMovie(@ModelAttribute FilterInput filterInput, ModelAndView modelAndView) {
        modelAndView.setViewName("movie/dashboard");
        if (filterInput.getValue().equals(""))
            modelAndView.addObject("movies", movieService.findAll());
        else
            modelAndView.addObject("movies", movieService.findBySearch(filterInput.getValue()));
        return modelAndView;
    }

    @PostMapping("/movie")
    @ResponseBody
    public GeneralResponse createMovie(@RequestBody CreateMovieDto createMovieDto) {
        return movieService.createMovie(createMovieDto);
    }

    @PutMapping("/movie")
    @ResponseBody
    public GeneralResponse updateMovie(@RequestBody CreateMovieDto createMovieDto){
            return movieService.updateMovie(createMovieDto);
    }

    @DeleteMapping("/movie")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GeneralResponse deleteMovie(@RequestParam Long id){
        return movieService.deleteMovie(id);
    }

    @PostMapping("/actor")
    @ResponseBody
    public GeneralResponse createActor(@RequestBody ActorDto actorDto){
        return actorService.createActor(actorDto);
    }

    @PutMapping("/actor")
    @ResponseBody
    public GeneralResponse updateActor(@RequestBody ActorDto actorDto){
        return actorService.updateActor(actorDto);
    }

    @DeleteMapping("/actor")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GeneralResponse deleteActor(@RequestParam Long id){
        return actorService.deleteActor(id);
    }




}
