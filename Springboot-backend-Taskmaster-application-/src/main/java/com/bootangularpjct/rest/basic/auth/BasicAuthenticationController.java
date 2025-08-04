package com.bootangularpjct.rest.basic.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//controller

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

@RestController
public class BasicAuthenticationController {

	 private String getLoggedInUsername() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        return authentication.getName();
	    }


	  @GetMapping(path = "/basicauth")
	    public AuthenticationBean helloWorldBean() {
	        String username = getLoggedInUsername();
	        return new AuthenticationBean("You are authenticated as " + username);
	    }

	   
//Returning a  bean object
//	@GetMapping(path = "/basicauth")
//
//  public AuthenticationBean  helloWorldBean() {
//
//		// return "You are authenticated";
//	return new AuthenticationBean("You are authenticated");
//		}
//
//
//	}

}
