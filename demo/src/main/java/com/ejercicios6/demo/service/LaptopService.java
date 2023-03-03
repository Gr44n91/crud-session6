package com.ejercicios6.demo.service;

import com.ejercicios6.demo.model.Laptop;
import com.ejercicios6.demo.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {
    private final Logger log = LoggerFactory.getLogger(LaptopService.class);
    private final LaptopRepository repository;

    public LaptopService(LaptopRepository repository) {
        this.repository = repository;
    }
    //findAll
    //devuelve la lista completa de laptop
    public ResponseEntity<List<Laptop>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }
    //findById
    public ResponseEntity<Laptop> findById(Long id){
        Optional<Laptop> result = repository.findById(id);
        //Comprobamos que nos envian un id existente
            if(result.isPresent()) {
                log.warn("Se devuelve el Laptop con id: " + id);
                return ResponseEntity.ok(result.get());
            }else {
                log.warn("No se ha encontrado el Laptop con el id: "+ id);
                return ResponseEntity.notFound().build();
            }
        }
    //create
    //Guarda un laptop y lo devuelve
    public ResponseEntity<Laptop> save(Laptop laptop){
        //Comprobamos que el id esta vacio
        if(laptop.getId() == null) {
            return ResponseEntity.ok(repository.save(laptop));
        }
        log.warn("Se ha enviado un id, por favor para crear libro nuevo no envie ningun id");
        return ResponseEntity.badRequest().build();
    }
    //Update --Devulve ResponseEntity
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        //Cuando recibimos un Objeto tenemos que anotar con @RequestBody
        //Comprobamos que nos envian laptop existente
        if(laptop.getId() == null){
            log.warn("No se ha enviado ningun id");
            return ResponseEntity.badRequest().build();
        }
        if(!repository.existsById(laptop.getId())){
            log.warn("El id enviado no se corresponde con ningun libro");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.save(laptop));
    }

    //DeleteById
    public ResponseEntity<Laptop> deleteById(Long id){
        if(!repository.existsById(id)){
            log.warn("El id enviado no corresponde con ningun Laptop");
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        log.warn("Se ha borrado el libro con el id: "+ id);
        return ResponseEntity.noContent().build();
    }

    //DeleteAll
    public ResponseEntity<Laptop> deleteAll(){
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
