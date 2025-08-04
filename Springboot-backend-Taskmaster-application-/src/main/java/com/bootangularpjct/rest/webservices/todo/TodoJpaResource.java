package com.bootangularpjct.rest.webservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class TodoJpaResource {



	@Autowired
	private TodoHardCodedService todoService ;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	private String getLoggedInUsername() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication.getName();
	}
	
	@GetMapping("/jpa/users/todos")
	public List<Todo> getAllTodos() {
		return todoJpaRepository.findAll();
	//  return todoService.findAll(); // ✅ matches your method `findAll()`
	}
	
//	@GetMapping("/jpa/users/todos")
//	public List<Todo> getAllTodos(Authentication authentication) {
//	    Jwt jwt = (Jwt) authentication.getPrincipal();
//	    String username = jwt.getSubject(); // or jwt.getClaim("sub");
//	    
//	    return todoJpaRepository.find;
//	}
	
//	@GetMapping("/users/todos")
//	public List<Todo> getAllTodos(){
//	    String username = getLoggedInUsername();
//	    System.out.println("Logged-in username: " + username);
//	    return todoService.findAll(); // 👈 service method must filter by username
//	}
	
//	@GetMapping("/users/{username}/todos")
//	public List<Todo> getAllTodos(@PathVariable String username){
//		return todoService.findAll();
//	}

	@GetMapping("/jpa/users/todos/{id}")
	public Todo getTodo(@PathVariable long id ){
		return todoJpaRepository.findById(id).get();
		//return todoService.findById(id);
	}
	
//	@GetMapping("/users/todos/{id}")
//	public Todo getTodo(@PathVariable long id){
//	    String username = getLoggedInUsername();
//	    return todoService.findByIdAndUsername(id, username);
//	}

//	@DeleteMapping("/users/{username}/todos/{id}")
//	public ResponseEntity<Void> deleteTodo(@PathVariable String username , @PathVariable long id){
//
//
//		Todo todo = todoService.deleteById(id);
//		if(todo!=null) {
//			return ResponseEntity.noContent().build();
//		}
//		return ResponseEntity.notFound().build();
//	}


	//DELETE /users/todos/{id}
	@DeleteMapping("/jpa/users/todos/{id}")
	public ResponseEntity<Void> deleteTodo(	 @PathVariable long id){
		
		
		//Todo todo = todoService.deleteById(id);
		
		todoJpaRepository.deleteById(id);
	
			return ResponseEntity.noContent().build();
		
		
		//return ResponseEntity.notFound().build();
	}
	
	
	// Edit/update a Todo : PUT /users/{username}/todos/{todo_id}

//	@PutMapping("/users/{username}/todos/{id}")
//	public ResponseEntity<Todo> updateTodo(@PathVariable String username , @PathVariable long id , @RequestBody  Todo todo){
//
//		Todo todoUpdated = todoService.save(todo);
//		return new  ResponseEntity<>(todo, HttpStatus.OK);
//
//	}

	//Edit/Update a Todo
		//PUT /users/{user_name}/todos/{todo_id}
		@PutMapping("/jpa/users/todos/{id}")
		public ResponseEntity<Todo> updateTodo(@PathVariable long id, @RequestBody Todo todo){
				
			
			Todo todoUpdated = todoJpaRepository.save(todo);
			
			return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		}



	//Create a new Todo : POST /users/{user_name}/todos
	//existing post req
//		@PostMapping("/jpa/users/todos") 
//		public ResponseEntity<Void> createTodo( @RequestBody Todo todo){
//			
//			Todo createdTodo = todoJpaRepository.save(todo);
//			// get Location url of the created resource
//			//Get current resource url
//     		//append the {id} to get complete url like /users/todos/{id}
//			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//					.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
//			
//			return ResponseEntity.created(uri).build();
//		}

	//modified post req
		
		@PostMapping("/jpa/users/todos")
		public ResponseEntity<Void> createTodo(@RequestBody Todo todo) {
		    // Always treat this as a new todo
		    todo.setId(null);

		    // ✅ Extract username from JWT
		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
		    todo.setUsername(username);

		    Todo createdTodo = todoJpaRepository.save(todo);

		    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		            .path("/{id}")
		            .buildAndExpand(createdTodo.getId())
		            .toUri();

		    return ResponseEntity.created(uri).build();
		}
		
//	@PostMapping("/users/{user_name}/todos")
//
//	public ResponseEntity<Void> createTodo(@PathVariable String username , @RequestBody  Todo todo){
//
//		Todo createdTodo = todoService.save(todo);
//		// get Location url of the created resource
//		//Get current resource url
//		//append the {id} to get complete url like /users/{user_name}/todos/{id}
//	URI uri  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
//
//		return  ResponseEntity.created(uri).build();
//
//	}

}
