package com.blog.xyz.listener;

import com.blog.xyz.dtos.UserDeleteEvenDto;
import com.blog.xyz.repository.PostRepository;
import com.blog.xyz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserDeleteEventListener {

    private final PostRepository postRepository;

    private final UserService userService;

    @Autowired
    public UserDeleteEventListener (PostRepository postRepository,UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @EventListener
    @Async
    public void handleAsyncUserDelete(UserDeleteEvenDto userDeleteEvenDto){
       try{
           log.info("Received event {}",userDeleteEvenDto);
           Integer authorid = userDeleteEvenDto.getAuthorId();
           String transactionId = userDeleteEvenDto.getTransactionId();
           List<Integer> postIds = postRepository.findAllPostIdByAuthorId(authorid);
           for (Integer postId : postIds){
               log.info("Deleting Post for [postId {}, transactionid {}, authorid {}]",postId,transactionId,authorid);
               postRepository.deleteById(postId);
               log.info("Deleted Post for [postId {}, transactionid {}, authorid {}]",postId,transactionId,authorid);
           }
           log.info("Deleting User for [transactionid {}, authorid {}]",transactionId,authorid);
           userService.deleteUserById(authorid);
           log.info("Deleted User for [transactionid {}, authorid {}]",transactionId,authorid);

       } catch (Exception e) {
           log.error("Exception occurred while deleting : {}",userDeleteEvenDto);
       }

    }
}
