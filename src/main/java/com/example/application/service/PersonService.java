package com.example.application.service;

import com.example.application.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAllPeople();

    void savePerson(Person person);

    Person getPersonById(long id);

    void deletePerson(long id);
    void updatePerson(Person perso);
}
