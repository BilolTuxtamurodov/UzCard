package com.company.UzCard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Authentication bazada bor yoligini tekshiradi

        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}adminjon").roles("admin")
                .and()
                .withUser("profile").password("{noop}profilejon").roles("profile");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorization dostupi bor yoqligini tekshiradi
        http.authorizeRequests()
                .antMatchers("/card/**").permitAll()
                .antMatchers("/client/**").hasAnyRole("admin")
                .antMatchers("/transaction/**").hasAnyRole("profile")
                .anyRequest().authenticated()
                .and().httpBasic();
        http.csrf().disable().cors().disable();
//                .and().formLogin();
    }
}
