package com.fsantosinfo.lockygame.controller;

import com.fsantosinfo.lockygame.repository.LuckyGameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LuckyGameController {

    @Autowired
    private LuckyGameRepository repository;

    @GetMapping("/games")
    public ModelAndView games(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("games");

        modelAndView.addObject("allGames", repository.getAllLuckyGame());
        return modelAndView;
    }


}