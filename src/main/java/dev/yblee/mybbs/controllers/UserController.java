package dev.yblee.mybbs.controllers;

import dev.yblee.mybbs.services.UserService;
import dev.yblee.mybbs.vos.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller(value = "dev.yblee.mybbs.controllers.UserController")
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired //생성자의 매개변수는 spring이 알아서 객체화해야한다라는 의미를 가지고 있음
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView getLogin(ModelAndView modelAndView) {
        modelAndView.setViewName("fragments/user/login");
        return modelAndView;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView postLogin(ModelAndView modelAndView) {
        modelAndView.setViewName("fragments/user/login");
        return modelAndView;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView getRegister(ModelAndView modelAndView) {
        modelAndView.setViewName("fragments/user/register");
        return modelAndView;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView postRegister(RegisterVo registerVo,
                                     ModelAndView modelAndView) {
        registerVo.setAdmin(false);
        registerVo.setResult(null);
        this.userService.register(registerVo);
        modelAndView.addObject("registerVo", registerVo);
        modelAndView.setViewName("fragments/user/register");
        return modelAndView;
    }
}
