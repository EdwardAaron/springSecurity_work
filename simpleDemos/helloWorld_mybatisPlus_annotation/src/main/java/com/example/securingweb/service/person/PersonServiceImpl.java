package com.example.securingweb.service.person;

import com.example.securingweb.dao.person.PersonDao;
import com.example.securingweb.entity.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private Logger logger = LogManager.getLogger();
    @Autowired
    private PersonDao personDao;

    @Override
    public void savePerson() {

    }

    @Secured({"ROLE_admin","addPerson"})
    @Override
    public Person getPerson(Integer id) {
        logger.info("get person by id");
        Person person = personDao.get(id);
        if (person == null) {
            throw new RuntimeException("person not found");
        }
        return person;
    }
}
