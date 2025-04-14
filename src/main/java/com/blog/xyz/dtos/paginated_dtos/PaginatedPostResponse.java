package com.blog.xyz.dtos.paginated_dtos;

import com.blog.xyz.dtos.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedPostResponse {
    List<PostResponse> posts;
    Integer currentPage;
    Integer nextPage;
}
