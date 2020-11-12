package com.gatech.streamingwars.controller;

import com.gatech.streamingwars.model.main.Account;
import com.gatech.streamingwars.model.main.DemographicGroup;
import com.gatech.streamingwars.repository.AccountRepository;
import com.gatech.streamingwars.repository.DemographicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebController {

    @Autowired
    AccountRepository repository;

    @Autowired
    DemographicRepository demographicRepository;

    @RequestMapping("/")
    public  String index(Model model)
    {
      return "index.xhtml";
    }

    @RequestMapping("/login")
    public  String login(Model model)
    {
       return "index.xhtml";
    }

    @RequestMapping("/createdemo")
    public  String createDemo(Model model)
    {
        clearModelAttributes(model);
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
