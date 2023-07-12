package todo.younah.todoHomework.todo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import todo.younah.todoHomework.todo.entity.Todo;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {
    long todoId;
    Todo.TodoStatus todoStatus;
    LocalDateTime createdAt;
    String content;

    public static TodoResponse of(long todoId, Todo.TodoStatus todoStatus, String content, LocalDateTime createdAt){
        TodoResponse response = new TodoResponse();

        response.setTodoId(todoId);
        response.setTodoStatus(todoStatus);
        response.setContent(content);
        response.setCreatedAt(createdAt);

        return response;
    }
}
