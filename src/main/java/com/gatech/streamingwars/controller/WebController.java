package com.gatech.streamingwars.controller;

import com.gatech.streamingwars.model.main.Account;
import com.gatech.streamingwars.model.main.DemographicGroup;
import com.gatech.streamingwars.model.main.User;
import com.gatech.streamingwars.repository.AccountRepository;
import com.gatech.streamingwars.repository.DemographicRepository;
import com.gatech.streamingwars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WebController {

    @Autowired
    AccountRepository repository;

    @Autowired
    DemographicRepository demographicRepository;

    @Autowired
    UserService userService;


    @RequestMapping("/")
    public  String none(Model model)
    {
      return "login.xhtml";
    }

    @RequestMapping("/index")
    public  String index(Model model) { return "index.xhtml";}

    @RequestMapping("/login")
    public  String login(Model model,@RequestParam Boolean error) {
        if(error!=null && error)
        {
            model.addAttribute("errormessage", "Login Failed. Check the Credentials and Try Again.");
        }
        return "login.xhtml";
    }

    @RequestMapping("/registration")
    public  String registration(Model model) {
        clearModelAttributes(model);
        User user = new User();
        model.addAttribute(user);
        return "registration.xhtml";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user, Model model)
    {
        User userByUserName = userService.findUserByUserName(user.getName());
        if(userByUserName!=null)
        {
            model.addAttribute("errormessage", "User Exists");
            model.addAttribute("user",userByUserName);
            return "registration.xhtml";
        }
        else {
            User saveUser = userService.saveUser(user);
            model.addAttribute("successmessage", "User Created Successfully!");
            return "login.xhtml";
        }
    }

    @RequestMapping("/createdemo")
    public  String createDemo(Model model)
    {
        clearModelAttributes(model);
        if(!userService.isAuthenticated())
        {
            return "/";
        }
        DemographicGroup dGroup = new DemographicGroup();
        model.addAttribute("group",dGroup);
        return "createdemo.xhtml";
    }

    @PostMapping("/createdemo")
    public String greetingSubmit(@ModelAttribute DemographicGroup group, Model model) {
        DemographicGroup saved = demographicRepository.save(group);
        if(saved!=null) {
            model.addAttribute("successmessage", "Demographic Group Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", "Demographic Group Failed, Please try again");
            model.addAttribute("group",group);
            return "createdemo.xhtml";
        }

    }

    private void clearModelAttributes(Model model)
    {
        model.addAttribute("successmessage",null);
        model.addAttribute("errormessage",null);
    }
}
