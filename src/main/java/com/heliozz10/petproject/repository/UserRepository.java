package com.heliozz10.petproject.repository;

import com.heliozz10.petproject.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findByUsername(String username);
    @EntityGraph(attributePaths = "authorities")
    List<User> findByFirstName(String firstName);
    @EntityGraph(attributePaths = "authorities")
    List<User> findBySecondName(String secondName);
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findByEmail(String email);
}
