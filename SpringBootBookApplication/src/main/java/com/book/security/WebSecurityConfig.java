package com.book.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		return new BookUserDetailsService();
	}
	
	// Encode user password
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().anyRequest().permitAll();
//		return http.build();
//	}
	
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests()
//			.anyRequest().authenticated()
//			.and()
//			.formLogin()
//				.loginPage("/login")
//				.usernameParameter("email")
//				.permitAll()
//			.and().logout().permitAll();
//		return http.build();
//	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("email")
                        .permitAll()).logout(logout -> logout.permitAll());
		return http.build();
	}
	
	
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**",
        		"/fontawesome/**", "/webfonts/**", "/style.css");
    }


}
