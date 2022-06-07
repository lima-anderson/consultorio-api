package com.deps.consultorioapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private JwtTokenStore tokenStore;

    private static final String[] VETOR_PUBLIC = {"/oauth/token"};
    private static final String[] VETOR_OPERADOR = {"/medicos/**", "/pacientes/**", "/consultas/**", "/oauth/logout"};
    private static final String[] VETOR_ADMIN = {"/usuarios/","/usuarios/**"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(VETOR_PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, VETOR_OPERADOR).hasAnyRole("OPERATOR", "ADMIN")
                .antMatchers(HttpMethod.POST, VETOR_OPERADOR).hasAnyRole("OPERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, VETOR_OPERADOR).hasAnyRole("OPERATOR", "ADMIN")
                .antMatchers(HttpMethod.PUT, VETOR_OPERADOR).hasAnyRole("OPERATOR", "ADMIN")
                .antMatchers(VETOR_ADMIN).hasRole("ADMIN")
                .anyRequest().authenticated();

    }
}
