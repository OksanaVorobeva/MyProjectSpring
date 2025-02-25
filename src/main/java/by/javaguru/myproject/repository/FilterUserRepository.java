package by.javaguru.myproject.repository;

import by.javaguru.myproject.dto.UserFilter;
import by.javaguru.myproject.entity.User;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}
