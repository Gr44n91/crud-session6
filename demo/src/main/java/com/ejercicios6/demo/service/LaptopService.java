package com.ejercicios6.demo.service;

import com.ejercicios6.demo.model.Laptop;
import com.ejercicios6.demo.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {

    private final LaptopRepository repository;

    public LaptopService(LaptopRepository repository) {
        this.repository = repository;
    }

    //devuelve la lista completa de laptop
    public List<Laptop> findAll(){
        return repository.findAll();
    }
    public Laptop save(Laptop laptop){
        return repository.save(laptop);
    }
    //Devuelve un optional
    public Laptop getById(Long id){
        Optional<Laptop> laptopOpt;
        laptopOpt = repository.findById(id);
        if(laptopOpt.isPresent()) {
            return laptopOpt.get();
        }else
            return null;
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
