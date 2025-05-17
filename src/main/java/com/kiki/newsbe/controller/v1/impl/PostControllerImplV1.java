package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.controller.v1.PostControllerV1;
import com.kiki.newsbe.request.v1.PostRequestV1;
import com.kiki.newsbe.response.base.DataResponseParameter;
import com.kiki.newsbe.response.base.ListResponseParameter;
import com.kiki.newsbe.response.base.PageResponseParameter;
import com.kiki.newsbe.response.base.SliceResponseParameter;
import com.kiki.newsbe.response.v1.PostResponseV1;
import com.kiki.newsbe.services.v1.PostServiceV1;
import com.kiki.newsbe.utils.ResponseHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

@BaseControllerImpl
@RequiredArgsConstructor
public class PostControllerImplV1 implements PostControllerV1 {

    private final PostServiceV1 postServiceV1;
    private final HttpServletRequest httpServletRequest;

    @Override
    public ListResponseParameter<PostResponseV1> getListPosts() {
        return ResponseHelper.createResponse(postServiceV1.getListPosts());
    }

    @Override
    public DataResponseParameter<PostResponseV1> createPost(
            String category_id, String title, String content, MultipartFile image) {
        PostRequestV1 request = PostRequestV1.builder()
                .category_id(category_id)
                .title(title)
                .content(content)
                .image(image)
                .build();
        return ResponseHelper.createResponse(postServiceV1.createPost(request));
    }

    @Override
    public DataResponseParameter<PostResponseV1> detailsPost(String id) {
        return ResponseHelper.createResponse(postServiceV1.detailsPost(id));
    }

    @Override
    public DataResponseParameter<PostResponseV1> updatePost(
            String id, String category_id, String title, String content, MultipartFile image
    ) {
        PostRequestV1 request = PostRequestV1.builder()
                .category_id(category_id)
                .title(title)
                .content(content)
                .image(image)
                .build();
        return ResponseHelper.createResponse(postServiceV1.updatePost(id, request));
    }

    @Override
    public DataResponseParameter<PostResponseV1> deletePost(String id) {
        return ResponseHelper.createResponse(postServiceV1.deletePost(id));
    }

    @Override
    public PageResponseParameter<PostResponseV1> getListPostActive(Pageable pageable, String stringFilter) {
        return ResponseHelper.createResponse(postServiceV1.getListPostActive(pageable, stringFilter));
    }

    @Override
    public SliceResponseParameter<PostResponseV1> getListPostInactive(Pageable pageable) {
        return ResponseHelper.createResponse(postServiceV1.getListPostInactive(pageable));
    }
}
