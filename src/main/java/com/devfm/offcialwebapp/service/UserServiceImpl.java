package com.devfm.offcialwebapp.service;
import com.devfm.offcialwebapp.dto.UserDto;
import com.devfm.offcialwebapp.repository.RoleRepository;
import com.devfm.offcialwebapp.repository.UserRepository;
import com.devfm.offcialwebapp.security.Role;
import com.devfm.offcialwebapp.security.User;
import com.devfm.offcialwebapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    UserDto userDto = new UserDto();

    User user = new User();

    @Override
    public Role saveRole(Role role) {
        // Save a new role to the repository
        return roleRepository.save(role);
    }

    @Override
    public List<User> getUser() {
        // Retrieve a list of all users
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User newUser) {
        // Save a new user to the repository, encoding their password
        String rawPassword = newUser.getPsw();
        newUser.setPsw(passwordEncoder.encode(newUser.getPsw()));
        userRepository.save(newUser);
    }

    @Override
    public Optional<User> getuserbyid(Long id) {
        // Retrieve a user by their ID
        return userRepository.findById(id);
    }

    @Override
    public void updateuser(Long id, String name) throws Exception {
        // Find a user by ID and update their name
        User updateUser = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        updateUser.setName(name);
        userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        // Delete a user by their ID
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUserLogica(Long id) throws Exception {
        // Find a user by ID and logically delete them by setting the flagDeleted attribute to 1
        User deleteUser = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        deleteUser.setFlagDeleted(1);
        userRepository.save(deleteUser);
    }

    @Override
    public List<User> findAllAndFlag() {
        // Retrieve a list of all users with flagDeleted equal to 1 (logically deleted users)
        return userRepository.findAllByFlagDeleted(1);
    }

    @Override
    public List<UserDto> getAllUsersCart() {
        // Retrieve a list of all users and convert them to DTOs
        return ((List<User>) userRepository
                .findAll())
                .stream()
                .map(user -> userDto.convertDataIntoDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        // Add a role to a user based on their username and role name
        Role role = roleRepository.findRoleByName(roleName);
        User user = userRepository.findUserByName(username);
        user.getAddedRoles().add(role);
    }

    @Override
    public UserDetails loadUserByUsername(String name_user) throws UsernameNotFoundException {
        // Load a user by their username and convert their roles to authorities
        User user = userRepository.findUserByName(name_user);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database:: {}", name_user);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getAddedRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPsw(), authorities);
    }
}
