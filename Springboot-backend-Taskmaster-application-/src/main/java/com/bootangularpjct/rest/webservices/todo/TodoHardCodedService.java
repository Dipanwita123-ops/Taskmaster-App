package com.bootangularpjct.rest.webservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class TodoHardCodedService {


	private static List<Todo> todos = new ArrayList();
	private static long idCounter = 0;



	static {
		todos.add(new Todo(++idCounter, "Dipa", "code DSA", new Date(), true));
		todos.add(new Todo(++idCounter, "Anita", "Learn Java", new Date(), false));
		todos.add(new Todo(++idCounter, "Sumit", "Play cricket", new Date(), false));
		//todos.add(new Todo(++idCounter, "Dipanwita", "Test Spring Security", new Date(), false)); 
		//todos.add(new Todo(++idCounter, "Dipanwita", "Build Todo app", new Date(), false));
	}


//	public List<Todo> findAll() {
//		return todos ;
////        return todos.stream()
////                .filter(todo -> todo.getUsername().equalsIgnoreCase(username))
////                .collect(Collectors.toList());
//    }
	
	public List<Todo> findAll() {
		// TODO Auto-generated method stub
		return todos;
	}
	
//	public Todo findByIdAndUsername(long id, String username) {
//        return todos.stream()
//                .filter(todo -> todo.getId() == id && todo.getUsername().equalsIgnoreCase(username))
//                .findFirst()
//                .orElse(null);
//    }
	public Todo findById(long id) {
		for(Todo todo:todos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		
		return null;
	}
	

	public Todo save(Todo todo) {
		if(todo.getId()==-1 || todo.getId()==0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}

//	public Todo deleteByIdAndUsername(long id, String username) {
//        Todo todo = findByIdAndUsername(id, username);
//        if (todo != null && todos.remove(todo)) {
//            return todo;
//        }
//        return null;
//    }
//	
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		
		if(todo==null) return null;
		
		if(todos.remove(todo)) {
			return todo;
		}
		
		return null;
	}
	
	

//	public Todo findById(long id) {
//        return todos.stream()
//                .filter(todo -> todo.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }

	

//	public List<Todo> findAllByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
