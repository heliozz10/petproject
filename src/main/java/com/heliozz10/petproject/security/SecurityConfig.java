package com.heliozz10.petproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomAuthenticationProvider authenticationProvider) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(authenticationProvider);
        return builder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        requests -> requests
                                .requestMatchers("/css/**", "/js/**", "/images/**", "/register", "/api/register-process", "/error").permitAll()
                                .anyRequest().permitAll()
                )
                .formLogin(
                        login -> login
                                .loginPage("/login").permitAll()
                                .defaultSuccessUrl("/")
                                .loginProcessingUrl("/api/login-process")
                )
                .csrf(
                        csrf -> csrf
                                .csrfTokenRepository(new CookieCsrfTokenRepository())
                )
                .rememberMe(
                        rememberMe -> rememberMe
                                .key("4eea0c9c-de9e-4096-87b0-c053855fbfd8")
                                .rememberMeParameter("rememberMe")
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
