package by.javaguru.myproject.http.controller;


import by.javaguru.myproject.dto.PageResponse;
import by.javaguru.myproject.dto.UserReadDto;
import by.javaguru.myproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/user")
    public String loginSuccessHandler() {
        return "/user/user";
    }

}

