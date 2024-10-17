package com.example.SerialNumberScanner.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection (if needed)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/serialnumbers/search")
                        .hasAnyRole("ADMIN", "ADVANCED_USER", "BASIC_USER")
                        .requestMatchers("/api/serialnumbers/**").hasAnyRole("ADMIN", "ADVANCED_USER")
                        .requestMatchers("/api/serialnumbers/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());  // Proper usage in Spring Security 6.1+

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
