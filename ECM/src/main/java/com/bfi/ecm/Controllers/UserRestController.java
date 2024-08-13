package com.bfi.ecm.Controllers;

import com.bfi.ecm.Config.UserAuthProvider;
import com.bfi.ecm.DTO.CredentialsDto;
import com.bfi.ecm.DTO.SignupDto;
import com.bfi.ecm.DTO.UserDto;
import com.bfi.ecm.Entities.User;
import com.bfi.ecm.Mappers.UserMapper;
import com.bfi.ecm.Services.service_Interface.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserService userService;
private final UserMapper userMapper;
    private final UserAuthProvider userAuthProvider;
    @Operation(description = "Add User")
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody SignupDto signupDto) {
        UserDto user= userService.register(signupDto);
        System.out.println("activated");
        //user.setToken(userAuthProvider.createToken(user));
        return  ResponseEntity.created(URI.create("/users/"+user.getId())).body(user);
    }
    @Operation(description = "Login User")
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto user= userService.login(credentialsDto);
       // user.setToken(userAuthProvider.createToken(user));
        System.out.println("Logged in user: " + user);

        return ResponseEntity.ok(user);
    }


    @Operation(description = "Retrieve all Users")
    @GetMapping("/all")
    public List<User> retrieveAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(description = "Retrieve User by Id")
    @GetMapping("/get/{id}")
    public ResponseEntity<User> retrieveUserById(@PathVariable("id") Long idUser) {
        Optional<User> userOptional = userService.getUserById(idUser);
        return userOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(description = "Update User")
    @PutMapping("/update")
    public User updateUser(@RequestBody User userData) {
        return userService.updateUser(userData);
    }

    @Operation(description = "Delete User by Id")
    @DeleteMapping("/delete/{id}")
    public void removeUser(@PathVariable("id") Long idUser) {
        userService.deleteUser(idUser);
    }



}
