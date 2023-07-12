package todo.younah.todoHomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("todo.younah.todoHomework.todo.entity")
public class TodoHomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoHomeworkApplication.class, args);
	}

}
