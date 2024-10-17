package com.company.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
 



@Configuration
@EnableWebSecurity
public class SecurityConfig {
      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
          http
              .authorizeHttpRequests(authorize -> authorize
                  .requestMatchers(mvc.pattern("/"), mvc.pattern("/index"), mvc.pattern("/signup"), mvc.pattern("/css/**")).permitAll()
                  .requestMatchers(mvc.pattern("/admin/**")).hasAnyRole("ADMIN", "USER")
                  .anyRequest().authenticated()
              )
              .formLogin(form -> form
                  .loginPage("/login")
                  .permitAll()
                  .defaultSuccessUrl("/admin/users", true)
              )
              .logout(logout -> logout
                  .permitAll()
              );

          return http.build();
      }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER", "ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user);
    }


@Bean
public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
}
}