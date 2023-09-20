package com.example.application.service;

import com.example.application.exception.PersonNotFoundException;
import com.example.application.model.Person;
import com.example.application.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;

    @Transactional
    @Override
    public List<Person> findAllPeople() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void savePerson(Person person) {
        repository.save(person);
    }

    @Transactional
    @Override
    public Person getPersonById(long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            return person.get();
        } else
            throw new PersonNotFoundException(String.format("Person with id %d not found!", id));
    }

    @Transactional
    @Override
    public void deletePerson(long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void updatePerson(Person person) {
        repository.save(person);
    }
}
