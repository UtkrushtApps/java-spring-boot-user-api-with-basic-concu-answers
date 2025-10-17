package com.example.userapi.service;

import com.example.userapi.model.User;
import com.example.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Autowired
    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with email already exists");
        }
        User savedUser = userRepository.save(user);
        // Run notification asynchronously
        executorService.submit(() ->
            notificationService.sendWelcomeEmail(savedUser.getName(), savedUser.getEmail())
        );
        return savedUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @PreDestroy
    public void onDestroy() {
        executorService.shutdown();
    }
}