package com.avoworld.springbe.controller;

import com.avoworld.springbe.model.User;
import com.avoworld.springbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



@RestController
@RequestMapping("/api/accounts")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}/nickname")
    public User updateNickname(@PathVariable Long userId, @RequestBody String nickname) {
        User user = userService.getUserById(userId);
        if (user != null) {
            user.setNickname(nickname);
            return userService.createUser(user);
        }
        return null;
    }

    @PutMapping("/{userId}/password")
    public User updatePassword(@PathVariable Long userId, @RequestBody String password) {
        User user = userService.getUserById(userId);
        if (user != null) {
            user.setPassword(password);
            return userService.createUser(user);
        }
        return null;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/register/profileimg")
    public String uploadProfileImg(@RequestParam("profileimg") MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("./uploads/" + file.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        User user = userService.getUserById(loginUser.getUserId());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            return "Login successful";
        }
        return "Invalid credentials";
    }

    @PostMapping("/logout")
    public String logout() {
        // Invalidate session or remove cookies here
        return "Logout successful";
    }

    @PostMapping("/{userId}/profileimg")
    public String uploadProfileImgForUser(@PathVariable Long userId, @RequestParam("profileimg") MultipartFile file) {
        User user = userService.getUserById(userId);
        if (file.isEmpty() || user == null) {
            return "No file uploaded or user not found";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("./uploads/" + file.getOriginalFilename());
            Files.write(path, bytes);
            user.setProfilePicture(path.toString());
            userService.createUser(user);
            return "Profile image updated successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload";
        }
    }
}