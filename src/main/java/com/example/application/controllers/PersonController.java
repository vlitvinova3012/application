package com.example.application.controllers;

import com.example.application.model.Person;
import com.example.application.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
    private final PersonService service;
    @GetMapping
    public List<Person> getAllPeople() {
        return service.findAllPeople();
    }
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") long id) {
        return service.getPersonById(id);
    }
    @PostMapping
    public ResponseEntity<HttpStatus> savePerson(@RequestBody Person person) {
        service.savePerson(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping
    public Person updatePerson(@RequestBody Person person) {
        service.updatePerson(person);
        return service.getPersonById(person.getId());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
        service.deletePerson(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
