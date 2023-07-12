package todo.younah.todoHomework.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import todo.younah.todoHomework.todo.entity.Todo;
import todo.younah.todoHomework.todo.repository.TodoRepository;
import todo.younah.todoHomework.todo.response.TodoResponse;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepo;

    @Transactional
    public TodoResponse createTodo(String content){

        Todo todo = Todo.of(content);
        todoRepo.save(todo);

        return response(todo);
    }


    @Transactional
    public TodoResponse getTodo(long todoId){
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(()->new IllegalStateException("존재하지 않는 todo입니다."));
        return response(targetTodo);
    }


    @Transactional
    public List<TodoResponse> getTodos(){
        List<Todo> todos = todoRepo.findAll(Sort.by("id").descending());

        todos.forEach(response -> {
            long todoId = response.getId();
            if (todoId == 0) {
                throw new IllegalStateException("존재하지 않는 todo입니다.");
            }
        });

        List<TodoResponse> response = todos.stream().map(todo -> response(todo)).collect(Collectors.toList());

        return response;
    }


    @Transactional
    public String updateTodo(long todoId, Boolean isDone){

        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(()->new IllegalStateException("존재하지 않는 todo입니다."));

        if(isDone){ //false: 활동 전 + 활동중/ true: 활동 완료
            targetTodo.setStatus(Todo.TodoStatus.TODO_DONE);
            targetTodo.setDone(true);
        }else{
            targetTodo.setStatus(Todo.TodoStatus.TODO_BEFORE);
            targetTodo.setDone(false);
        }
        targetTodo.setModified_at(LocalDateTime.now());
        todoRepo.save(targetTodo);

        String response = "success";

        return response;
    }

    @Transactional
    public TodoResponse updateContent(long todoId, String content){
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(()->new IllegalStateException("존재하지 않는 todo입니다."));

        targetTodo.setContent(content);
        targetTodo.setModified_at(LocalDateTime.now());

        todoRepo.save(targetTodo);
        return response(targetTodo);

    }

    public void deleteTodo(long todoId){
        todoRepo.deleteById(todoId);
    }




    public TodoResponse response(Todo todo) { //voteCountAddResponse
        return TodoResponse.of(
                todo.getId(),
                todo.getStatus(),
                todo.getContent(),
                todo.getCreated_at()
        );
    }


}
