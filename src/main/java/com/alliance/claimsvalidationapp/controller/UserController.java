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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("/login")
    public String indexPage(){
        return "login";
    }

    @PostMapping("/homepage")
    public ModelAndView loginUserController(@ModelAttribute User user, HttpSession httpSession){
        List<User> listOfUser= userService.getAllUserService();
        ModelAndView modelAndView = new ModelAndView();
        User sessionUser = userService.loginUserService(user.getEmail(), user.getPassword());
        modelAndView.addObject("user", sessionUser);
        modelAndView.addObject("listOfUsers", listOfUser);

        httpSession.setAttribute("User", sessionUser);

        if(sessionUser.getUsertype().contains("Accounting")){
            modelAndView.setViewName("accountingPage");
        } else {
            modelAndView.setViewName("employeePage");
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logoutUserController(HttpServletRequest request){
        HttpSession sessionUser = request.getSession(false);
        if (sessionUser != null) {
            sessionUser.invalidate();
        }
        return "login";
    }
}
