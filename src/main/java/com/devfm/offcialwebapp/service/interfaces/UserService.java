package com.devfm.offcialwebapp.service.interfaces;

import com.devfm.offcialwebapp.dto.UserDto;
import com.devfm.offcialwebapp.security.Role;
import com.devfm.offcialwebapp.security.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    // Save a new role
    public Role saveRole(Role role);

    // Retrieve a list of users
    public List<User> getUser();

    // Save a new user
    public void saveUser(User NewUser);

    // Retrieve a user by their ID
    public Optional<User> getuserbyid(Long id);

    // Update a user's name by their ID
    public void updateuser(Long id, String name) throws Exception;

    // Delete a user by their ID
    public void deleteUser(Long id);

    // Logically delete a user by their ID
    public void deleteUserLogica(Long id) throws Exception;

    // Find all users with a specific flag
    public List<User> findAllAndFlag();

    // Retrieve a list of user DTOs
    public List<UserDto> getAllUsersCart();

    // Add a role to a user by their username and role name
    public void addRoleToUser(String name_user, String role_name);
}
