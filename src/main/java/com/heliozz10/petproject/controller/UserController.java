package com.heliozz10.petproject.controller;

import com.heliozz10.petproject.dto.UserDto;
import com.heliozz10.petproject.repository.UserRepository;
import com.heliozz10.petproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    UserRepository userRepository;
    UserService userService;
    AuthenticationManager authenticationManager;

    public UserController(UserRepository userRepository, UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/register-process")
    public String registerProcess(@Valid UserDto user, HttpServletRequest request, HttpServletResponse response, RememberMeServices rememberMe) throws Exception {
        if(userService.saveUser(user.username(), null, user.password()) == null) return "redirect:/register";

        //Authenticate the new user
        Authentication auth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(user.username(), user.password()));
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

        //Manually set up remember me
        rememberMe.loginSuccess(request, response, auth);
        return "redirect:/";
    }
}
