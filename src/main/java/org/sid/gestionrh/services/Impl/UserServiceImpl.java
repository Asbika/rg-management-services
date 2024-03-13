package org.sid.gestionrh.services.Impl;

import org.sid.gestionrh.entities.User;
import org.sid.gestionrh.exceptions.ResourceAlreadyExistsException;
import org.sid.gestionrh.exceptions.ResourceNotFound;
import org.sid.gestionrh.models.payloads.TokenBody;
import org.sid.gestionrh.models.requests.LoginRequest.LoginRequest;
import org.sid.gestionrh.models.response.LoginResponse.LoginResponse;
import org.sid.gestionrh.repositories.UserRepository;
import org.sid.gestionrh.services.RegesterRequest.RegesterRequest;
import org.sid.gestionrh.services.UserResponse.UserResponse;
import org.sid.gestionrh.services.UserService;
import org.sid.gestionrh.services.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        System.out.println(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return LoginResponse.builder().token(securityUtils.generateToken(TokenBody.builder().email(loginRequest.getEmail()).build())).build();
    }

    @Override
    public UserResponse register(RegesterRequest regesterRequest) {
        Optional<User> userByEmail = userrepository.findByEmail(regesterRequest.getEmail());
        if(userByEmail.isPresent()){
            String message = messageSource.getMessage("employee.email.message",new Object[]{regesterRequest.getEmail()},"No Message", LocaleContextHolder.getLocale());
            throw new ResourceAlreadyExistsException(message);
        }else{
         User  user =  userrepository.save(User.builder().name(regesterRequest.getName())
                    .email(regesterRequest.getEmail())
                    .password(passwordEncoder.encode(regesterRequest.getPassword()))
                    .role(regesterRequest.getRole()).build());

            return  UserResponse.builder().name(user.getName())
                    .id(user.getId())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();
        }
    }

}
