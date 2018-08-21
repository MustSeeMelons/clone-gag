package com.strautins.CloneGag.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class CloneGagSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LogManager.getLogger(CloneGagSecurityConfiguration.class.getName());

    @Autowired
    @Qualifier("postgres")
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/post/**").hasRole("USER")
                .and()
                    .formLogin()
                        .loginPage("/login")
                .and()
                    .exceptionHandling().accessDeniedPage("/denied")
                .and()
                    .csrf().disable();
        // @formatter:on
    }
}
