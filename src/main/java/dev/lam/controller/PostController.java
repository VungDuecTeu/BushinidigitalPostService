package dev.lam.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.json.mgmt.Permission;
import com.auth0.json.mgmt.Scope;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import dev.lam.user.AuthController;

@RestController()
@RequestMapping(value = "/post")
public class PostController {
	
	@Autowired
	AuthController authcontroller;
	
	@GetMapping() 
	ResponseEntity<String> getPostById(HttpServletRequest request) {
		try {
			Optional<DecodedJWT> jwt = authcontroller.validate(request);
		    Claim claims = jwt.orElseThrow(Exception::new).getClaim("scope");

		    if (claims.asString().matches("(.*)read:current_user(.*)")) {
				return ResponseEntity.ok().body("hey");	
		    }
		    
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    
		} catch (Exception exception){
			exception.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
