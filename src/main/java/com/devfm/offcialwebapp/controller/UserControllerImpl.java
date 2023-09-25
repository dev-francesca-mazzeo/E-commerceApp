package com.devfm.offcialwebapp.controller;

import com.devfm.offcialwebapp.controller.interfaces.UserController;
import com.devfm.offcialwebapp.dto.UserDto;
import com.devfm.offcialwebapp.repository.UserRepository;
import com.devfm.offcialwebapp.security.*;
import com.devfm.offcialwebapp.service.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.devfm.offcialwebapp.costants.Endpoint.*;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(USER)
public class UserControllerImpl implements UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    // Endpoint to retrieve a list of users
    @Override
    public List<User> getUser() {
        return userServiceImpl.getUser();
    }

    // Endpoint to add a new user
    @Override
    public String addUser(@RequestBody User NewUser){
        userServiceImpl.saveUser(NewUser);
        return "New User!";
    }

    // Endpoint to update a user's name by ID
    @Override
    public Optional<User> updateUser(@RequestBody String name, @PathVariable Long id) throws Exception {
        userServiceImpl.updateuser(id, name);
        return userServiceImpl.getuserbyid(id);
    }

    // Endpoint to retrieve a user by ID
    @Override
    public Optional<User> getUserById (@PathVariable Long id){
        return userServiceImpl.getuserbyid(id);
    }

    // Endpoint to delete a user by ID
    @Override
    public String deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return "Deleted user!";
    }

    // Endpoint to perform logical deletion of a user by ID
    @Override
    public String deleteUserLogica(@PathVariable  Long id) throws Exception {
        userServiceImpl.deleteUserLogica(id);
        return "Deleted User!";
    }

    // Endpoint to find users with a specific flag
    @Override
    public List<User> findAllAndFlag() {
        return userServiceImpl.findAllAndFlag();
    }

    // Endpoint to retrieve a list of UserDto objects
    @Override
    public List<UserDto> getAllUsersCartgetAllUsersCart() {
        return userServiceImpl.getAllUsersCart();
    }

    // Endpoint to save a new role
    @Override
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/controller/add-role/").toUriString());
        return ResponseEntity.created(uri).body(userServiceImpl.saveRole(role));
    }

    // Endpoint to add a role to a user
    @Override
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userServiceImpl.addRoleToUser(form.getUsername(), form.getRole_name()) ;
        return ResponseEntity.ok().build();
    }

    // Endpoint to refresh a user's token
    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(("secret".getBytes()));
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userRepository.findUserByName(username);
                String access_token = JWT.create()
                        .withSubject(user.getName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAddedRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
