package com.example.securingweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity 不用好像也可以
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    private PasswordEncoder password;

    @Autowired
    public void setPassword(PasswordEncoder password) {
        this.password = password;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //用户没有权限，跳转页面
        http.exceptionHandling().accessDeniedPage("/unauth");

        http.formLogin()//form登录设置
                //认证界面
                .loginPage("/login")
                //login.html form中的提交地址，对应的controller会自动生成
                .loginProcessingUrl("/user/login")
                //直接登录成功后的url
                .defaultSuccessUrl("/home");
        //logout 配置
        http.logout()
                //退出url
                .logoutUrl("/logout")
                //退出成功后指向的url
                .logoutSuccessUrl("/home")
                .permitAll();
        //不需要认证就可以访问的url,授权配置，注意顺序，优先采用先配置的权限
        http.authorizeRequests()
                .antMatchers("/home", "/login", "/user/login").permitAll();

        http.authorizeRequests()
                //所有访问都要认证
                .antMatchers("/**").authenticated()
                // /admin 下的目录需要授权，且具有admin或manager权限
                .antMatchers("/admin/**").hasAnyRole("admin", "manager")
                .and()
                //自动登录
                .rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)
                .userDetailsService(userDetailsService)
                .and()
//                .csrf().disable()//关闭csrf保护
        ;
    }

    //持久登录
    @Bean
    PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Autowired
    DataSource dataSource;
}
