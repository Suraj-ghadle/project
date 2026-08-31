package com.example.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager users() {

        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Public user page
                .requestMatchers("/", "/index.html").permitAll()

                // Anyone can view movies
                .requestMatchers(HttpMethod.GET, "/movies").permitAll()

                // Admin pages
                .requestMatchers("/admin.html", "/manage-movies.html").hasRole("ADMIN")

                // Admin movie operations
                .requestMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/movies/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/movies/**").hasRole("ADMIN")

                // Everything else
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .defaultSuccessUrl("/admin.html", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/index.html")
                .permitAll()
            );

        return http.build();
    }
}