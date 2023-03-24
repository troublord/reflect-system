package com.reflect.demo.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationConfig {
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        		.authorizeHttpRequests((requests) -> requests
        				.requestMatchers("/", "/home","/registration","/css/**","/js/**","/images/**").permitAll()
        				.requestMatchers("/admin/**").hasRole("ADMIN")
        				.anyRequest().authenticated()
        			)
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/activities/")
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true).logoutSuccessUrl("/")
                .permitAll()
                .and()
                .csrf().disable()
                .build();
    }
	
}
