package com.manjush.todo.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ConditionalOnProperty(name = "todo.app.security.enabled", havingValue = "true")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //ToDo support Basic Authentication

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test")
                .password("pwd123")
                .roles("user")
                .and()
                .withUser("admin")
                .password("adminPassword")
                .roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console*").permitAll()
                .antMatchers("/swagger-ui*").permitAll()
                .anyRequest()
                .fullyAuthenticated();
    }

    //ToDo use BCryptPasswordEncoder
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
