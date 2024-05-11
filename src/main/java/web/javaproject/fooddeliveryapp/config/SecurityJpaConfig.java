package web.javaproject.fooddeliveryapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import web.javaproject.fooddeliveryapp.service.security.JpaUserDetailsService;

@Configuration
@Profile("mysql")
public class SecurityJpaConfig {
    private final JpaUserDetailsService userDetailsService;

    public SecurityJpaConfig(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> auth
                        .requestMatchers("/order/create").hasRole("CLIENT")
                        .requestMatchers("/order/all").hasAnyRole("CLIENT", "ADMIN")
                        .requestMatchers("/restaurant/edit").hasAnyRole("ADMIN", "RESTAURANT")
                        .requestMatchers("/restaurant/delete").hasAnyRole("ADMIN", "RESTAURANT")
                        .requestMatchers(HttpMethod.PUT, "/order").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/order").hasAnyRole("ADMIN", "CLIENT")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
