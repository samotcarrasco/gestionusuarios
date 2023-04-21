package es.mdef.gestionusuarios.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
		AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
		http.csrf()
			.ignoringRequestMatchers("/**")
			.and()
			.authorizeHttpRequests()
			.requestMatchers("/login").permitAll()
			.requestMatchers(HttpMethod.DELETE).hasAuthority("Administrator")
			.anyRequest().authenticated()
			.and()
			.addFilter(new JwtAuthenticationFilter(authenticationManager))
			.addFilter(new JwtAuthorizationFilter(authenticationManager))
			;
		return http.build();
	}
	
}