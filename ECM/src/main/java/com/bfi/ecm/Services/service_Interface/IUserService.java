package com.bfi.ecm.Services.service_Interface;

import com.bfi.ecm.DTO.CredentialsDto;
import com.bfi.ecm.DTO.SignupDto;
import com.bfi.ecm.DTO.UserDto;
import com.bfi.ecm.Entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User saveUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(Long id);
    UserDto login(CredentialsDto credentialsDto);
    UserDto register(SignupDto signupDto);
}
