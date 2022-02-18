package com.example.securingweb.service.authentication;

import com.example.securingweb.dao.users.UsersDao;
import com.example.securingweb.entity.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger defaultLogger = LogManager.getLogger();
    @Autowired
    private UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        defaultLogger.info("get user details");
        Users users = usersDao.getByName(username);
        if (users == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new User(users.getUsername(), users.getPassword(),
                getUserAuthority(users));
    }

    //获取用户的权限
    private List<GrantedAuthority> getUserAuthority(Users users) {
        Assert.notNull(users, "users 不能为空");
        String authority = users.getAuthority();
        authority = authority == null ? "" : authority;
        return AuthorityUtils
                .commaSeparatedStringToAuthorityList(authority);
    }
}
