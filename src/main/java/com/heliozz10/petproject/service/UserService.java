package com.heliozz10.petproject.service;

import com.heliozz10.petproject.entity.Authority;
import com.heliozz10.petproject.entity.User;
import com.heliozz10.petproject.exception.AuthorityNotFoundException;
import com.heliozz10.petproject.repository.AuthorityRepository;
import com.heliozz10.petproject.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private AuthorityService authorityService;
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, AuthorityService authorityService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }

    public User saveUser(String username, String email, String rawPassword) throws Exception {
        if(userRepository.findByUsername(username).isPresent()) return null;
        Authority authority = authorityService.find("USER").orElseThrow(() -> new AuthorityNotFoundException("USER"));
        User user = new User(username, email, passwordEncoder.encode(rawPassword));
        user.addAuthority(authority);
        return userRepository.save(user);
    }
}
