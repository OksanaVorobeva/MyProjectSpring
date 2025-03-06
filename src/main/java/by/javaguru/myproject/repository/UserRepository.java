package by.javaguru.myproject.repository;

import by.javaguru.myproject.dto.UserReadDto;
import by.javaguru.myproject.entity.User;
import by.javaguru.myproject.mapper.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        FilterUserRepository, QuerydslPredicateExecutor<User> {


    Optional<User> findByUsername(String username);
}
