package todo.younah.todoHomework.todo.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String content;

    @Column
    private boolean isDone; //미완료:false / 완료:true

    @Enumerated(value = EnumType.STRING)
    @Column
    private TodoStatus status = TodoStatus.TODO_BEFORE;

    @Column
    private LocalDateTime created_at = LocalDateTime.now();

    @Column
    private LocalDateTime modified_at;

    @Column
    private LocalDateTime deleted_at;


    public enum TodoStatus {
        TODO_BEFORE("활동 전"),
        TODO_DONE("활동 완료");

        @Getter
        private String status;

        TodoStatus(String status) {
            this.status = status;
        }
    }

    public static Todo of(String content){
        Todo todo = new Todo();
        todo.setContent(content);
        todo.setCreated_at(LocalDateTime.now());
        return todo;
    }
}
