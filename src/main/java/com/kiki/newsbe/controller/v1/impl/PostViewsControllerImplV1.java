package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.controller.v1.PostViewsControllerV1;
import com.kiki.newsbe.response.base.BaseResponse;
import com.kiki.newsbe.response.base.ResponseHelper;
import com.kiki.newsbe.services.v1.PostViewsServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@BaseControllerImpl
@RequiredArgsConstructor
public class PostViewsControllerImplV1 implements PostViewsControllerV1 {

    private final PostViewsServiceV1 postViewsServiceV1;

    @Override
    public ResponseEntity<BaseResponse> getListPostViews() {
        return ResponseHelper.buildOkResponse(postViewsServiceV1.getListPostViews());
    }

}
