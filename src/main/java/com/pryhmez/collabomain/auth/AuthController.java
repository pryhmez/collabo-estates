package com.pryhmez.collabomain.auth;


import com.pryhmez.collabomain.user.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest userLogin) throws IllegalAccessException {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                userLogin.username(),
                                userLogin.password()));

        log.info("Token requested for user :{}", authentication.getAuthorities());
//        Field[] fields = authentication.getPrincipal().getClass().getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            Object value = field.get(authentication.getPrincipal());
//            log.info(field.getName() + " = " + value);
//        }
        Arrays.stream(authentication.getPrincipal().getClass().getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    try {
                        Object value = field.get(authentication.getPrincipal());
//                        log.info(field.getName() + " = " + value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        String token = authService.generateToken(authentication);
        return token;
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody @Valid AuthDTOs.UserDto userDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.debug("Validation errors found: {}", bindingResult.getAllErrors());
            // Collect validation errors
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            // Return a response entity with the validation errors
            return ResponseEntity.badRequest().body(errors);
        }

        User user = authService.createUser(userDto);
        return ResponseEntity.ok(user);
    }
}
