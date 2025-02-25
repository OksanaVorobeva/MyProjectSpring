package by.javaguru.myproject.integration.repository;

import by.javaguru.myproject.annotation.IT;
import by.javaguru.myproject.entity.Role;
import by.javaguru.myproject.entity.User;
import by.javaguru.myproject.repository.UserRepository;
import jakarta.persistence.EntityManager;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@IT
@RequiredArgsConstructor
public class UserRepositoryTest {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Test
    void save(){
        var user = User.builder()
                .username("username")
                .password("password")
                .birthDate(LocalDate.now())
                .role(Role.ADMIN)
                .firstname("test")
                .lastname("test")
                .build();
        entityManager.persist(user);
        assertNotNull(user.getId());

    }
}
