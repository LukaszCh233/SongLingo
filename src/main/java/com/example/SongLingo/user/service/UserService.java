package com.example.SongLingo.user.service;

import com.example.SongLingo.config.JwtService;
import com.example.SongLingo.exception.ExistsException;
import com.example.SongLingo.exception.IncorrectPasswordException;
import com.example.SongLingo.mapper.EntityMapper;
import com.example.SongLingo.user.Role;
import com.example.SongLingo.user.dto.AdminDTO;
import com.example.SongLingo.user.dto.UserDTO;
import com.example.SongLingo.user.entity.LoginRequest;
import com.example.SongLingo.user.entity.User;
import com.example.SongLingo.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityMapper entityMapper;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EntityMapper entityMapper,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.entityMapper = entityMapper;
        this.jwtService = jwtService;
    }

    public UserDTO createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ExistsException("User with this email is registered");
        }

        user.setRole(Role.USER);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return entityMapper.mapUserToUserDTO(userRepository.save(user));
    }

    public AdminDTO createAdmin(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ExistsException("Admin with this email is registered");
        }
        user.setRole(Role.ADMIN);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return entityMapper.mapAdminToAdminDTO(userRepository.save(user));
    }

    public String userAuthorization(LoginRequest loginRequest) {
        User registeredUser = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()
                -> new EntityNotFoundException("User not exists"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), registeredUser.getPassword())) {
            throw new IncorrectPasswordException("Incorrect email or password");
        }
        return jwtService.generateToken(registeredUser);
    }

    public String adminAuthorization(LoginRequest loginRequest) {
        User registeredAdmin = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()
                -> new EntityNotFoundException("Admin not exists"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), registeredAdmin.getPassword())) {
            throw new IncorrectPasswordException("Incorrect email or password");
        }
        return jwtService.generateToken(registeredAdmin);
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findByRole(Role.USER);
        if (users.isEmpty()) {
            throw new EntityNotFoundException("List is empty");
        }
        return entityMapper.mapUserListToUserListDTO(users);
    }

    public List<AdminDTO> findAllAdmins() {
        List<User> admins = userRepository.findByRole(Role.ADMIN);
        if (admins.isEmpty()) {
            throw new EntityNotFoundException("List is empty");
        }
        return entityMapper.mapAdminListToAdminListDTO(admins);
    }
}


