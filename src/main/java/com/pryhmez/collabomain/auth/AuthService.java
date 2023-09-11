package com.pryhmez.collabomain.auth;

import com.pryhmez.collabomain.user.User;
import com.pryhmez.collabomain.user.UserEnums;
import com.pryhmez.collabomain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.HOURS))
                .subject(authentication.getName())
//                .claim("roles", scope)
                .claim("scope", scope)
//                .claim("authorities", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public User createUser(AuthDTOs.UserDto userDto) {
        Set<UserEnums.UserRoles> userRoles = new HashSet<>();
        userRoles.add(UserEnums.UserRoles.ROLE_USER);
//        userRoles.add(UserEnums.UserRoles.ROLE_ADMIN);

        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .firstName(userDto.getFirstname())
                .lastName(userDto.getLastname())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(userRoles) // Set roles using the Set<UserRoles>
                .build();

        return userRepository.save(user);

    }



}
