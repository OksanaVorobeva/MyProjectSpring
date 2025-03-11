package by.javaguru.myproject.integration.service;

import by.javaguru.myproject.annotation.IT;
import by.javaguru.myproject.dto.UserCreateEditDto;
import by.javaguru.myproject.dto.UserReadDto;
import by.javaguru.myproject.entity.Role;
import by.javaguru.myproject.entity.User;
import by.javaguru.myproject.mapper.UserReadMapper;
import by.javaguru.myproject.repository.UserRepository;
import by.javaguru.myproject.service.ImageService;
import by.javaguru.myproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

@IT
@RequiredArgsConstructor
@SpringBootTest
public class UserServiceIT {
    private final UserService userService;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final static Long USER_ID = 1L;


    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).isNotNull();
    }

    @Test
    void findById() {
        Optional<UserReadDto> result = userService.findById(USER_ID);
        List<UserReadDto> userReadDto = userService.findAll();
        assertTrue(result.isPresent(),"User be present");
        assertEquals(userReadDto.get(0), result.get());
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
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getFirstName(), actualResult.getFirstName());
        assertEquals(userDto.getLastName(), actualResult.getLastName());
        assertSame(userDto.getRole(), actualResult.getRole());
    }


    @Test
    public void findAvatar() {

        Long userId1 = 1L;
        Long userId2 = 4L;


        Optional<byte[]> avatar = userService.findAvatar(userId1);
        Optional<byte[]> avatar2 = userService.findAvatar(userId2);

        assertFalse(avatar.isPresent(), "Avatar should not be present for user with no image");
        assertTrue(avatar2.isPresent(), "Avatar should be present");
    }


}

