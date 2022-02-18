package com.example.securingweb.dao.users;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.securingweb.entity.Users;
import com.example.securingweb.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDaoImpl implements UsersDao {
    Logger logger = LogManager.getLogger();
    @Autowired
    private UserMapper userMapper;
    @Override
    public Users getByName(String name) {
        logger.info("get users by name");
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username", name);
        return userMapper.selectOne(wrapper);
    }
}
