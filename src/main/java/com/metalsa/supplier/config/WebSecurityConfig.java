package com.metalsa.supplier.config;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Configuration;

import com.metalsa.supplier.filters.JWTAuthorizationFilter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
	protected void configure(HttpSecurity http) throws Exception {
                // http.csrf().disable()
		// 		.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		// 		.authorizeRequests()
		// 		.antMatchers(HttpMethod.POST, "/api/suppliers/vendorToken").permitAll()
		// 		.anyRequest().authenticated();

                // Add JWT token filter
                // http.addFilterBefore(
                //         new JWTAuthorizationFilter(),
                //         UsernamePasswordAuthenticationFilter.class
                // );


        http.addFilterBefore(new CorsWebConfig(), ChannelProcessingFilter.class)
            .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/suppliers/generateToken/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/suppliers/proveedor/getUser/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/suppliers/proveedor/validaProveedor/**").permitAll()
            .anyRequest().authenticated()
            .and().headers().frameOptions().sameOrigin()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.csrf().disable();
    }


       /* @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList("*"));
                config.setAllowedMethods(Arrays.asList("GET", "POST"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", config);
                return source;
        }

        @Bean
    	public FilterRegistrationBean<CorsFilter> corsFilter(){
    		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
    		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    		return bean;
    	}*/
	
}
