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
                .authorizeRequests(auth -> {
                            try {
                                auth
                                        .requestMatchers("/", "/webjars/**", "/login", "/resources/**", "/perform_login").permitAll()
                                        .requestMatchers("/order/create").hasRole("CLIENT")
                                        .requestMatchers("/order/all").hasAnyRole("CLIENT", "ADMIN")
                                        .requestMatchers("/restaurant/delete", "/restaurant/edit", "/dishes/edit").hasAnyRole("ADMIN", "RESTAURANT")
                                        .requestMatchers(HttpMethod.POST, "/restaurant").hasAnyRole("ADMIN", "RESTAURANT")
                                        .requestMatchers(HttpMethod.PUT, "/restaurant").hasAnyRole("ADMIN", "RESTAURANT")
                                        .requestMatchers(HttpMethod.POST, "/dishes").hasAnyRole("ADMIN", "RESTAURANT")
                                        .requestMatchers(HttpMethod.PUT, "/dishes").hasAnyRole("ADMIN", "RESTAURANT")
                                        .requestMatchers("/dishes/delete").hasAnyRole("ADMIN", "RESTAURANT")
                                        .requestMatchers(HttpMethod.PUT, "/order").hasAnyRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/order").hasAnyRole("ADMIN", "CLIENT")
                                        .requestMatchers(HttpMethod.POST, "/courier_review").hasAnyRole("ADMIN", "CLIENT")
                                        .requestMatchers(HttpMethod.PUT, "/courier_review").hasAnyRole("ADMIN", "CLIENT")
                                        .requestMatchers("/courier_review/delete").hasAnyRole("ADMIN", "CLIENT")
                                        .requestMatchers("/client/edit").hasAnyRole("ADMIN", "CLIENT")
                                        .requestMatchers(HttpMethod.PUT, "/client").hasAnyRole("ADMIN", "CLIENT")
                                        .requestMatchers("/client/delete").hasAnyRole("ADMIN", "CLIENT")
                                        .anyRequest().authenticated();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .userDetailsService(userDetailsService)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
