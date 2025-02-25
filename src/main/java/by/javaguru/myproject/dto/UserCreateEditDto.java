package by.javaguru.myproject.dto;

import by.javaguru.myproject.entity.Role;
import by.javaguru.myproject.validator.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo
public class UserCreateEditDto {
    @Email
    String username;
    String rawPassword;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate birthDate;
    @Size(min = 3, max = 30)
    String firstName;
    String lastName;
    Role role;
    MultipartFile image;
}
