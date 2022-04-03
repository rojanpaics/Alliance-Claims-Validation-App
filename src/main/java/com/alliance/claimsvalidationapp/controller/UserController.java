package com.alliance.claimsvalidationapp.controller;

import com.alliance.claimsvalidationapp.entity.User;
import com.alliance.claimsvalidationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ModelAndView registerUserController(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView();
        User savedUser = userService.registerUserService(user);
        modelAndView.addObject("user",savedUser);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }
}
