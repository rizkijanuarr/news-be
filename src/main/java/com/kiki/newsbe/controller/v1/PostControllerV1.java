package com.kiki.newsbe.controller.v1;

import com.kiki.newsbe.annotations.swagger.*;
import com.kiki.newsbe.controller.advices.BaseController;
import com.kiki.newsbe.response.base.DataResponseParameter;
import com.kiki.newsbe.response.base.ListResponseParameter;
import com.kiki.newsbe.response.base.PageResponseParameter;
import com.kiki.newsbe.response.base.SliceResponseParameter;
import com.kiki.newsbe.response.v1.PostResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@BaseController("api/v1/post")
public interface PostControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "Post Management",
            description = "Get list of all Posts",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ListResponseParameter<PostResponseV1> getListPosts();

    @PostEndpoint(
            value = "/",
            tagName = "Post Management",
            description = "Create a new Post",
            group = SwaggerTypeGroup.APPS_WEB,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    DataResponseParameter<PostResponseV1> createPost(
            @RequestPart("category_id") String category_id,
            @RequestPart("title") String title,
            @RequestPart("content") String content,
            @RequestPart("image") MultipartFile image
    );

    @GetEndpoint(
            value = "/{id}",
            tagName = "Post Management",
            description = "Details Post",
            group = SwaggerTypeGroup.APPS_WEB
    )
    DataResponseParameter<PostResponseV1> detailsPost(@PathVariable("id") String id);

    @PutEndpoint(
            value = "/{id}",
            tagName = "Post Management",
            description = "Update Post",
            group = SwaggerTypeGroup.APPS_WEB,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    DataResponseParameter<PostResponseV1> updatePost(
            @PathVariable("id") String id,
            @RequestPart(value = "category_id") String category_id,
            @RequestPart(value = "title") String title,
            @RequestPart(value = "content") String content,
            @RequestPart(value = "image", required = false) MultipartFile image
    );

    @DeleteEndpoint(
            value = "/{id}",
            tagName = "Post Management",
            description = "Delete Post",
            group = SwaggerTypeGroup.APPS_WEB
    )
    DataResponseParameter<PostResponseV1> deletePost(
            @PathVariable("id") String id
    );

    @GetEndpoint(
            value = "/list/ACTIVE",
            tagName = "Post Management",
            description = "List Post ACTIVE",
            group = SwaggerTypeGroup.APPS_WEB
    )
    PageResponseParameter<PostResponseV1> getListPostActive(
            Pageable pageable,
            @RequestParam(value = "string_filter", required = false) String stringFilter
    );

    @GetEndpoint(
            value = "/list/INACTIVE",
            tagName = "Post Management",
            description = "List Post INACTIVE",
            group = SwaggerTypeGroup.APPS_WEB
    )
    SliceResponseParameter<PostResponseV1>  getListPostInactive(Pageable pageable);

}
