package by.javaguru.myproject.mapper;

import by.javaguru.myproject.dto.UserCreateEditDto;
import by.javaguru.myproject.entity.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final PasswordEncoder passwordEncoder;


    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCreateEditDto fromUser, User toUser) {
        toUser.setUsername(fromUser.getUsername());
        toUser.setFirstName(fromUser.getFirstName());
        toUser.setLastName(fromUser.getLastName());
        toUser.setBirthDate(fromUser.getBirthDate());
        toUser.setRole(fromUser.getRole());
        Optional.ofNullable(fromUser.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(toUser::setPassword);
        Optional.ofNullable(fromUser.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> toUser.setImage(image.getOriginalFilename()));
    }
}
