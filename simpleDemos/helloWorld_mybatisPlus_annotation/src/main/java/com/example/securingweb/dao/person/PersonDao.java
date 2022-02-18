package com.example.securingweb.dao.person;

import com.example.securingweb.entity.Person;

public interface PersonDao {
    void saveNew(Person person);

    Person get(Integer id);
}
