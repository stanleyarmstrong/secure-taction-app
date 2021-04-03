package com.secure.taction.SeniorProject;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    /*
        @Override
        public void configure(HttpSecurity http) throws Exception {
            super.configure(http);
            http.csrf().disable();
            http.cors().disable();
            http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.httpBasic().disable();
            http.formLogin().disable();
        }
    }
    */
}
