package com.pryhmez.collabomain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pryhmez.collabomain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

public class AuthDTOs {

    @Data
    public static class UserDto {

        @NotBlank(message = "Username cannot be blank")
        private String username;
        @NotBlank(message = "lastname cannot be empty please fill out a field")
        private String lastname;
        @NotBlank(message = "firstname cannot be empty please fill out a field")
        private String firstname;
        @NotBlank(message = "email cannot be empty")
        private String email;
        @NotBlank(message = "password cannot be empty")
        private String password;
    }

    public record LoginRequest(String username, String password) {
    }
    @Data
    public static class Response {
        private String message;
        private String token;
        private Object user;
    }

    @Data
    public static class PropertyDto {
        private Long propertyId;
        private String address;
        private String description;
        // Other fields and getters/setters
    }



}
