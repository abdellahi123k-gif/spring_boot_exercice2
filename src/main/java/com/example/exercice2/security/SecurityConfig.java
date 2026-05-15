package com.example.exercice2.security;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/h2-console/**")
                        .permitAll()

                        .requestMatchers("/api/users/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST,
                                "/api/articles/**")
                        .hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/api/articles/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.PUT,
                                "/api/articles/**")
                        .hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/articles/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/comments/**")
                        .authenticated()

                        .anyRequest()
                        .authenticated()
                )

                // 🔥 هذا أهم سطر لحالتك
                .httpBasic(Customizer.withDefaults())

                // 🔥 نوقف HTML login
                .formLogin(form -> form.disable());

        return http.build();
    }
}