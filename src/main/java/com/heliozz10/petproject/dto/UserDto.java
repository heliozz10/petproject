package com.heliozz10.petproject.dto;

import jakarta.validation.constraints.Size;

public record UserDto(@Size(min = 3, max = 20) String username, @Size(min = 8, max = 50) String password) { }
