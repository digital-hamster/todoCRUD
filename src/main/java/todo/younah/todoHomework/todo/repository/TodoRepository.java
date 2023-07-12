package todo.younah.todoHomework.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.younah.todoHomework.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
