package com.dxc.barry.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    // @Bean
    // public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
    //     return authenticationConfiguration.getAuthenticationManager();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(httpReq -> {
                    httpReq.requestMatchers("/**", "/login", "/swagger-ui/**", "/swagger-ui.html").permitAll();
                    httpReq.requestMatchers(toH2Console());
                    httpReq.anyRequest().permitAll();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headersConfigurer -> headersConfigurer.frameOptions(FrameOptionsConfig::sameOrigin))
                .build();
    }

// 	@Bean
// 	CorsConfigurationSource corsConfigurationSource() {
// 			CorsConfiguration configuration = new CorsConfiguration();
// //				configuration.setAllowedOrigins(Arrays.asList("*"));
// 			configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD","OPTIONS","PUT"));
// 			configuration.setAllowCredentials(true);
// 			configuration.setAllowedOriginPatterns(List.of("*"));
// 			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
// 			source.registerCorsConfiguration("/**", configuration);
// 			return source;
// 	}

}
