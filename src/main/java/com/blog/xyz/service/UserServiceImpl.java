package com.blog.xyz.service;

import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.Users;
import com.blog.xyz.exception.ServiceException;
import com.blog.xyz.repository.UserRepository;
import com.blog.xyz.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordUtil passwordUtil;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordUtil passwordUtil
    ){
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
    }

    @Override
    public Users addUser(Users user) {
        try{
            UserResponse userResponse = userRepository.findUserByUsername(user.getUsername());
            if(userResponse != null){
                throw new ServiceException("User Already Exist");
            }
            user.setPassword(passwordUtil.hashPassword(user.getPassword()));
            log.info("Adding User : {}", user);
            Users savedUser = userRepository.save(user);
            log.info("User saved with uid : {}",user.getUid());
            return savedUser;
        }
        catch (ServiceException serviceException){
            log.error("Error while saving user : {}", serviceException.getMessage());
            throw serviceException;
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        try{
            List<UserResponse> users = userRepository.findAllusers();
            return users;
        }
        catch (Exception exception){
            log.error("Error while getting all the user: {}", exception.getMessage());
            throw exception;
        }
    }
}
