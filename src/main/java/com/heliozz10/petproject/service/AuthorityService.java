package com.heliozz10.petproject.service;

import com.heliozz10.petproject.entity.Authority;
import com.heliozz10.petproject.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {
    private AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Optional<Authority> find(String name) {
        return authorityRepository.findByName(name);
    }
}
