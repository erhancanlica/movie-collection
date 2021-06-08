package com.spring.moviecollection.controller;

import com.spring.moviecollection.model.Movie;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.model.dto.MovieDto;
import com.spring.moviecollection.service.ActorService;
import com.spring.moviecollection.service.CategoryService;
import com.spring.moviecollection.service.LanguageOptionService;
import com.spring.moviecollection.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LanguageOptionService languageOptionsService;

    @Autowired
    private ActorService actorService;

    @GetMapping
    public ModelAndView dashboard(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("movie/dashboard");
        modelAndView.addObject("movies", movieService.findAll());
        return modelAndView;
    }

    @GetMapping("/movie")
    public ModelAndView getCreateMovie(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("movie/createMovie");
        modelAndView.addObject("movies", new MovieDto());
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("languageOptions", languageOptionsService.findAll());
        return modelAndView;
    }

    @PostMapping("/movie")
    @ResponseBody
    public GeneralResponse createMovie(@RequestBody  MovieDto movieDto, @RequestParam(value = "chooseFile") MultipartFile multipartFile) {
        return movieService.createMovie(movieDto);
    }

    @PutMapping("/movie")
    @ResponseBody
    public GeneralResponse updateMovie(@RequestBody MovieDto movieDto){
        return movieService.updateMovie(movieDto);
    }

    @DeleteMapping("/movie")
    @ResponseBody
    public GeneralResponse deleteMovie(@RequestParam("id") Long id ){
        return movieService.deleteMovie(id);
    }

}
