package com.gusto.gustoapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorizeHttpRequest) ->
                        authorizeHttpRequest
                                .requestMatchers("/api/v1/recipes/**").permitAll()
                                .requestMatchers("/api/v1/drafts/**").authenticated()
                                .requestMatchers("/api/v1/unit_measurements/**").authenticated()
                                .requestMatchers("/api/v1/files/drafts/**").authenticated()
                                .requestMatchers("/api/v1/files/recipes/**").permitAll()
                                .requestMatchers("/api/v1/ping/**").authenticated()
                )
                .cors((cors) -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
                    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                    return corsConfiguration;
                }))
                .oauth2ResourceServer((oauth2) ->
                        oauth2.jwt(jwt-> jwt.jwkSetUri("https://dev-fej5oaqsv6la8gk5.us.auth0.com/.well-known/jwks.json"))
                );

        return http.build();
    }

}
