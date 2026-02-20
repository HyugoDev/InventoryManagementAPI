    package com.inventory.management.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    import java.util.Arrays;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    // 1. Activar CORS explícitamente al inicio
                    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                    // 2. Deshabilitar CSRF (común en APIs stateless)
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            // 3. Permitir explícitamente todas las peticiones OPTIONS (Preflight)
                            .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers("/**").permitAll()
                    )
                    .httpBasic(httpBasic -> httpBasic.disable())
                    .formLogin(form -> form.disable());

            return http.build();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedOrigins(Arrays.asList(
                    "http://localhost:4321",
                    "https://web-inventario.hyugodev.me"
            ));

            configuration.setAllowedMethods(Arrays.asList(
                    "GET","POST","PUT","PATCH","DELETE","OPTIONS"
            ));

            configuration.setAllowedHeaders(Arrays.asList(
                    "Authorization","Content-Type","X-Requested-With"
            ));

            configuration.setAllowedOriginPatterns(Arrays.asList("*"));
            configuration.setAllowCredentials(false); // Importante: * no funciona con credentials=true

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }
