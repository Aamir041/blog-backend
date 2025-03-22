package com.blog.xyz.service;

import com.blog.xyz.dtos.PostRequest;
import com.blog.xyz.dtos.PostResponse;
import com.blog.xyz.dtos.Posts;
import com.blog.xyz.repository.PostRepository;
import com.blog.xyz.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
public class PostServiceImpl implements PostService{

    PostRepository postRepository;
    
    ObjectMapper objectMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           ObjectMapper objectMapper){
        this.postRepository = postRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public PostResponse createPost(PostRequest postRequest, String username) {
        try{
            Posts post = objectMapper.convertValue(postRequest,Posts.class);
            post.setAuthor(username);
            post.setPublicationDate(todaysDate());
            Posts savedPost = postRepository.save(post);
            PostResponse response = objectMapper.convertValue(savedPost,PostResponse.class);
            return response;
        }
        catch (DataAccessException dataAccessException){
            log.error("Error occured while saving post in db for username : {} exception : {}", username, dataAccessException);
            throw dataAccessException;
        }
        catch (Exception exception){
            log.error("Error occured while saving post in db for username : {} exception : {}", username, exception);
            throw exception;
        }
    }

    private Date todaysDate(){
        return Date.from(
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
        );
    }

}
