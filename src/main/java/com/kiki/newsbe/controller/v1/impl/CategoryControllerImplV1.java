package com.kiki.newsbe.controller.v1.impl;

import com.kiki.newsbe.controller.advices.BaseControllerImpl;
import com.kiki.newsbe.controller.v1.CategoryControllerV1;
import com.kiki.newsbe.request.v1.CategoryRequestV1;
import com.kiki.newsbe.response.base.DataResponseParameter;
import com.kiki.newsbe.response.base.ListResponseParameter;
import com.kiki.newsbe.response.base.PageResponseParameter;
import com.kiki.newsbe.response.base.SliceResponseParameter;
import com.kiki.newsbe.response.v1.CategoryResponseV1;
import com.kiki.newsbe.services.v1.CategoryServiceV1;
import com.kiki.newsbe.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

@BaseControllerImpl
@RequiredArgsConstructor
public class CategoryControllerImplV1 implements CategoryControllerV1 {

    private final CategoryServiceV1 categoryServiceV1;

    @Override
    public ListResponseParameter<CategoryResponseV1> getListCategories() {
        return ResponseHelper.createResponse(categoryServiceV1.getListCategories());
    }

    @Override
    public DataResponseParameter<CategoryResponseV1> createCategory(String name, MultipartFile image) {
        CategoryRequestV1 request = CategoryRequestV1.builder()
                .name(name)
                .image(image)
                .build();
        return ResponseHelper.createResponse(categoryServiceV1.createCategory(request));
    }

    @Override
    public DataResponseParameter<CategoryResponseV1> detailsCategory(String id) {
        return ResponseHelper.createResponse(categoryServiceV1.detailsCategory(id));
    }

    @Override
    public DataResponseParameter<CategoryResponseV1> updateCategory(String id, String name, MultipartFile image) {
        CategoryRequestV1 request = CategoryRequestV1.builder()
                .name(name)
                .image(image)
                .build();
        return ResponseHelper.createResponse(categoryServiceV1.updateCategory(id, request));
    }

    @Override
    public DataResponseParameter<CategoryResponseV1> deleteCategory(String id) {
        return ResponseHelper.createResponse(categoryServiceV1.deleteCategory(id));
    }

    @Override
    public PageResponseParameter<CategoryResponseV1> getListCategoriesActive(Pageable pageable, String stringFilter) {
        return ResponseHelper.createResponse(categoryServiceV1.getListCategoriesActive(pageable, stringFilter));
    }

    @Override
    public SliceResponseParameter<CategoryResponseV1> getListCategoriesInactive(Pageable pageable) {
        return ResponseHelper.createResponse(categoryServiceV1.getListCategoriesInactive(pageable));
    }
}
