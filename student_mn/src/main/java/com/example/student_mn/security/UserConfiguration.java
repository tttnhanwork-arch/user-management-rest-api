package com.example.student_mn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class UserConfiguration {
    @Bean
    public JdbcUserDetailsManager userDetailsManager (DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET,"/students").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/students/**").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.POST,"/students").hasAnyRole( "ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.PUT,"/students/**").hasAnyRole("ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.DELETE,"/students/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/parents").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/parents/**").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.POST,"/parents").hasAnyRole("ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.PUT,"/parents/**").hasAnyRole("ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.DELETE,"/parents/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/employees").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/employees/**").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.POST,"/employees").hasAnyRole("ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.PUT,"/employees/**").hasAnyRole("ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.DELETE,"/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/class").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/api/class/**").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.POST,"/api/class").hasAnyRole("ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.PUT,"/api/class/**").hasAnyRole("ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.DELETE,"/api/class/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/school").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/api/school/**").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/api/scoresheet").hasAnyRole("STUDENT", "PARENT","ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/api/scoresheet/**").hasAnyRole("STUDENT", "PARENT","ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.POST,"/api/scoresheet").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PUT,"/api/scoresheet/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE,"/api/scoresheet/**").hasAnyRole("ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/api/reportcard").hasAnyRole("STUDENT", "PARENT","ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/api/reportcard/**").hasAnyRole("STUDENT", "PARENT","ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.POST,"/api/reportcard").hasAnyRole("ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.PUT,"/api/reportcard/**").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE,"/api/reportcard/**").hasAnyRole("ADMIN","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/api/subject").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.GET,"/api/subject/**").hasAnyRole("STUDENT", "PARENT","ADMIN","NURSE","TEACHER")
                        .requestMatchers(HttpMethod.POST,"/api/subject").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/subject/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/subject/**").hasRole("ADMIN")
        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }
}
