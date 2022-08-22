package dev.yblee.mybbs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "dev.yblee.mybbs.controllers.UserController")
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView getLogin(ModelAndView modelAndView){
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView getRegister(ModelAndView modelAndView){
        modelAndView.setViewName("user/register");
        return modelAndView;
    }
}
