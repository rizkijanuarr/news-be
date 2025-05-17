package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.controller.v1.PostViewsControllerV1;
import com.kiki.newsbe.response.base.ListResponseParameter;
import com.kiki.newsbe.response.v1.PostViewsResponseV1;
import com.kiki.newsbe.services.v1.PostViewsServiceV1;
import com.kiki.newsbe.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;

@BaseControllerImpl
@RequiredArgsConstructor
public class PostViewsControllerImplV1 implements PostViewsControllerV1 {

    private final PostViewsServiceV1 postViewsServiceV1;

    @Override
    public ListResponseParameter<PostViewsResponseV1> getListPostViews() {
        return ResponseHelper.createResponse(postViewsServiceV1.getListPostViews());
    }

}
