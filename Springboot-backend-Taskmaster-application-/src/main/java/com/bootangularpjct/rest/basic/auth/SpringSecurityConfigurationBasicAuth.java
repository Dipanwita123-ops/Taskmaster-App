package com.bootangularpjct.rest.basic.auth;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth{

	@Bean
	public UserDetailsService userDetailsService() {
	    var user = User.withUsername("Dipanwita")
	                   .password("{noop}dummy") // {noop} = plain text
	                   .roles("USER")
	                   .build();
	    return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    return http
	            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 👈 use your custom config
	            .authorizeHttpRequests(auth -> auth
	            		
                       .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow preflight requests
                     .anyRequest().authenticated() //authentication needed
	                  
	         //  .anyRequest().permitAll() //allows all requests without authentication
	            )
	            .httpBasic(httpBasic -> 
                httpBasic.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            ) // basic auth
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .csrf(csrf -> csrf.disable()) // disable CSRF for APIs
	            .build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOriginPatterns(List.of("http://localhost:4200")); // use pattern-based origins
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(List.of("*"));
	    configuration.setAllowCredentials(true); // VERY IMPORTANT
	    configuration.setMaxAge(3600L);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    return http
//	        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//	        .csrf(csrf -> csrf.disable())
//	        .build();
//	}

}
