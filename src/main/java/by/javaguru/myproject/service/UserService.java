package by.javaguru.myproject.service;

import by.javaguru.myproject.dto.UserCreateEditDto;
import by.javaguru.myproject.dto.UserFilter;
import by.javaguru.myproject.dto.UserReadDto;
import by.javaguru.myproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<UserReadDto> findAll(UserFilter filter, Pageable pageable);

    List<UserReadDto> findAll();

    Optional<UserReadDto> findById(Long id);

    UserReadDto create(UserCreateEditDto userCreateEditDto);

    Optional<UserReadDto> update(Long id, UserCreateEditDto userCreateEditDto);

    boolean delete(Long id);

    Optional<byte[]> findAvatar(Long id);

    User findByUsername(String username);

    void uploadImage(MultipartFile image);
}
