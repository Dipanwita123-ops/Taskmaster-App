import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Todo } from 'src/app/list-todos/list-todos.component';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(private http:HttpClient) { }

retrieveAllTodos(){

   return this.http.get<Todo[]>(`http://localhost:8080/jpa/users/todos`);
  }

deleteTodo(Id){
  return this.http.delete(`http://localhost:8080/jpa/users/todos/${Id}`)
}

retrieveTodo(Id){
  return this.http.get<Todo>(`http://localhost:8080/jpa/users/todos/${Id}`)
}

updateTodo(Id ,todo){
  return this.http.put<Todo>(`http://localhost:8080/jpa/users/todos/${Id}` , todo);
}

createTodo(todo){
  return this.http.post<Todo>(`http://localhost:8080/jpa/users/todos` , todo);
}


}
