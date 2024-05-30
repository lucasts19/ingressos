package com.example.ingressos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
        ModelAndView mav = new ModelAndView();
        if (userService.authenticate(username, password)) {
            mav.setViewName("home");
            mav.addObject("username", username);
        } else {
            mav.setViewName("login");
            mav.addObject("message", "Usuário ou senha inválidos");
        }
        return mav;
    }
}
