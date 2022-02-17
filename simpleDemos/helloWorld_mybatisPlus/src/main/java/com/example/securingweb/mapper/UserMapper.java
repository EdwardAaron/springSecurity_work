package com.example.securingweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.securingweb.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<Users> {
}
