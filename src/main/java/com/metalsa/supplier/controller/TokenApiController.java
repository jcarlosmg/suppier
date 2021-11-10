package com.metalsa.supplier.controller;

import com.metalsa.supplier.pojo.PoVendorPojo;
import com.metalsa.supplier.pojo.Token;
import com.metalsa.supplier.services.VendorService;
import com.metalsa.supplier.services.SpTblCatalogoService;
import com.nimbusds.jose.util.Base64;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/api/suppliers/")
public class TokenApiController {

    @Autowired
    private VendorService vendorService;

//	private final String keyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuPhLbLeFIe8w6Jvvs-QlR-7Gx4TKdNAPDXJSv9DY78RByJKQpCkKHenpVaN5dmCK3aPz5z5IZ0oMN-RTX92CxBMf1fQZ2BDRFw-9N4lezWDVhy3qkqsNcEB7SiJrntlXviGvw3fDPV_4v9Bx0JwsIQ-dZx8wl_021NNJM3WvdgwIDAQAB";
//  @Value("${issuer}")
    private final String issuer = "metalsa.com";  
    private final String keySecret = "m3t4ls4%$P99r#";
 
    //</PERFIL>
    @RequestMapping(value = "generateToken/{user}/{pass}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map> getTokenPortal(@PathVariable("user") String user, @PathVariable("pass") String pass) {

        PoVendorPojo existVendor = validaVendor(user, pass); 
        
        if(existVendor != null){
            String token = getJWTToken(existVendor.getVendorId().toString(), existVendor.getIdioma());
            Map userMap = new HashMap();
            userMap.put("vendorId",existVendor.getVendorId().toString());
            userMap.put("token",token);
            System.out.print("obj : " + existVendor.getVendorId().toString());
            return new ResponseEntity(userMap, HttpStatus.OK);
        }else{
            Map userMap = new HashMap();
            userMap.put("vendorId",user);
            userMap.put("token","invalid vendor");
            return new ResponseEntity(userMap, HttpStatus.UNAUTHORIZED);
        }
    }

    public boolean validaVendor(String vendorID) {        
        PoVendorPojo vendorMap = vendorService.getProveedorByVendorEncoded(vendorID);        
        return vendorMap == null ? false : true;
    }

    public PoVendorPojo validaVendor(String user, String pass) {        
        PoVendorPojo vendorMap = vendorService.getProveedorByVendorLogin(user, pass);        
        return vendorMap;
    }

    
    private String getJWTToken(String vendorID, String idioma) {
		/*
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts.builder().setId("supplierJWT")
					   .setSubject(username)
					   .claim("authorities",grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
					   .setIssuedAt(new Date(System.currentTimeMillis()))
					   .setExpiration(new Date(System.currentTimeMillis() + 600000))
					   .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return "Bearer " + token;
		*/
				
        //Let's set the JWT Claims SPX
        JwtBuilder builder = Jwts.builder().setId("supplierJWT")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(issuer)
                .claim("vendorId", vendorID)
                .claim("idioma", idioma)
//                .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 720))
                .setExpiration(new Date(System.currentTimeMillis() + 60000 * 30))
                .signWith(SignatureAlgorithm.HS512, keySecret.getBytes());
                        
        return "Bearer " + builder.compact();

	}
    
    public String getJWTToken2(String vendorID, String idioma, String name) {
        JwtBuilder builder = Jwts.builder().setId("supplierJWT")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(issuer)
                .claim("vendorId", vendorID)
                .claim("idioma", idioma)
                .claim("name", name)
                .setExpiration(new Date(System.currentTimeMillis() + 60000 * 30))
                .signWith(SignatureAlgorithm.HS512, keySecret.getBytes());
                        
        return "Bearer " + builder.compact();

	}
	
}
