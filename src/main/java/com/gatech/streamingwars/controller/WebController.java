package com.gatech.streamingwars.controller;

import com.gatech.streamingwars.model.main.Account;
import com.gatech.streamingwars.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebController {

    @Autowired
    MainRepository repository;

    @RequestMapping("/")
    public  String index(Model model)
    {

        return "index.xhtml";
    }

    @RequestMapping("/login")
    public  String login(Model model)
    {
        Account account = new Account();
        account.setName("Aravind");
        repository.save(account);

        return "index.xhtml";
    }
}
