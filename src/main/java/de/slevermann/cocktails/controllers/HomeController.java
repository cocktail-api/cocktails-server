package de.slevermann.cocktails.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/{regex:\\w+}", "/**/{regex:\\w+}"})
    public String index() {
        return "forward:/index.html";
    }
}
