package com.alliance.claimsvalidationapp.controller;

import com.alliance.claimsvalidationapp.entity.User;
import com.alliance.claimsvalidationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/addUser")
    public ModelAndView registerUserController(@ModelAttribute User user){

        user.setUserStatus("active");
        User savedUser = userService.registerUserService(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listOfUsers" ,userService.getAllUserService());
        modelAndView.setViewName("accountingPage");

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

    @PostMapping("/deleteSessionUser")
    public String deleteSessionUserController(Long id){
        userService.deleteUserService(id);
        return "login";
    }

//    @PostMapping("/editSessionUserPassword")
//    public String editSessionUserController(String password, Long id){
//        userService.updateUserPassword(password, id);
//        return "true";
//    }

    @PostMapping("/editSessionUserPassword")
    @ResponseBody
    public User editSessionUserPasswordController(Long id, String password){
        return userService.editSessionUserPasswordService(id, password);
    }

    @PostMapping("/validateSessionUserPassword")
    @ResponseBody
    public String validateSessionUserPassword(Long id, String password){
        return userService.validateSessionUserPasswordService(id, password);
    }

}
