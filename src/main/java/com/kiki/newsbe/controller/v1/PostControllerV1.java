package com.kiki.newsbe.controller.v1;

import com.kiki.newsbe.annotations.swagger.*;
import com.kiki.newsbe.controller.advices.BaseController;
import com.kiki.newsbe.request.v1.CategoryRequestV1;
import com.kiki.newsbe.request.v1.PostRequestV1;
import com.kiki.newsbe.response.base.BaseResponse;
import com.kiki.newsbe.response.base.BaseResponseSlice;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@BaseController("api/v1/post")
public interface PostControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "Post Management",
            description = "Get list of all Posts",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> getListPosts();

    @PostEndpoint(
            value = "/",
            tagName = "Post Management",
            description = "Create a new Post",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> createPost(
            @Valid @RequestBody PostRequestV1 request,
            String requester
    );

    @GetEndpoint(
            value = "/{id}",
            tagName = "Post Management",
            description = "Details Post",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> detailsPost(@PathVariable("id") String id);

    @PutEndpoint(
            value = "/{id}",
            tagName = "Post Management",
            description = "Update Post",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> updatePost(
            @PathVariable("id") String id,
            @RequestBody PostRequestV1 request,
            String requester
    );

    @DeleteEndpoint(
            value = "/{id}",
            tagName = "Post Management",
            description = "Delete Post",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> deletePost(
            @PathVariable("id") String id,
            String requester
    );

    @GetEndpoint(
            value = "/list/ACTIVE",
            tagName = "Post Management",
            description = "List Post ACTIVE",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getListPostActive(Pageable pageable);

    @GetEndpoint(
            value = "/list/INACTIVE",
            tagName = "Post Management",
            description = "List Post INACTIVE",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getListPostInactive(Pageable pageable);

}
