package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.response.base.BaseResponse;
import com.kiki.newsbe.response.base.BaseResponseSlice;
import com.kiki.newsbe.response.base.ResponseHelper;
import com.kiki.newsbe.controller.v1.UserControllerV1;
import com.kiki.newsbe.request.v1.UserRequestV1;
import com.kiki.newsbe.services.v1.UserServiceV1;
import com.kiki.newsbe.configs.constant.ConstantHeader;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@BaseControllerImpl
@RequiredArgsConstructor
public class UserControllerImplV1 implements UserControllerV1 {

    private final UserServiceV1 userService;
    private final HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<BaseResponse> getListUser() {
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_WHO).toString();
        return ResponseHelper.buildOkResponse(userService.getListUser(requester));
    }

    @Override
    public ResponseEntity<BaseResponse> createUser(UserRequestV1 req) {
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_WHO).toString();
        return ResponseHelper.buildOkResponse(userService.createUser(req, requester));
    }

    @Override
    public ResponseEntity<BaseResponse> detailUser(String id) {
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_WHO).toString();
        return ResponseHelper.buildOkResponse(userService.detailUser(id, requester));
    }

    @Override
    public ResponseEntity<BaseResponse> updateUser(String id, UserRequestV1 req) {
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_WHO).toString();
        return ResponseHelper.buildOkResponse(userService.updateUser(id, req, requester));
    }

    @Override
    public ResponseEntity<BaseResponse> deleteUser(String id) {
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_WHO).toString();
        return ResponseHelper.buildOkResponse(userService.deleteUser(id, requester));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getUsersActive(Pageable pageable) {
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_WHO).toString();
        return ResponseHelper.buildOkeResponse(userService.getUsersActive(pageable, requester));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getUsersInActive(Pageable pageable) {
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_WHO).toString();
        return ResponseHelper.buildOkeResponse(userService.getUsersInActive(pageable, requester));
    }
}
