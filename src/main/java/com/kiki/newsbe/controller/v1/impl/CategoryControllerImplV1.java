package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.controller.v1.CategoryControllerV1;
import com.kiki.newsbe.request.v1.CategoryRequestV1;
import com.kiki.newsbe.response.base.BaseResponse;
import com.kiki.newsbe.response.base.BaseResponseSlice;
import com.kiki.newsbe.response.base.ResponseHelper;
import com.kiki.newsbe.services.v1.CategoryServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@BaseControllerImpl
@RequiredArgsConstructor
public class CategoryControllerImplV1 implements CategoryControllerV1 {

    private final CategoryServiceV1 categoryServiceV1;

    @Override
    public ResponseEntity<BaseResponse> getListCategories() {
        return ResponseHelper.buildOkResponse(categoryServiceV1.getListCategories());
    }

    @Override
    public ResponseEntity<BaseResponse> createCategory(CategoryRequestV1 request) {
        return ResponseHelper.buildOkResponse(categoryServiceV1.createCategory(request));
    }

    @Override
    public ResponseEntity<BaseResponse> detailsCategory(String id) {
        return ResponseHelper.buildOkResponse(categoryServiceV1.detailsCategory(id));
    }

    @Override
    public ResponseEntity<BaseResponse> updateCategory(String id, CategoryRequestV1 request) {
        return ResponseHelper.buildOkResponse(categoryServiceV1.updateCategory(id, request));
    }

    @Override
    public ResponseEntity<BaseResponse> deleteCategory(String id) {
        return ResponseHelper.buildOkResponse(categoryServiceV1.deleteCategory(id));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getListCategoriesActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(categoryServiceV1.getListCategoriesActive(pageable));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getListCategoriesInactive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(categoryServiceV1.getListCategoriesInactive(pageable));
    }
}
