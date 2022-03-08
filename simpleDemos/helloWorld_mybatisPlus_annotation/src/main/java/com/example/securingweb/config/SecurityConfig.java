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
        http.exceptionHandling().accessDeniedPage("/unauth.html");

        http.formLogin()//form登录设置
                //认证界面
                .loginPage("/login.html")
                //login.html form中的提交地址，对应的controller会自动生成
                .loginProcessingUrl("/user/login")
                //直接登录成功后的url
                .defaultSuccessUrl("/home")
                .and()
                //认证请求的配置
                .authorizeRequests()
                // /user/login 不需要认证
                .antMatchers("/login.html","/user/login","/home")
                .permitAll();
        //授权配置，注意顺序，优先采用先配置的权限
        //logout设置
        http.logout()
                //退出url
                .logoutUrl("/logout")
                //退出成功后指向的url
                .logoutSuccessUrl("/home")
                .permitAll();
        http.authorizeRequests()
                // /admin 下的目录需要授权，且具有admin或manager权限
                .antMatchers("/admin/**").hasAnyRole("admin","manager")
                // /** 下的所有url需要授权访问，但不要求用户有指定权限
                .antMatchers("/**").authenticated()
                .and()
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
