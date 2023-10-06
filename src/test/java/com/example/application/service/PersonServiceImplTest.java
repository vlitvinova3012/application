package com.example.application.service;

import com.example.application.exception.PersonNotFoundException;
import com.example.application.model.Person;
import com.example.application.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @Mock
    private PersonRepository repository;
    @InjectMocks
    private PersonServiceImpl service;
    private Person firstPerson;
    private Person secondPerson;

    @BeforeEach
    void setUp() {
        firstPerson = new Person() {{
            setId(1L);
            setName("Petr");
            setAge(32);
        }};
        secondPerson = new Person() {{
            setId(2L);
            setName("Ivan");
            setAge(35);
        }};
    }

    @Test
    void findAllPeopleShouldReturnListOfPeople() {
        List<Person> people = new ArrayList<>() {{
            add(firstPerson);
            add(secondPerson);
        }};
        when(repository.findAll()).thenReturn(people);
        List<Person> peopleList = service.findAllPeople();
        assertEquals(2, peopleList.size());
        assertNotNull(peopleList);
    }

    @Test
    void savePersonShouldSavePerson() {

        when(repository.save(any(Person.class))).thenReturn(firstPerson);
        service.savePerson(firstPerson);
        verify(repository, times(1)).save(firstPerson);
    }

    @Test
    void getPersonByIdShouldReturnPersonWhichGetById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(secondPerson));
        var existingPerson = service.getPersonById(secondPerson.getId());
        assertNotNull(existingPerson);
        assertNotNull(existingPerson.getId());
    }

    @Test
    void deletePersonShouldDeletePersonByIdIfPersonExist() {
        doNothing().when(repository).deleteById(anyLong());
        service.deletePerson(2);
        verify(repository, times(1)).deleteById(secondPerson.getId());
    }

    @Test
    void getExceptionIfPersonByIdNotExist() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class, () -> service.getPersonById(anyLong()));
    }

    @Test
    void updatePersonShouldUpdatePerson() {
        when(repository.save(any(Person.class))).thenReturn(firstPerson);
        firstPerson.setAge(16);
        service.updatePerson(firstPerson);
        verify(repository, times(1)).save(firstPerson);
    }
}
