package com.example.securingweb.controller.person;

import com.example.securingweb.entity.Person;
import com.example.securingweb.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Integer id) {
        return personService.getPerson(id);
    }
    @GetMapping("/save")
    public String savePerson() {
        return personService.savePerson();
    }
}
