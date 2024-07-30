package com.bfi.ecm.Services.service_Implimentation;

//import com.bfi.ecm.DTO.CredentialsDto;
//import com.bfi.ecm.DTO.UserDto;
import com.bfi.ecm.DTO.CredentialsDto;
import com.bfi.ecm.DTO.SignupDto;
import com.bfi.ecm.DTO.UserDto;
import com.bfi.ecm.Entities.User;
import com.bfi.ecm.Exceptions.AppException;
import com.bfi.ecm.Mappers.UserMapper;
import com.bfi.ecm.Repositories.IUserRepository;
 import com.bfi.ecm.Services.service_Interface.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IUserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto login(CredentialsDto credentialsDto) {
       User user= userRepository.findByEmail(credentialsDto.email()).
                orElseThrow(()->new AppException("Unknow user ", HttpStatus.NOT_FOUND));
       if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password())
               ,user.getPassword())) {
            return  userMapper.toUserDto(user);
       }
       throw  new AppException("Invalid password",HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDto register(SignupDto signupDto) {
        Optional<User> oUser=userRepository.findByEmail(signupDto.email());
        if(oUser.isPresent()) {
            throw new AppException("Login already exists",HttpStatus.BAD_REQUEST);
        }
         User user=userMapper.signupToUser(signupDto);
        user.setPassword(passwordEncoder.encode(signupDto.password()));
          User saveduser=userRepository.save(user);
        return userMapper.toUserDto(saveduser);
    }
}
