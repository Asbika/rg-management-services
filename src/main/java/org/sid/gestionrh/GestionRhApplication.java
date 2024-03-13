package org.sid.gestionrh;

import org.sid.gestionrh.entities.User;
import org.sid.gestionrh.models.payloads.TokenBody;
import org.sid.gestionrh.repositories.UserRepository;
import org.sid.gestionrh.services.EmployeeService;
import org.sid.gestionrh.services.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionRhApplication implements CommandLineRunner {

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(GestionRhApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeService.init();
        userRepository.deleteAll();
        User user = User.builder()
                        .name("ahmed Akkouh")
                                .email("ahmed@gmail.com")
                                        .role("ROLE_ADMIN")
                                                .password(passwordEncoder.encode("1232434"))
                                                        .build();
        userRepository.save(user);
        String token = securityUtils.generateToken(TokenBody.builder().role(user.getRole()).email(user.getEmail()).build());
        System.out.println(token);
    }
}
