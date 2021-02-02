package com.fsantosinfo.lockygame.controller;

import javax.validation.Valid;

import com.fsantosinfo.lockygame.model.entities.Player;
import com.fsantosinfo.lockygame.model.services.PlayerCredentialService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
This Controller is used only for the sign up processes
*/

@Controller
public class PlayerSignUpController {

    private final PlayerCredentialService playerCredentialService;
    private final PasswordEncoder passwordEncoder;

    public PlayerSignUpController(PlayerCredentialService playerCredentialService, PasswordEncoder passwordEncoder) {
        this.playerCredentialService = playerCredentialService;
        this.passwordEncoder = passwordEncoder;
        // All atributes with the "final" key word must be included in the constructor
    }


    @GetMapping("/signup")
    public ModelAndView newPlayer(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-player");

        Player player = new Player();
        modelAndView.addObject("player", player);
        return modelAndView;
    }


    @PostMapping("/registering")
    public String registerPlayer(@Valid @ModelAttribute Player player, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "new-player";
        }
        String encryptedPassword = passwordEncoder.encode(player.getPassword());
        player.setPassword(encryptedPassword);
        player.setIsAdmin(false);
        playerCredentialService.save(player);

        redirectAttributes.addFlashAttribute("message", "Cadastro efetuado com Sucesso");
        return "redirect:dashboard/player/";

    }

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }
}
