package com.GerenciadorTCC.controller.view;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "home")
public class HomeController {
    @GetMapping("/page1")
    public ResponseEntity<String> page1() {
        String body = "<h1>Olá Mundo!</h1>";
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/page2/{id}")
    @ResponseBody
    public String page2(@PathVariable("id") int id) {
        return "Olá Mundo " + id;
    }

    @GetMapping("/")
    public String home() {
        return "home.html";
    }
}
