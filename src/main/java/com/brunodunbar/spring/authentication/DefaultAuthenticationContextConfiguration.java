package com.brunodunbar.spring.authentication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultAuthenticationContextConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationContext defaultAuthenticationContext() {
        return new AuthenticationContext(ScopePolicy.ALL);
    }

}
