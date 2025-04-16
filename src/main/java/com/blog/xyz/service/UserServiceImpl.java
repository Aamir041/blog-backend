package com.blog.xyz.service;

import com.blog.xyz.dtos.*;
import com.blog.xyz.dtos.UserDeleteResponse;
import com.blog.xyz.exception.ServiceException;
import com.blog.xyz.publisher.UserDeleteEventPublisher;
import com.blog.xyz.repository.UserRepository;
import com.blog.xyz.util.PasswordUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordUtil passwordUtil;
    private ObjectMapper objectMapper;
    private UserDeleteEventPublisher userDeleteEventPublisher;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordUtil passwordUtil,
            ObjectMapper objectMapper,
            UserDeleteEventPublisher userDeleteEventPublisher
    ){
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
        this.objectMapper = objectMapper;
        this.userDeleteEventPublisher = userDeleteEventPublisher;
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        try{
            Users user = objectMapper.convertValue(userRequest, Users.class);
            Users users = userRepository.findByUsername(user.getUsername());
            if(users != null){
                throw new ServiceException("User Already Exist");
            }
            user.setRole(new Role(2, "USER", "User role"));
            user.setPassword(passwordUtil.hashPassword(user.getPassword()));
            log.info("Adding User : {}", user);
            Users savedUser = userRepository.save(user);
            log.info("User saved with uid : {}",user.getUid());
            UserResponse userResponse = objectMapper.convertValue(savedUser, UserResponse.class);
            return userResponse;
        }
        catch (ServiceException serviceException){
            log.error("Error while saving user : {}", serviceException.getMessage());
            throw serviceException;
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        try{
            List<Users> users = userRepository.findAll();
            List<UserResponse> usersResonse = new ArrayList<>();
            for(Users user : users){
                usersResonse.add(objectMapper.convertValue(user, UserResponse.class));
            }
            return usersResonse;
        }
        catch (Exception exception){
            log.error("Error while getting all the user: {}", exception.getMessage());
            throw exception;
        }
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        try{
            UserResponse user = objectMapper.convertValue(userRepository.findByUsername(username), UserResponse.class);
            return user;
        }
        catch (Exception exception){
            log.error("Exception occured while getting user by username {}", exception.getMessage());
            throw exception;
        }
    }

    @Override
    public UserResponse getUserByUid(Integer uid) {
        try{
            Users user = userRepository.findUserByUid(uid);
            UserResponse userResponse = formatUserResponse(user);
            return userResponse;
        }
        catch (Exception exception){
            log.error("Exception occured while getting user by id {}", exception.getMessage());
            throw exception;
        }
    }

    @Override
    public UserResponse updateUserRequest(UserUpdateRequest updatedUser) {
        try{
            Users user = userRepository.findByUsername(updatedUser.getUsername());

            if(updatedUser.getBio() != null && !updatedUser.getBio().equals(user.getBio())){
                user.setBio(updatedUser.getBio());
            }
            if(updatedUser.getBirthdate() != null && !updatedUser.getBirthdate().equals(user.getBirthdate())){
                user.setBirthdate(updatedUser.getBirthdate());
            }

            Users savedUser = userRepository.save(user);
            UserResponse userResponse = formatUserResponse(savedUser);
            return userResponse;
        }
        catch (Exception exception){
            log.error("Exception occured while updating user by username {} ", exception.getMessage());
            throw exception;
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        try{
            Optional<Users> user = userRepository.findById(id);
            if(user.isEmpty()){
                log.error("No user exist with ID : {}",id);
                throw new ServiceException("No user exist with ID : "+id);
            }
            userRepository.deleteById(id);
        }
        catch (ServiceException serviceException){
            throw  serviceException;
        }
        catch (Exception exception){
            throw exception;
        }
    }

    @Override
    public UserDeleteResponse deleteUserByUsername(String username){
        try{
            Users user = userRepository.findByUsername(username);
            if(user == null){
                log.error("User {} does not exist", username);
                throw new ServiceException("User " + username +" does not exist.");
            }
            String transactionId = UUID.randomUUID().toString();

            UserDeleteResponse  userDeleteResponse = new UserDeleteResponse();
            userDeleteResponse.setTransactionId(transactionId);

            userDeleteEventPublisher.publishDeleteEvent(transactionId, user.getUid());

            return userDeleteResponse;
        }
        catch (ServiceException serviceException){
            throw serviceException;
        }
        catch (Exception exception){
            throw exception;
        }
    }

    private UserResponse formatUserResponse (Users user){
        UserResponse userResponse = objectMapper.convertValue(user, UserResponse.class);
        if(null != user.getBirthdate()){
            String usersBirthDate = getUserBirthDate(user.getBirthdate());
            userResponse.setBirthdate(usersBirthDate);
        }
        return userResponse;
    }

    private String getUserBirthDate(Date birthDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(birthDate);
    }

}
