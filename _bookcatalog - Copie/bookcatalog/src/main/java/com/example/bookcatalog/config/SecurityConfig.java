package com.example.bookcatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/**").permitAll() // API endpoints are open to everyone
                                .requestMatchers("/actuator/health", "/actuator/info").permitAll() // Health & info endpoints are open to everyone
                                .requestMatchers("/actuator/**").hasRole("ADMIN") // Actuator endpoints require ADMIN role
                                .anyRequest().authenticated() // All other requests require authentication
                )
                .httpBasic(); // HTTP Basic Authentication

        return http.build();
    }
}
