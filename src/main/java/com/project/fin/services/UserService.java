package com.project.fin.services;

import com.project.fin.dto.LoginDto;
import com.project.fin.dto.LoginResponseDto;
import com.project.fin.dto.RegisterDto;
import com.project.fin.exceptions.UserAlreadyExistException;
import com.project.fin.exceptions.UsernameNotFoundException;
import com.project.fin.models.User;
import com.project.fin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder encoder;
    public User register(RegisterDto dto) throws UserAlreadyExistException {
        if (emailExists(dto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + dto.getEmail());
        }
        if (usernameExists(dto.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that username: "
                    + dto.getUsername());
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public LoginResponseDto login(LoginDto dto) throws UsernameNotFoundException {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        authenticationManager.authenticate(auth);
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new UsernameNotFoundException(dto.getEmail()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
        String token = jwtService.generateToken(userDetails, user.getRole().name());
        return new LoginResponseDto(token);
    }
}
