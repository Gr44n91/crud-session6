package com.ejercicios6.demo.controller;

import com.ejercicios6.demo.model.Laptop;
import com.ejercicios6.demo.service.LaptopService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name ="Controlador de Laptops", description = "Realiza CRUD basico")
public class LaptopController {

    private final LaptopService service;

    public LaptopController(LaptopService service) {
        this.service = service;
    }

    //findAll
    @GetMapping("/api/laptops")
    @Operation(summary = "Devuelve una lista de laptops")
    public ResponseEntity<List<Laptop>> getLaptops(){
        //Devulenve un ResponseEntity.ok
        return service.findAll();
    }
    //findById
    @GetMapping("/api/laptops/{id}")
    @Operation(summary = "Devuelve un Laptop por id")
    public ResponseEntity<Laptop> findById(
            @Parameter(description = "Recibe un Long", example = "3")
            @PathVariable Long id){
        return service.findById(id);
    }
    //Create
    @PostMapping("/api/laptops")
    @Operation(summary = "Crea el Laptop enviado por parametro en Json")
    public ResponseEntity<Laptop> save(
            @RequestBody Laptop laptop){
        return service.save(laptop);
    }

    //Update
    @PutMapping("/api/laptops")
    @Operation(summary = "Actualiza el Laptop enviado por parametro en Json")
    public ResponseEntity<Laptop> uptdate(
            @RequestBody Laptop laptop){
        return service.update(laptop);
    }
    //DeleteById
    @Hidden
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        return service.deleteById(id);
    }
    //DeleteAll
    @Hidden
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> delete(){
        return service.deleteAll();
    }
}
