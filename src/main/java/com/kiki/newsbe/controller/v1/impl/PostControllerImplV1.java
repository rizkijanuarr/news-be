package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.controller.v1.PostControllerV1;
import com.kiki.newsbe.request.v1.PostRequestV1;
import com.kiki.newsbe.response.base.BaseResponse;
import com.kiki.newsbe.response.base.BaseResponseSlice;
import com.kiki.newsbe.response.base.ResponseHelper;
import com.kiki.newsbe.services.v1.PostServiceV1;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@BaseControllerImpl
@RequiredArgsConstructor
public class PostControllerImplV1 implements PostControllerV1 {

    private final PostServiceV1 postServiceV1;
    private final HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<BaseResponse> getListPosts() {
        return ResponseHelper.buildOkResponse(postServiceV1.getListPosts());
    }

    @Override
    public ResponseEntity<BaseResponse> createPost(PostRequestV1 request, String requester) {
        return ResponseHelper.buildOkResponse(postServiceV1.createPost(request, requester));
    }

    @Override
    public ResponseEntity<BaseResponse> detailsPost(String id) {
        return ResponseHelper.buildOkResponse(postServiceV1.detailsPost(id));
    }

    @Override
    public ResponseEntity<BaseResponse> updatePost(String id, PostRequestV1 request, String requester) {
        return ResponseHelper.buildOkResponse(postServiceV1.updatePost(id, request, requester));
    }

    @Override
    public ResponseEntity<BaseResponse> deletePost(String id, String requester) {
        return ResponseHelper.buildOkResponse(postServiceV1.deletePost(id, requester));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getListPostActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(postServiceV1.getListPostActive(pageable));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getListPostInactive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(postServiceV1.getListPostInactive(pageable));
    }
}
