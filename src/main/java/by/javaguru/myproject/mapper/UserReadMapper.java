package by.javaguru.myproject.mapper;

import by.javaguru.myproject.dto.UserReadDto;
import by.javaguru.myproject.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getBirthDate(),
                object.getFirstName(),
                object.getLastName(),
                object.getRole(),
                object.getImage()
        );
    }
}
