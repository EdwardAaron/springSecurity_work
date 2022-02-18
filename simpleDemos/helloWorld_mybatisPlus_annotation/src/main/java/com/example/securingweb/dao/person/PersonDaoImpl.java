package com.example.securingweb.dao.person;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.securingweb.entity.Person;
import com.example.securingweb.entity.Users;
import com.example.securingweb.mapper.PersonMapper;
import com.example.securingweb.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {
    private static final Logger defaultLogger = LogManager.getLogger();

    @Autowired
    private PersonMapper personMapper;

    @Override
    public void saveNew(Person person) {
        defaultLogger.info("get person by id");
        personMapper.insert(person);
    }

    @Override
    public Person get(Integer id) {
        defaultLogger.info("get person by id");
        return personMapper.selectById(id);
    }
}
