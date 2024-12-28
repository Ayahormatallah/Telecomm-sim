package com.example.telecomm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("home")
    public String home() {
        return "index";
    }


    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/protocolesvedettes")
    public String protocolesvedettes() {return "protocolesvedettes";
    }
}
