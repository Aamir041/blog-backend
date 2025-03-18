package com.blog.xyz.service;

import com.blog.xyz.dtos.PostRequest;
import com.blog.xyz.dtos.PostResponse;
import com.blog.xyz.dtos.PostUserMapping;
import com.blog.xyz.dtos.Posts;
import com.blog.xyz.repository.PostRepository;
import com.blog.xyz.repository.PostUserMappingRepository;
import com.blog.xyz.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostServiceImpl implements PostService{

    PostRepository postRepository;
    UserRepository userRepository;
    PostUserMappingRepository postUserMappingRepository;

    ObjectMapper objectMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository,
                           PostUserMappingRepository postUserMappingRepository,
                           ObjectMapper objectMapper){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postUserMappingRepository = postUserMappingRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public PostResponse createPost(PostRequest postRequest, String username) {
        try{
            Posts post = objectMapper.convertValue(postRequest,Posts.class);
            Posts savedPost = postRepository.save(post);
            savePostUserMapping(savedPost.getPostId(),username);
            PostResponse response = objectMapper.convertValue(post,PostResponse.class);
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

    @Async
    private void savePostUserMapping(Integer postId, String username){
        try{
            Integer userId = userRepository.findByUsername(username).getUid();
            PostUserMapping postUserMapping = createPostUserMapping(postId, userId);
            postUserMappingRepository.save(postUserMapping);
        } catch (Exception exception) {
            log.error("Error occured while saving mapping in db for username : {} exception : {}", username, exception);
            throw exception;
        }
    }

    private PostUserMapping createPostUserMapping(Integer postId, Integer userId){
        PostUserMapping postUserMapping = new PostUserMapping();
        postUserMapping.setPostId(postId);
        postUserMapping.setUserId(userId);
        return postUserMapping;
    }

}
