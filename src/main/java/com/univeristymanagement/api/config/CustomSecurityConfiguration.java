//package com.univeristymanagement.api.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//@Configuration
//public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**").permitAll() // Allow all GET requests
//                .anyRequest().authenticated() // Require authentication for all other requests
//                .and()
//                .csrf().disable(); // Disable CSRF protection (for simplicity; not recommended for production)
////    }
//}