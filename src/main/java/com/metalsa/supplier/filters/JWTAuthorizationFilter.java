package com.metalsa.supplier.filters;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import com.metalsa.supplier.controller.TokenApiController;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.Header;
// import io.jsonwebtoken.Jwt;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.MalformedJwtException;
// import io.jsonwebtoken.UnsupportedJwtException;
import net.minidev.json.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

// @Component
// @Order(Ordered.HIGHEST_PRECEDENCE)
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	
	private final String PREFIX = "Bearer ";
	private final String SECRET_KEY = "m3t4ls4%$P99r#";
	
	// private final String HEADER = "Authorization";
	private static final String TOKEN_HEADER = "Authorization";
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try { 
			System.out.println("JWTAuthorizationFilter doFilterInternal(request)");
			//System.out.println("JWTAuthorizationFilter doFilterInternal() -> " + " - " + request.getHeader(TOKEN_HEADER));
			
			if (existeJWTToken(request, response)) {
				
				String tokenPortal = request.getHeader(TOKEN_HEADER);
//				checkToken(tokenPortal);
				System.out.println("tokenPortal -> " + tokenPortal);
				Claims claims = validateToken(request);
				if (claims.get("vendorId") != null) {
					//setUpSpringAuthentication(claims);
	                
	                // if(tokenPortal == null){
	                //     tokenPortal = request.getHeader(TOKEN_HEADER) != null ? request.getHeader(TOKEN_HEADER).substring(7, request.getHeader(TOKEN_HEADER).length()) : request.getHeader(TOKEN_HEADER);
	                // }
	                JSONObject respuesta = validarTokenPortal(claims);
	                System.out.println("respuesta -> " + respuesta);
	                if (respuesta.get("status").equals(HttpStatus.ACCEPTED.value())) {
	                    
	                	response.addHeader("vendorId", respuesta.get("vendorId").toString());
	                    Cookie cookie = new Cookie("vendorId", respuesta.get("vendorId").toString());
	                    cookie.setHttpOnly(true);
	                    cookie.setSecure(false);
	                    cookie.setPath("/");
	                    Cookie cookieIdioma = new Cookie("idioma", respuesta.get("idioma").toString());
	                    cookieIdioma.setHttpOnly(true);
	                    cookieIdioma.setSecure(false);
	                    cookieIdioma.setPath("/");
	                    response.addCookie(cookie);
	                    response.addCookie(cookieIdioma);
	                    
	                    setUpSpringAuthentication(claims);
	                    //chain.doFilter(request, response);
	                } else {
	                    response.setStatus(HttpStatus.FORBIDDEN.value());
						SecurityContextHolder.clearContext();
	                    //response.setStatus(HttpStatus.ACCEPTED.value());
	                }
				} else {
					response.setStatus(HttpStatus.FORBIDDEN.value());
					SecurityContextHolder.clearContext();
				}
			} else {

					SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}	

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(TOKEN_HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(jwtToken).getBody();
	}
	
	// private Claims validateTokenPortal(HttpServletRequest request) {
	// 	String jwtToken = request.getHeader(HEADER_PORTAL).replace(PREFIX, "");		
		
	// 	Jwt<Header, String> vendorId = Jwts.parser().parsePlaintextJwt(jwtToken);
	// 	System.out.println("vendorId -> " + vendorId.getBody());
		
	// 	return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(jwtToken).getBody();
	// }

	/**
	 * Metodo para autenticarnos dentro del flujo de Spring
	 * 
	 * @param claims
	 */
	private void setUpSpringAuthentication(Claims claims) {		
		List<String> authorities = Arrays.asList((String) claims.get("vendorId"),(String) claims.get("idioma"));
		
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(TOKEN_HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		
		return true;
	}
	
	public JSONObject validarTokenPortal(Claims claims) {
        JSONObject respuesta = new JSONObject();
        try {
        	
//          Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(keyString)).parseClaimsJws(token).getBody();
            
            respuesta.put("status", HttpStatus.ACCEPTED.value());
            respuesta.put("vendorId", claims.get("vendorId", String.class));
            respuesta.put("idioma", claims.get("idioma", String.class));
                        
            return respuesta;
        } catch (Exception e) {
        	System.out.println("Error token" + e.getLocalizedMessage());
            respuesta.put("status", HttpStatus.FORBIDDEN.value());
            return respuesta;
        }
    }
	
// 	private boolean checkToken(String token) {

//         try {
//             JWT jwt = JWTParser.parse(token);
//             SignedJWT sjwt = (SignedJWT) jwt;
            
//             System.out.println(" - " 
//             + " - " + jwt.getJWTClaimsSet().getStringClaim("vendorId")
//             + " - " + jwt.getJWTClaimsSet().getStringClaim("idioma")            
//             + " - " + jwt.getJWTClaimsSet().getSubject()
//             );
            
                        
//             // Base64URL signature = sjwt.getSignature();
//             Base64 decode = new Base64(keyString);
//             byte[] publicBytes = decode.decode();
//             X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
//             KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
//             PublicKey pubKey = keyFactory.generatePublic(keySpec);
//             JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) pubKey);
            
            
//             boolean validSignature = sjwt.verify(verifier);
//             Date now = new Date();
//             boolean validDate = jwt.getJWTClaimsSet().getExpirationTime().after(now);
//             boolean validIssuer = issuer.equals(jwt.getJWTClaimsSet().getIssuer());

//             return validSignature && validDate && validIssuer;
//         } catch (JOSEException | NoSuchAlgorithmException | InvalidKeySpecException | ParseException e) {
// //            log.error("checkToken: ", e);
// //            log.debug("token invalido ->" + token, e);
//             return false;
//         }
//     }
		

}
