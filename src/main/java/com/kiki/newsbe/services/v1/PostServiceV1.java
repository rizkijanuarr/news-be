package com.kiki.newsbe.services.v1;



import com.kiki.newsbe.request.v1.PostRequestV1;
import com.kiki.newsbe.response.v1.PostResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PostServiceV1 {

    List<PostResponseV1> getListPosts();

    PostResponseV1 createPost(PostRequestV1 request, String requester);

    PostResponseV1 detailsPost(String id);

    PostResponseV1 updatePost(String id, PostRequestV1 request, String requester);

    PostResponseV1 deletePost(String id, String requester);

    Slice<PostResponseV1> getListPostActive(Pageable pageable);

    Slice<PostResponseV1> getListPostInactive(Pageable pageable);
}
