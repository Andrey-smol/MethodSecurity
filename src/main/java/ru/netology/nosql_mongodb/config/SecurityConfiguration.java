package ru.netology.nosql_mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain configureEndpointSecurity(HttpSecurity http) throws Exception {
        return http.formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(HttpMethod.GET, "api/users")
                            .permitAll()
                            .anyRequest().authenticated();
                }).build();
    }

    @Bean
    public UserDetailsService configureUsersDetails() {
        UserDetails user1 = User.withUsername("Slava")
                .password(encoder().encode("user1"))
                .roles("READ")
                .build();
        UserDetails user2 = User.withUsername("Sweta")
                .password(encoder().encode("user2"))
                .roles("WRITE")
                .build();
        UserDetails user3 = User.withUsername("Masha")
                .password(encoder().encode("user3"))
                .roles("UPDATE")
                .build();
        UserDetails user4 = User.withUsername("Boris")
                .password(encoder().encode("user4"))
                .roles("READ", "WRITE", "UPDATE", "DELETE")
                .build();
        UserDetails user5 = User.withUsername("Ivan")
                .password(encoder().encode("user5"))
                .authorities("READ", "WRITE")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3, user4, user5);
    }

}
