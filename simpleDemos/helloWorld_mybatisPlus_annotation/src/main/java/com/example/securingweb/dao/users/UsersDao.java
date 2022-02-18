package com.example.securingweb.dao.users;

import com.example.securingweb.entity.Users;

public interface UsersDao {
    Users getByName(String name);
}
