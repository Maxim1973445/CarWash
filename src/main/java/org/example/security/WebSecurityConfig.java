package org.example.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(
                        authz -> authz
                                .requestMatchers("/", "/static/**", "/registrationowner",
                                        "/registration", "/addclient", "/addowner",
                                        "/auth").permitAll()
                                .requestMatchers("/owneraccount/**", "/order/**", "/stationList").hasRole("OWNER")
                                .requestMatchers("/clientaccount/**", "/addCar", "/changeDate",
                                        "/deleteOrder", "/sendRegister").hasRole("CLIENT")
                                .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/authorization").defaultSuccessUrl("/auth", true)
                                .permitAll()
                ).logout(
                        LogoutConfigurer::permitAll)
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
