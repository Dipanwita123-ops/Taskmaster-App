package com.bootangularpjct.rest.webservices.helloworld;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//controller

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

@RestController
public class HelloWorldController {

	//GET
	//URI - /hello-world
	//method - ""Hello World"
	
	private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

	@RequestMapping(method = RequestMethod.GET , path = "/hello-world")

//	or use @GetMapping(path = "/hello-world")

	public String helloWorld() {

		return "Hello world" ;
	}

//Returning a  bean object
	@GetMapping(path = "/hello-world-bean")

  public HelloWorldBean  helloWorldBean() {

		//throw new RuntimeException("ERROR HAPPENED!!!");
	return new HelloWorldBean("Hello World Changed");
		}

	//hello-world/path-variable/{name}=Dipanwita

//	@GetMapping(path = "/hello-world/path-variable/{name}")
//	
//	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
//	    return new HelloWorldBean(String.format("Hello World , %s", name));
//	}

	@GetMapping(path = "/hello-world/path-variable")
	public HelloWorldBean helloWorldPathVariable() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    String message = String.format("Hello, %s! Welcome to the Hello World API.", username);
	    return new HelloWorldBean(message);
	}

//	@GetMapping(path = "/hello-world/path-variable")
//
//	public HelloWorldBean helloWorldPathVariable(@PathVariable(required = false ) String name) {
//
//		if(name == null)
//			name = "Guest" ;
//
//		return new HelloWorldBean(String.format("Hello World , " +name)) ;
//	}

	}


