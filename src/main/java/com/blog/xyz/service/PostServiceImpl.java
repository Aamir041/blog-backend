package com.blog.xyz.service;

import com.blog.xyz.dtos.PostRequest;
import com.blog.xyz.dtos.PostResponse;
import com.blog.xyz.dtos.Posts;
import com.blog.xyz.dtos.UserResponse;
import com.blog.xyz.dtos.paginated_dtos.PaginatedPostResponse;
import com.blog.xyz.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService{

    PostRepository postRepository;

    ObjectMapper objectMapper;

    UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           ObjectMapper objectMapper,
                           UserService userService){
        this.postRepository = postRepository;
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Override
    public PostResponse createPost(PostRequest postRequest, String username) {
        try{
            UserResponse authorUser = userService.getUserByUsername(postRequest.getAuthor());
            Posts post = objectMapper.convertValue(postRequest,Posts.class);
            post.setPublicationDate(todaysDate());
            post.setAuthorid(authorUser.getUid());
            Posts savedPost = postRepository.save(post);
            PostResponse response = objectMapper.convertValue(savedPost,PostResponse.class);
            response.setAuthor(authorUser.getUsername());
            return response;
        }
        catch (DataAccessException dataAccessException){
            log.error("Error occured while saving post in db for username : {} exception : ", username, dataAccessException);
            throw dataAccessException;
        }
        catch (Exception exception){
            log.error("Error occured while saving post in db for username : {} exception : ", username, exception);
            throw exception;
        }
    }

    @Override
    public PaginatedPostResponse getPosts(String username, Integer pageNumber) {

        UserResponse authorUser = userService.getUserByUsername(username);
        List<Posts> posts = postRepository.findAllPostsByAuthorId(getOffsetByPageNumber(pageNumber), authorUser.getUid());
        List<PostResponse> postResponses= posts.stream().map(post -> {
            PostResponse res = objectMapper.convertValue(post,PostResponse.class);
            res.setAuthor(authorUser.getUsername());
            return res;
        }).toList();

        PaginatedPostResponse response = new PaginatedPostResponse();
        response.setCurrentPage(pageNumber);
        response.setNextPage(response.getCurrentPage()+1);
        response.setPosts(postResponses);

        return response;
    }

    private Integer getOffsetByPageNumber(Integer pageNumber){
        return pageNumber*10;
    }

    private Date todaysDate(){
        return Date.from(
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
        );
    }

}
