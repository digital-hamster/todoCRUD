package todo.younah.todoHomework.todo.controller;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todo.younah.todoHomework.todo.dto.TodoDto;
import todo.younah.todoHomework.todo.response.TodoResponse;
import todo.younah.todoHomework.todo.service.TodoService;

import java.util.List;


@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor //생성자 주입 해주는 public VoteController(Series Repo ... 이거해줌)
public class TodoController {

    private final TodoService todoService;


    @PostMapping
    public TodoResponse saveTodo(@NotNull @RequestBody TodoDto todoDto){
        String content = todoDto.getContent();
        TodoResponse response = todoService.createTodo(content);

        return response;
    }

    @GetMapping
    public List<TodoResponse> getTodos(){
        return todoService.getTodos();
    }

    @GetMapping("/{todo_id}")
    public TodoResponse getTodo(@PathVariable("todo_id") long todoId){
        return todoService.getTodo(todoId);
    }

    //그냥 끝냈는지 아닌지를 구별
    @PatchMapping("/{todo_id}/{is_done}")
    public String patchTodo(@NotNull @PathVariable("todo_id") long todoId, @NotNull @PathVariable("is_done") boolean isDone){
        String response =  todoService.updateTodo(todoId, isDone);
        return response;
    }

    //내용을 수정하는 api
    @PatchMapping("/{todo_id}")
    public TodoResponse updateTodoContent(@NotNull @PathVariable("todo_id") long todoId, @RequestBody TodoDto todoDto){
        String content = todoDto.getContent();
        TodoResponse response =  todoService.updateContent(todoId, content);
        return response;
    }

    @DeleteMapping("{todo_id}")
    public void deleteTodo(@NotNull @PathVariable("todo_id") long todoId){
        todoService.deleteTodo(todoId);
    }

}
