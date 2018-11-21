package org.mzherdev.awsdogs.controller;

import java.util.List;

import org.mzherdev.awsdogs.model.DogBreed;
import org.mzherdev.awsdogs.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/breed")
public class BreedController {

    @Autowired
    private BreedService breedService;

    @GetMapping
    public List<String> findAll() {
        return breedService.findAll();
    }

    @GetMapping(value = "/{id}")
    public DogBreed findById(@PathVariable(name = "id") Integer id) {
        return breedService.findById(id);
    }

    @GetMapping("/find")
    public List<DogBreed> findByName(@RequestParam(name = "name") String name) {
        return breedService.findByName(name);
    }

    @PostMapping
    public DogBreed generate() {
        return breedService.generate();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable(name = "id") Integer id) {
        breedService.deleteById(id);
    }
}
