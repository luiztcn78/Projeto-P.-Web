package br.upe.parkgusmap.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests( authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/relacionamentos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/relacionamentos").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.PUT, "/api/relacionamentos").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET, "/api/relacionamentos/comentarios/usuario").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/api/relacionamentos/avaliacoes/usuario").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/relacionamentos").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.POST, "/api/evento").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/api/evento").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/evento").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/api/evento").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/locais").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/api/locais").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/locais").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/api/locais/administrador").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/api/locais").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios").hasRole("USUARIO")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "http://localhost"
        ));
        configuration.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE"
        ));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
