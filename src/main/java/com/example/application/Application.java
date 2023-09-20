package com.example.application;

import com.example.application.model.Person;
import com.example.application.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        var configurableApplicationContext =
                SpringApplication.run(Application.class, args);
        var personRepository =
                configurableApplicationContext.getBean(PersonRepository.class);
        var firstPerson = new Person("Mike", 13);
        var secondPerson = new Person("Nina",16);
        personRepository.save(firstPerson);
        personRepository.save(secondPerson);

    }

}
