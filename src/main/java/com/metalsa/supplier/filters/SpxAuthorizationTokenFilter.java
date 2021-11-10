package com.metalsa.supplier.filters;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWT;
//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.JWSVerifier;
//import com.nimbusds.jose.crypto.RSASSAVerifier;
//import com.nimbusds.jose.util.Base64;
//import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
//import com.nimbusds.jwt.SignedJWT;
//import com.nimbusds.jose.util.Base64URL;
//import com.auth0.jwt.impl.JWTParser;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
//</TOKEN>
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import net.minidev.json.JSONObject;

/**
 * Filtro para chequear el header de autorizacion
 */

//@Log4j
// @Component
// @Order(Ordered.HIGHEST_PRECEDENCE)
public class SpxAuthorizationTokenFilter {
   //extends OncePerRequestFilter {
	
//	#llaves token
//	PUBLIC_KEY=MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuPhLbLeFIe8w6Jvvs-QlR-7Gx4TKdNAPDXJSv9DY78RByJKQpCkKHenpVaN5dmCK3aPz5z5IZ0oMN-RTX92CxBMf1fQZ2BDRFw-9N4lezWDVhy3qkqsNcEB7SiJrntlXviGvw3fDPV_4v9Bx0JwsIQ-dZx8wl_021NNJM3WvdgwIDAQAB
//	PRITAVE_KEY=MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK4-Etst4Uh7zDom--z5CVH7sbHhMp00A8NclK_0NjvxEHIkpCkKQod6elVo3l2YIrdo_PnPkhnSgw35FNf3YLEEx_V9BnYENEXD703iV7NYNWHLeqSqw1wQHtKImue2Ve-Ia_Dd8M9X_i_0HHQnCwhD51nHzCX_TbU00kzda92DAgMBAAECgYBfuo7ViGFrV0ie8mgai8AJIMVBuz35jGg83xJ5kSxyxkCI1YSzX4WptSqc3h69QSZhqfbCsL3OHhAOjzhaZvjhnWHtw0MaZ6bPs2VAe_rim9Asx_ItPLyFWCXtWDdO2Nkq5y37IecJRcjqwotyiJNtGZf62Av7IL3osBM6Eh7D4QJBAPhtmO0x8yXqbRi2J8wAlkASSDycx731QxACss-JfZjyEhrBn1OR1ytPFZaKwrs_jKhC5nmJtMriBgwTBty3j4kCQQCzjaFVtRoXUM3yEvDeKYGjTR2CpR4LoqtuhJnMoUFGNxah43dEQG5ZkyDwW_LVDOzGyr3KAlEIyWlMEePG0tWrAkEA6l9TiGu4bkv1MvwfHZpsJfRZjD2JQBBUfZVeJugawyGVrQKAkvIjYmuQ_V7aCpgQ1mmPUJh3JXUJqXiF39OZqQJBAIoIYXUQKqSt_AUTRpSj-ANgb0VWRojPX1cxHUNQ3GtsQjvmMHQJzEuUbggZx869hvE0Pz8jFMTagdZ3ElJxFs0CQQCS2zLtxvLS5Cq6Mn7Xhe6Zv2NEvCW1F0IbRN6k7d8c8xonkiNLxZ0pDwV-5lMyQA7kjOV0OTamdVJWFn5DViwa
//	issuer=metalsa.com
	private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PORTAL = "AuthorizationPortal";
    private static final String ALGORITHM = "RSA";//"HS256";
//	@Value("${PUBLIC_KEY}")
    private final String keyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuPhLbLeFIe8w6Jvvs-QlR-7Gx4TKdNAPDXJSv9DY78RByJKQpCkKHenpVaN5dmCK3aPz5z5IZ0oMN-RTX92CxBMf1fQZ2BDRFw-9N4lezWDVhy3qkqsNcEB7SiJrntlXviGvw3fDPV_4v9Bx0JwsIQ-dZx8wl_021NNJM3WvdgwIDAQAB";
//    @Value("${issuer}")
    private final String issuer = "Metalsa";
//    @Value("${spx.enable-debug:false}")
    private Boolean debug;
    
    //<TOKEN>
//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//             HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         if("OPTIONS".equalsIgnoreCase(request.getMethod())){
//             response.addHeader("Access-Control-Allow-Origin", "*");
//             response.setStatus(HttpStatus.OK.value());
//         }else{
//             String tokenPortal = null;
            
//             if(request.getHeader(TOKEN_HEADER) != null){
//                 tokenPortal = request.getHeader(TOKEN_HEADER).replace("Bearer ", "");
//             }else{
//                 tokenPortal = request.getHeader(TOKEN_PORTAL);   
//             }
            
//             if(tokenPortal == null){
//                 tokenPortal = request.getHeader(TOKEN_HEADER) != null ? request.getHeader(TOKEN_HEADER).substring(7, request.getHeader(TOKEN_HEADER).length()) : request.getHeader(TOKEN_HEADER);
//             }
//             System.out.println("tokenPortal -> " + tokenPortal);
//             if(checkToken(tokenPortal)) {
                
//                 JSONObject respuesta = jsonRespuestaTokenPortal(tokenPortal);
                
//                 if (respuesta.get("status").equals(HttpStatus.ACCEPTED.value())) {
//                 	System.out.println("respuesta-> " + respuesta.toJSONString());
                	
//                     response.addHeader("vendorId", respuesta.get("vendorId").toString());
//                     Cookie cookie = new Cookie("vendorId", respuesta.get("vendorId").toString());
//                     cookie.setHttpOnly(false);
//                     cookie.setSecure(false);
//                     cookie.setPath("/");
//                     Cookie cookieIdioma = new Cookie("idioma", respuesta.get("idioma").toString());
//                     cookieIdioma.setHttpOnly(true);
//                     cookieIdioma.setSecure(true);
//                     cookieIdioma.setPath("/");
//                     response.addCookie(cookie);
//                     response.addCookie(cookieIdioma);
//                     setUpSpringAuthentication(respuesta);
                    
//                 } else {
//                     response.setStatus(HttpStatus.FORBIDDEN.value());
//                     //response.setStatus(HttpStatus.ACCEPTED.value());
//                 }
//             }
//         }
        
//         filterChain.doFilter(request, response);
//     }

//     private boolean checkToken(String token) {

//             try {
//             	JWT jwt = JWTParser.parse(token);     
// //            	Map<String, Claim> claimsMap = jwt.getClaims();    //Key is the Claim name
// //            	Claim claim = claimsMap.get("vendorId");

// //                long nowMillis = System.currentTimeMillis();
// //                Date now = new Date(nowMillis);
// //            	//We will sign our JWT with our ApiKey secret
// //                byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(keyString);
// //                Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());            	
                
// //                SignedJWT sjwt = (SignedJWT) jwt;
// //                Base64 decode = new Base64(keyString);
// //                byte[] publicBytes = decode.decode();
// //                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
// //                KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
// //                PublicKey pubKey = keyFactory.generatePublic(keySpec);
// //                JWSVerifier verifier1 = new RSASSAVerifier((RSAPublicKey) pubKey);
// //                boolean validSignature = sjwt.verify(verifier1);
            	
//                  System.out.println( jwt.getJWTClaimsSet().getIssuer()
//                   + " - " + jwt.getJWTClaimsSet().getStringClaim("vendorId")//jwt.getSignature()           
// 	              + " - " + jwt.getJWTClaimsSet().getSubject()
// 	              + " - " + jwt.getJWTClaimsSet().getExpirationTime()
//         		 );
// //               boolean validDate = signingKey2.equals(signingKey);//claims.getExpiration().after(new Date());
//                boolean validDate = jwt.getJWTClaimsSet().getExpirationTime().after(new Date());
//                boolean validIssuer = issuer.equals(jwt.getJWTClaimsSet().getSubject()) ;
//                                     // && jwt.getJWTClaimsSet().getExpirationTime().after(new Date()); 
               
//                return validIssuer; //&& validDate && validSignature;
//             } catch (NullPointerException | ParseException e){//| NoSuchAlgorithmException | InvalidKeySpecException | JOSEException  e){
//                 //Invalid signature/claims
//             	System.out.println("JWTDecodeException - " + e);
//             	return false;
//             }
            
//     }
    
//     public JSONObject jsonRespuestaTokenPortal(String token) {
//         JSONObject respuesta = new JSONObject();
//         try {
// //        	DecodedJWT jwt = JWT.decode(token); 
//         	JWT jwt = JWTParser.parse(token); 
//             respuesta.put("status", HttpStatus.ACCEPTED.value());
//             respuesta.put("vendorId", jwt.getJWTClaimsSet().getStringClaim("vendorId"));
//             respuesta.put("idioma", jwt.getJWTClaimsSet().getStringClaim("idioma"));
//             respuesta.put("subjet", jwt.getJWTClaimsSet().getSubject());
//             respuesta.put("expiration", jwt.getJWTClaimsSet().getExpirationTime());
                        
//             return respuesta;
//         } catch (Exception e) {
//         	System.out.println("Error token" + e.getLocalizedMessage());
//             respuesta.put("status", HttpStatus.FORBIDDEN.value());
//             return respuesta;
//         }
//     }
//     //</TOKEN>
    
//     private void setUpSpringAuthentication(JSONObject respuesta) {
// //		@SuppressWarnings("unchecked")
// //		List<String> authorities = (List) claims.get("authorities");		
// 		List<String> authorities = Arrays.asList((String) respuesta.get("vendorId"),(String) respuesta.get("idioma") );
		
		
// 		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(respuesta.get("subjet") +":"+(String) respuesta.get("vendorId"), null,
// 				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

// 		SecurityContextHolder.getContext().setAuthentication(auth);

// 	}


//     /**
//      * En esta metodo se agregan al list las URL que deseamos que no tengan
//      * seguridad, puede ser un modulo completo o una URL en particular
//      *
//      * @param url
//      * @return true si la URL contiene algun elemento de la lista
//      * @date 10/Jun/2020
//      */
//     public boolean allowUrlPortal(String url) {
//     	System.out.println("url -> " + url);
//         List<String> allowUrl = new ArrayList();
//         allowUrl.add("allUen");
//         allowUrl.add("user");
//         allowUrl.add("/api/v1/token/validarTokenPortal");
//         allowUrl.add("/api/v1/cotizacion");
//         allowUrl.add("/api/v1/sp/catalogo/uenssp/");
//         boolean isMatch = allowUrl.stream().anyMatch(t -> url.contains(t));
//         System.out.println("isMatch -> " + isMatch);
        
//         return isMatch;
//     }

//     public boolean allowUrlSinToken(String url) {
//         List<String> allowUrl = new ArrayList();
//         allowUrl.add("/getTokenPortal/");
//         allowUrl.add("/utilerias/getReclasificacion");
//         allowUrl.add("/sendEmails/");
//         allowUrl.add("/guardarCalificacion");
//         return allowUrl.stream().anyMatch(t -> url.contains(t));
//     }

}
