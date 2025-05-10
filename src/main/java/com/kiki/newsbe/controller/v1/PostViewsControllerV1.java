package com.kiki.newsbe.controller.v1;

import com.kiki.newsbe.annotations.swagger.GetEndpoint;
import com.kiki.newsbe.annotations.swagger.SwaggerTypeGroup;
import com.kiki.newsbe.controller.advices.BaseController;
import com.kiki.newsbe.response.base.BaseResponse;
import org.springframework.http.ResponseEntity;

@BaseController("api/v1/post-views")
public interface PostViewsControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "Post Views Management",
            description = "Get list of all Posts Views",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> getListPostViews();

}
