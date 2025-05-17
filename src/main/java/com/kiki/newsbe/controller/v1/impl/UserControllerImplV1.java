package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.controller.v1.UserControllerV1;
import com.kiki.newsbe.request.v1.UserRequestV1;
import com.kiki.newsbe.response.base.DataResponseParameter;
import com.kiki.newsbe.response.base.ListResponseParameter;
import com.kiki.newsbe.response.base.PageResponseParameter;
import com.kiki.newsbe.response.base.SliceResponseParameter;
import com.kiki.newsbe.response.v1.UserResponseV1;
import com.kiki.newsbe.services.v1.UserServiceV1;
import com.kiki.newsbe.utils.ResponseHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@BaseControllerImpl
@RequiredArgsConstructor
public class UserControllerImplV1 implements UserControllerV1 {

    private final UserServiceV1 userService;
    private final HttpServletRequest httpServletRequest;

    @Override
    public ListResponseParameter<UserResponseV1> getListUser() {
        return ResponseHelper.createResponse(userService.getListUser());
    }

    @Override
    public DataResponseParameter<UserResponseV1> createUser(UserRequestV1 req) {
        return ResponseHelper.createResponse(userService.createUser(req));
    }

    @Override
    public DataResponseParameter<UserResponseV1> detailUser(String id) {
        return ResponseHelper.createResponse(userService.detailUser(id));
    }

    @Override
    public DataResponseParameter<UserResponseV1> updateUser(String id, UserRequestV1 req) {
        return ResponseHelper.createResponse(userService.updateUser(id, req));
    }

    @Override
    public DataResponseParameter<UserResponseV1> deleteUser(String id) {
        return ResponseHelper.createResponse(userService.deleteUser(id));
    }

    @Override
    public PageResponseParameter<UserResponseV1> getUsersActive(Pageable pageable, String stringFilter) {
        return ResponseHelper.createResponse(userService.getUsersActive(pageable, stringFilter));
    }

    @Override
    public SliceResponseParameter<UserResponseV1> getUsersInActive(Pageable pageable) {
        return ResponseHelper.createResponse(userService.getUsersInActive(pageable));
    }
}
