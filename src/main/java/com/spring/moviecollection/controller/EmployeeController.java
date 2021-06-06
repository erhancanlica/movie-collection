package com.spring.moviecollection.controller;

import com.spring.moviecollection.service.ActorService;
import com.spring.moviecollection.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final MovieService movieService;

    private final ActorService actorService;

}
