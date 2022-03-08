package com.example.securingweb.service.person;

import com.example.securingweb.entity.Person;

public interface PersonService {
    String savePerson();

    Person getPerson(Integer id);
}
