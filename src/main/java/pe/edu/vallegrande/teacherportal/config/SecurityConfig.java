package pe.edu.vallegrande.teacherportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/css/**", "/error").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .headers(headers -> {
                    headers.contentSecurityPolicy(csp -> csp.policyDirectives(
                            "default-src 'self'; style-src 'self'; img-src 'self' data:; "
                                    + "form-action 'self'; frame-ancestors 'none'; base-uri 'self'; object-src 'none'"));
                    headers.referrerPolicy(referrer -> referrer.policy(ReferrerPolicy.SAME_ORIGIN));
                    headers.addHeaderWriter((request, response) -> {
                        response.setHeader("Permissions-Policy", "camera=(), microphone=(), geolocation=()");
                        response.setHeader("Cross-Origin-Embedder-Policy", "require-corp");
                        response.setHeader("Cross-Origin-Opener-Policy", "same-origin");
                        response.setHeader("Cross-Origin-Resource-Policy", "same-origin");
                    });
                })
                .build();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("teacher@school.edu")
                        .password(passwordEncoder.encode("Secure123!"))
                        .roles("TEACHER")
                        .build());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
