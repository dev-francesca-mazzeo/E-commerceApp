package com.devfm.offcialwebapp.controller.interfaces;

import com.devfm.offcialwebapp.controller.RoleToUserForm;
import com.devfm.offcialwebapp.dto.UserDto;
import com.devfm.offcialwebapp.security.Role;
import com.devfm.offcialwebapp.security.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public interface UserController {

    // Endpoint to retrieve a list of users
    @GetMapping(value = "/get-user")
    public List<User> getUser();

    // Endpoint to add a new user
    @PostMapping(value = "/add-user")
    public String addUser(User NewUser);

    // Endpoint to update a user by ID
    @PutMapping(value = "/update-use/{id}")
    public Optional<User> updateUser(String name, Long id) throws Exception;

    // Endpoint to retrieve a user by ID
    @GetMapping(value = "/get-user-by-id/{id}")
    public Optional<User> getUserById(Long id);

    // Endpoint to delete a user by ID
    @DeleteMapping(value = "/delete-user/{id}")
    public String deleteUser(Long id);

    // Endpoint to perform logical deletion of a user by ID
    @PutMapping(value = "/delete-user-logica/{id}")
    public String deleteUserLogica(Long id) throws Exception;

    // Endpoint to find users with a specific flag
    @GetMapping(value = "/find-flag")
    public List<User> findAllAndFlag();

    // Endpoint to retrieve a list of UserDto objects
    @GetMapping(value = "/map")
    public List<UserDto> getAllUsersCartgetAllUsersCart();

    // Endpoint to save a new role
    @PostMapping(value = "/save-role")
    public ResponseEntity<Role> saveRole(Role role);

    // Endpoint to add a role to a user
    @PostMapping(value = "/add-role-to-user")
    public ResponseEntity<?> addRoleToUser(RoleToUserForm form);

    // Endpoint to refresh a user's token
    @GetMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
