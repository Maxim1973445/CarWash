package org.example.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        authz -> authz
                                .requestMatchers("/", "/static/*").permitAll()
                                .requestMatchers("/owneraccount/**").hasRole("OWNER")
                                .requestMatchers("clientaccount/**").hasRole("CLIENT")
                                .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/authorization")
                                .permitAll()
                ).logout(
                        LogoutConfigurer::permitAll);;


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
