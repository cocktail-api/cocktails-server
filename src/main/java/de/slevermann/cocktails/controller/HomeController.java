package de.slevermann.cocktails.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /*
     * This endpoint maps the general root paths to always return our index.html,
     * which is the entry point for our angular application.
     */
    @GetMapping(value = {"/{regex:\\w+}", "/**/{regex:\\w+}", "/"})
    public String index() {
        return "forward:/index.html";
    }
}
