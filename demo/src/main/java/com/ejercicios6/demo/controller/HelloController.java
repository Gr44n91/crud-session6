package com.ejercicios6.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/saludo")
    public String saludo(){
        return "Hola desde el controller";
    }
}
