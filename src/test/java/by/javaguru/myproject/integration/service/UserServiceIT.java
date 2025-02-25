package by.javaguru.myproject.integration.service;

import by.javaguru.myproject.annotation.IT;
import by.javaguru.myproject.dto.UserCreateEditDto;
import by.javaguru.myproject.dto.UserReadDto;
import by.javaguru.myproject.entity.Role;
import by.javaguru.myproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.assertj.core.api.Assertions.assertThat;

@IT

public class UserServiceIT {
    private final UserService userService;

    public UserServiceIT(UserService userService) {
        this.userService = userService;
    }

    @Test
    void findAll(){
        List<UserReadDto> result=userService.findAll();
        assertThat(result).isNotNull();
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                new MockMultipartFile("test", new byte[0])
        );
        UserReadDto actualResult = userService.create(userDto);

        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthDay(), actualResult.getBirthDate());
        assertEquals(userDto.getFirstName(), actualResult.getFirstname());
        assertEquals(userDto.getLastName(), actualResult.getLastname());
        assertSame(userDto.getRole(), actualResult.getRole());
    }
}
