package com.avoworld.springbe.service;

import com.avoworld.springbe.model.User;
import com.avoworld.springbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setNickname(userDetails.getNickname());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setProfilePicture(userDetails.getProfilePicture());
            user.setUpdateAt(userDetails.getUpdateAt());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}