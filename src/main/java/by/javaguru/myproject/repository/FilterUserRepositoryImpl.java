package by.javaguru.myproject.repository;

import by.javaguru.myproject.dto.QPredicates;
import by.javaguru.myproject.dto.UserFilter;
import  com.querydsl.jpa.impl.JPAQuery;
import by.javaguru.myproject.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static by.javaguru.myproject.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.firstName(),user.firstName::containsIgnoreCase)
                .add(filter.lastName(),user.lastName::containsIgnoreCase)
                .add(filter.birthDate(),user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}
