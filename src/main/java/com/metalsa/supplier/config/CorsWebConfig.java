package com.metalsa.supplier.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * configuracion del CORS para las peticiones http
 */
@Component
@Configuration
public class CorsWebConfig implements Filter {
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // response.setHeader("Access-Control-Allow-Origin", "https://testapps.metalsa.com:4443");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Accept, X-Requested-With, "
                        + "Access-Control-Allow-Headers, Access-Control-Allow-Origin, "
                        + "AuthorizationPortal, authorization, x-auth-token, Cache-Control");
        response.addHeader("X-Frame-Options", "SAMEORIGIN");
        
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }
    
    @Override
    public void destroy() {
    }
    
}
