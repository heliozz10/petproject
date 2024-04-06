package com.heliozz10.petproject.repository;

import com.heliozz10.petproject.entity.Authority;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends ListCrudRepository<Authority, Long> {
    Optional<Authority> findByName(String name);
}
