package com.blog.xyz.repository;

import com.blog.xyz.dtos.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Integer> {
    @Query(value = "select * from Posts p where p.authorid= :authorid offset :offset limit 10", nativeQuery = true)
    List<Posts> findAllPostsByAuthorId(@Param("offset") Integer offset, @Param("authorid") Integer authorid);

    @Query(value = "select p.postid from Posts p where p.authorid = :authorid ", nativeQuery = true)
    List<Integer> findAllPostIdByAuthorId(@Param("authorid") Integer authorid);
}
