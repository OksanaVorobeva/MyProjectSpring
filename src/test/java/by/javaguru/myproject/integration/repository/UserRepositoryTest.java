package by.javaguru.myproject.integration.repository;

import by.javaguru.myproject.annotation.IT;
import by.javaguru.myproject.dto.UserFilter;
import by.javaguru.myproject.entity.Role;
import by.javaguru.myproject.entity.User;
import by.javaguru.myproject.repository.UserRepository;
import jakarta.persistence.EntityManager;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@IT
@RequiredArgsConstructor
@SpringBootTest
public class UserRepositoryTest {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    private static final Long USER_ID = 3L;
    private static final String USER_NAME = "oksana@gmail.ru";

    @Test
    void create(){
        var user = User.builder()
                .username("username")
                .password("password")
                .birthDate(LocalDate.now())
                .role(Role.ADMIN)
                .firstName("test")
                .lastName("test")
                .build();
        entityManager.persist(user);
        assertNotNull(user.getId());

    }

    @Test
    void delete() {
        var maybeUser = userRepository.findById(USER_ID);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(userRepository::delete);
        entityManager.flush();
        assertTrue(userRepository.findById(USER_ID).isEmpty());
    }


    @Test
    void findById() {
        var user = entityManager.find(User.class, USER_ID);
        assertNotNull(user);
        assertThat(user.getUsername()).hasSize(13);
    }

    @Test
    public void findAllByFilter() {

        UserFilter userFilter=
                new UserFilter("Oksana","Vorobeva",LocalDate.of(1982,02,20));

        List<User> users = userRepository.findAllByFilter(userFilter);

        assertThat(users).hasSize(1);
    }

    @Test
    public void update() {

        var user = User.builder()
                .username("username")
                .password("password")
                .birthDate(LocalDate.now())
                .role(Role.ADMIN)
                .firstName("test")
                .lastName("test")
                .build();
        entityManager.persist(user);

        user.setLastName("testUpdate");
        user.setFirstName("testUpdate");
        entityManager.flush();

        assertThat(user.getLastName()).isEqualTo("testUpdate");
        assertThat(user.getFirstName()).isEqualTo("testUpdate");
        assertNotNull(user.getId());
    }

    @Test
    public void findAllByUserName(){
        Optional<User> foundUser = userRepository.findByUsername(USER_NAME);
        assertTrue(foundUser.isPresent());
        assertEquals(USER_NAME, foundUser.get().getUsername());
    }
}
