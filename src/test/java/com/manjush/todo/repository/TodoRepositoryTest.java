package com.manjush.todo.repository;

import com.manjush.todo.entity.ToDo;
import com.manjush.todo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void canSaveToDo() {
        //given
        String desc = "todoDesc";

        ToDo todo = new ToDo();
        todo.setDescription(desc);
        todo.setCompleted(true);

        //when
        ToDo persistedToDo = todoRepository.save(todo);

        //then
        assertEquals(desc, persistedToDo.getDescription());
        assertTrue(persistedToDo.getCompleted());
    }

    @Test
    public void canSaveToDoWithUser() {
        //given
        String desc = "todoDesc";

        ToDo todo = new ToDo();
        todo.setDescription(desc);
        todo.setCompleted(true);

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPass");

        todo.setUser(user);

        //when
        ToDo persistedToDo = todoRepository.save(todo);

        //then
        assertEquals(desc, persistedToDo.getDescription());
        assertTrue(persistedToDo.getCompleted());
    }
}
