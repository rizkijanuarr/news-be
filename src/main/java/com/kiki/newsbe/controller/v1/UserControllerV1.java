package com.kiki.newsbe.controller.v1;

import com.kiki.newsbe.annotations.swagger.*;
import com.kiki.newsbe.controller.advices.BaseController;
import com.kiki.newsbe.response.base.BaseResponse;
import com.kiki.newsbe.response.base.BaseResponseSlice;
import com.kiki.newsbe.request.v1.UserRequestV1;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@BaseController("v1/user")
public interface UserControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "User Management",
            description = "Get list of all Users",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> getListUser();

    @PostEndpoint(
            value = "/",
            tagName = "User Management",
            description = "Create a new User",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> createUser(@Valid @RequestBody UserRequestV1 req);

    @GetEndpoint(
            value = "/{id}",
            tagName = "User Management",
            description = "Details User",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> detailUser(@PathVariable("id") String id);

    @PutEndpoint(
            value = "/{id}",
            tagName = "User Management",
            description = "Edit User",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> updateUser(@PathVariable("id") String id, @RequestBody UserRequestV1 req);

    @DeleteEndpoint(
            value = "/{id}",
            tagName = "User Management",
            description = "Delete User",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> deleteUser(@PathVariable("id") String id);

    @GetEndpoint(
            value = "/list/ACTIVE",
            tagName = "User Management",
            description = "List User Active",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getUsersActive(Pageable pageable);

    @GetEndpoint(
            value = "/list/INACTIVE",
            tagName = "User Management",
            description = "List User InActive",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getUsersInActive(Pageable pageable);

}
