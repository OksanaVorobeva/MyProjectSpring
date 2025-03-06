package by.javaguru.myproject.integration.controller;



import by.javaguru.myproject.annotation.IT;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.assertj.core.error.ActualIsNotEmpty;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static by.javaguru.myproject.dto.UserCreateEditDto.Fields.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
@SpringBootTest
@WithMockUser(username="test@gmail.ru",password = "test",authorities = {"ADMIN","USER"})
public class UserControllerIT {
    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));

    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(username, "test2@gmail.com")
                        .param(firstName, "Test")
                        .param(lastName, "TestTest")
                        .param(role, "ADMIN")
                        .param(birthDate, "01-01-2000")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/login")
                );
    }
}