package com.pryhmez.collabomain;

import com.pryhmez.collabomain.auth.RsaKeyConfigProperties;
import com.pryhmez.collabomain.user.User;
import com.pryhmez.collabomain.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Date;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
public class CollaboMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollaboMainApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository user, PasswordEncoder encoder) {
//		return args -> {
//			user.save(new User(
//					1L,
//					"prime",
//					"edmundisaac16@gmail.com",
//					"isaac",
//					"edmund",
//					encoder.encode("123123123"),
//					"ROLES_USER",
//					new Date()));
//		};
//	}

}
