package com.metalsa.supplier.controller;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import io.jsonwebtoken.JwtBuilder;
// //import es.softtek.jwtDemo.dto.User;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

//@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class TokenUserController {
	private final String secretKey = "m3t4ls4%$P99r#";
//	private final String keyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuPhLbLeFIe8w6Jvvs-QlR-7Gx4TKdNAPDXJSv9DY78RByJKQpCkKHenpVaN5dmCK3aPz5z5IZ0oMN-RTX92CxBMf1fQZ2BDRFw-9N4lezWDVhy3qkqsNcEB7SiJrntlXviGvw3fDPV_4v9Bx0JwsIQ-dZx8wl_021NNJM3WvdgwIDAQAB";
	private final String issuer = "metalsa.com";

	// @PostMapping("vendorToken")
	// public Map login(@RequestParam("vendorId") String vendorId, @RequestParam("idioma") String idioma) {
		
	// 	String token = getJWTToken(vendorId, idioma);
	// 	Map userMap = new HashMap();
	// 	userMap.put("vendorId",vendorId);
	// 	userMap.put("token",token);		
	// 	return userMap;
		
	// }
	
// 	@PostMapping("token")
// 	public void login(@RequestParam("user") String token) {
		
// 		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//         long nowMillis = System.currentTimeMillis();
//         Date now = new Date(nowMillis);
//         //We will sign our JWT with our ApiKey secret
//         byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
//         Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        
//         Jwts.parser().setSigningKey(signingKey).parse(token);
		
// 	}

// 	private String getJWTToken(String username, String idioma) {
// 		/*
// 		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
// 		String token = Jwts.builder().setId("supplierJWT")
// 					   .setSubject(username)
// 					   .claim("authorities",grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
// 					   .setIssuedAt(new Date(System.currentTimeMillis()))
// 					   .setExpiration(new Date(System.currentTimeMillis() + 600000))
// 					   .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
// 		return "Bearer " + token;
// 		*/
				
//         //Let's set the JWT Claims SPX
//         JwtBuilder builder = Jwts.builder().setId("1")
//                 .setIssuedAt(new Date(System.currentTimeMillis()))
//                 .setSubject(issuer)
//                 .claim("vendorId", username)
//                 .claim("idioma", idioma)
// //                .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 720))
//                 .setExpiration(new Date(System.currentTimeMillis() + 600000))
//                 .signWith(SignatureAlgorithm.HS256, secretKey.getBytes());
        
                
//         return "Bearer " + builder.compact();

// 	}
	
}
