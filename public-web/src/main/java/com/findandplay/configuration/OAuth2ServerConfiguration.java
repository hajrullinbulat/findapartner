package com.findandplay.configuration;

import com.findandplay.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.Optional;

public class OAuth2ServerConfiguration {

    private static final String RESOURCE_ID = "restservice";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {
        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources
                    .tokenStore(tokenStore)
                    .resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            super.configure(http);
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/auth").authenticated()
                    .antMatchers("/user").hasRole("USER");
        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        private static PasswordEncoder encoder;

        @Autowired
        private DataSource dataSource;

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private CustomUserDetailsService userDetailsService;

        @Bean
        public JdbcTokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(this.authenticationManager)
                    .userDetailsService(userDetailsService);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .jdbc(dataSource)
                    .passwordEncoder(passwordEncoder());
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(tokenStore());
            return tokenServices;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return Optional.ofNullable(encoder).orElse(new BCryptPasswordEncoder());
        }
    }
}
