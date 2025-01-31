package com.example.SongLingo.account.controller;

import com.example.SongLingo.account.user.AdminDTO;
import com.example.SongLingo.account.user.UserDTO;
import com.example.SongLingo.account.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> displayUsersList() {
        List<UserDTO> usersList = userService.findAllUsers();

        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminDTO>> displayAdminsList() {
        List<AdminDTO> adminsList = userService.findAllAdmins();

        return ResponseEntity.ok(adminsList);
    }
}
