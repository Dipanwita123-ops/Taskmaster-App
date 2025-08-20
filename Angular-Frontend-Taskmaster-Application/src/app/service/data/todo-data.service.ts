import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Todo } from 'src/app/list-todos/list-todos.component';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(private http:HttpClient) { }

retrieveAllTodos(){

   return this.http.get<Todo[]>(`${environment.apiUrl}/jpa/users/todos`);
  }

deleteTodo(Id){
  return this.http.delete(`${environment.apiUrl}/jpa/users/todos/${Id}`)
}

retrieveTodo(Id){
  return this.http.get<Todo>(`${environment.apiUrl}/jpa/users/todos/${Id}`)
}

updateTodo(Id ,todo){
  return this.http.put<Todo>(`${environment.apiUrl}/jpa/users/todos/${Id}` , todo);
}

createTodo(todo){
  return this.http.post<Todo>(`${environment.apiUrl}/jpa/users/todos` , todo);
}


}
