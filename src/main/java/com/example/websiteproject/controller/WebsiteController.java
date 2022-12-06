package com.example.websiteproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsiteController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/ordinateur")
    public String ordi() {
        return "ordinateur";
    }

    @GetMapping("/telephone")
    public String tele() {
        return "telephone";
    }

    @GetMapping("/accessoireP")
    public String ap() {
        return "accessoireP";
    }

    @GetMapping("/accessoireC")
    public String ac() {
        return "accessoireC";
    }

    @GetMapping("/jeux")
    public String jeu() {
        return "jeux";
    }

    @GetMapping("/console")
    public String conso() {
        return "console";
    }

    @GetMapping("/accueil")
    public String accueil() {
        return "accueil";
    }
}
