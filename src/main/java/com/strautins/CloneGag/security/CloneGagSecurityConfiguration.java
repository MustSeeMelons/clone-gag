package com.strautins.CloneGag.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class CloneGagSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");

        // TODO jdbcAuth is not working
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select user_name, password, true from gag.users where user_name=?")
//                .authoritiesByUsernameQuery("select user_name, role from gag.user_roles where user_name=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/post/new").access("hasRole('USER')")
                .anyRequest().permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                .and()
                    .logout().logoutSuccessUrl("/login")
                .and()
                    .exceptionHandling().accessDeniedPage("/denied")
                .and()
                    .csrf().disable();
        // @formatter:on
    }
}
