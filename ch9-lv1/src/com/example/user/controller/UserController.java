package com.example.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.user.dto.request.UserCreateRequest;
import com.example.user.dto.response.UserResponse;
import com.example.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")                          // ★バグ: @GetMaping → @GetMapping
    public ResponseEntity<UserResponse> findById(@PathVariable Integer id) {
        logger.info("GET /api/users/{}", id);
        UserResponse response = userService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        logger.info("GET /api/users");
        List<UserResponse> response = userService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<UserResponse>> findByDepartment(
            @PathVariable String department) {
        logger.info("GET /api/users/department/{}", department);
        List<UserResponse> response = userService.findByDepartment(department);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @RequestBody @Valid UserCreateRequest request) {
        logger.info("POST /api/users");
        UserResponse response = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
