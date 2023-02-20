package com.ejercicios6.demo.controller;

import com.ejercicios6.demo.model.Laptop;
import com.ejercicios6.demo.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopController {

    private final LaptopService service;

    public LaptopController(LaptopService service) {
        this.service = service;
    }


    @GetMapping("/api/laptops")
    public List<Laptop> getLaptops(){
        return service.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> buscarPorId(@PathVariable Long id) {
        if (service.getById(id)== null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(service.getById(id));
        }
    }

    @PostMapping("/api/laptops")
    public Laptop save(@RequestBody Laptop laptop){
        return service.save(laptop);
    }

    @DeleteMapping("/api/laptops/remove/{id}")
    public String remove(@PathVariable Long id){
        service.deleteById(id);
        return "redirect:/api/laptops";
    }
}
