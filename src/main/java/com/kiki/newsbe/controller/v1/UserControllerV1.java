package com.kiki.newsbe.controller.v1;

import com.kiki.newsbe.annotations.swagger.*;
import com.kiki.newsbe.controller.advices.BaseController;
import com.kiki.newsbe.request.v1.UserRequestV1;
import com.kiki.newsbe.response.base.DataResponseParameter;
import com.kiki.newsbe.response.base.ListResponseParameter;
import com.kiki.newsbe.response.base.PageResponseParameter;
import com.kiki.newsbe.response.base.SliceResponseParameter;
import com.kiki.newsbe.response.v1.UserResponseV1;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@BaseController("api/v1/user")
public interface UserControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "User Management",
            description = "Get list of all Users",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ListResponseParameter<UserResponseV1> getListUser();

    @PostEndpoint(
            value = "/",
            tagName = "User Management",
            description = "Create a new User",
            group = SwaggerTypeGroup.APPS_WEB
    )
    DataResponseParameter<UserResponseV1> createUser(@Valid @RequestBody UserRequestV1 req);

    @GetEndpoint(
            value = "/{id}",
            tagName = "User Management",
            description = "Details User",
            group = SwaggerTypeGroup.APPS_WEB
    )
    DataResponseParameter<UserResponseV1> detailUser(@PathVariable("id") String id);

    @PutEndpoint(
            value = "/{id}",
            tagName = "User Management",
            description = "Edit User",
            group = SwaggerTypeGroup.APPS_WEB
    )
    DataResponseParameter<UserResponseV1> updateUser(@PathVariable("id") String id, @RequestBody UserRequestV1 req);

    @DeleteEndpoint(
            value = "/{id}",
            tagName = "User Management",
            description = "Delete User",
            group = SwaggerTypeGroup.APPS_WEB
    )
    DataResponseParameter<UserResponseV1> deleteUser(@PathVariable("id") String id);

    @GetEndpoint(
            value = "/list/ACTIVE",
            tagName = "User Management",
            description = "List User Active",
            group = SwaggerTypeGroup.APPS_WEB
    )
    PageResponseParameter<UserResponseV1> getUsersActive(
            Pageable pageable,
            @RequestParam(value = "string_filter", required = false) String stringFilter
    );

    @GetEndpoint(
            value = "/list/INACTIVE",
            tagName = "User Management",
            description = "List User InActive",
            group = SwaggerTypeGroup.APPS_WEB
    )
    SliceResponseParameter<UserResponseV1> getUsersInActive(Pageable pageable);

}
