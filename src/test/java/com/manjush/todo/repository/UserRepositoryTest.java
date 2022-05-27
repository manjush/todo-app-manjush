package com.manjush.todo.repository;

import com.manjush.todo.entity.ToDo;
import com.manjush.todo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void canSaveUser() {
        //given
        String username = "testUsername";
        String password = "testPassword";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //when
        User persistedUser = userRepository.save(user);

        //then
        assertEquals(username, persistedUser.getUsername());
        assertEquals(password, persistedUser.getPassword());
        assertNotNull(user.getId());
    }

    @Test
    public void canDeleteUser() {
        //given
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        User persistedUser = userRepository.save(user);

        //when
        userRepository.delete(persistedUser);

        //then
        Optional<User> userOpt = userRepository.findById(persistedUser.getId());
        assertFalse(userOpt.isPresent());

    }

    @Test
    public void canFindUser() {
        //given
        User user = new User();
        user.setUsername("testUser1");
        user.setPassword("testPassword");
        User persistedUser = userRepository.save(user);

        //when
        Optional<User> userOpt = userRepository.findById(persistedUser.getId());

        //then
        assertTrue(userOpt.isPresent());
        assertEquals(user.getUsername(), userOpt.get().getUsername());
        assertEquals(user.getPassword(), userOpt.get().getPassword());

    }

    @Test
    public void canSaveUserWithToDo() {
        //given
        String username = "testUsername";
        String password = "testPassword";
        String description = "todoDesc";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        ToDo todo = new ToDo();
        todo.setDescription(description);
        todo.setCompleted(true);
        user.setTodoList(List.of(todo));

        //when
        User persistedUser = userRepository.save(user);

        //then
        assertEquals(username, persistedUser.getUsername());
        assertEquals(password, persistedUser.getPassword());
        assertEquals(description, persistedUser.getTodoList().stream().findFirst().get().getDescription());
        assertNotNull(user.getId());
    }

}