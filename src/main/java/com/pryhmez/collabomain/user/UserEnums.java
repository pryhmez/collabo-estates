package com.pryhmez.collabomain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class UserEnums {
    public enum UserRoles {
        ROLE_USER,
        ROLE_ADMIN,
    }
}
