package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private AuthenticationService authenticationService;
    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(this.authenticationService);

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // this may not be required, depends on your app configuration
        httpSecurity.authorizeRequests()
                // we need config just for console, nothing else
                .antMatchers("/h2/**").permitAll();
        // this will ignore only h2-console csrf, spring security 4+
        httpSecurity.csrf().ignoringAntMatchers("/h2/**");
        //this will allow frames with same origin which is much more safe
        httpSecurity.headers().frameOptions().sameOrigin();


            httpSecurity.authorizeRequests()
                .antMatchers("/error", "/signup", "/css/**", "/js/**","/h2/**").permitAll()
                    .anyRequest().authenticated();

            httpSecurity.formLogin()
                .loginPage("/login")
                .permitAll();

            httpSecurity.formLogin()
                .defaultSuccessUrl("/home", true);

    }

}
