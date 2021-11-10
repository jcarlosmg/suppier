package com.metalsa.supplier.config;

import com.metalsa.supplier.filters.JWTAuthorizationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.context.annotation.Configuration;
import com.metalsa.supplier.filters.SpxAuthorizationTokenFilter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
	protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .addFilterBefore(new CorsWebConfig(), ChannelProcessingFilter.class)
//                .addFilterAfter(new SpxAuthorizationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/vendorToken").permitAll()
//                .anyRequest().authenticated().and().headers().frameOptions().disable()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable()
                .addFilterBefore(new CorsWebConfig(), ChannelProcessingFilter.class)
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/suppliers/generateToken/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/suppliers/proveedor/getUser/**").permitAll()
                //.antMatchers(HttpMethod.GET, "/api/token/validarTokenPortal").permitAll()
                .anyRequest().authenticated()
                .and().headers().frameOptions().disable()
                .and().headers().frameOptions().sameOrigin()
                //.and().cors().configurationSource(corsConfigurationSource())                
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
	
}
