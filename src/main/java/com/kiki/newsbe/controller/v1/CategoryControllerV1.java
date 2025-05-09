package com.kiki.newsbe.controller.v1;

import com.kiki.newsbe.annotations.swagger.*;
import com.kiki.newsbe.controller.advices.BaseController;
import com.kiki.newsbe.request.v1.CategoryRequestV1;
import com.kiki.newsbe.response.base.BaseResponse;
import com.kiki.newsbe.response.base.BaseResponseSlice;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@BaseController("api/v1/category")
public interface CategoryControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "Categories Management",
            description = "Get list of all Categories",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> getListCategories();

    @PostEndpoint(
            value = "/",
            tagName = "Categories Management",
            description = "Create a new Category",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> createCategory(@Valid @RequestBody CategoryRequestV1 request);

    @GetEndpoint(
            value = "/{id}",
            tagName = "Categories Management",
            description = "Create a new Category",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> detailsCategory(@PathVariable("id") String id);

    @PutEndpoint(
            value = "/{id}",
            tagName = "Categories Management",
            description = "Details Category",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> updateCategory(@PathVariable("id") String id, @RequestBody CategoryRequestV1 request);

    @DeleteEndpoint(
            value = "/{id}",
            tagName = "Categories Management",
            description = "Delete Category",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> deleteCategory(@PathVariable("id") String id);

    @GetEndpoint(
            value = "/list/ACTIVE",
            tagName = "Categories Management",
            description = "List Categories ACTIVE",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getListCategoriesActive(Pageable pageable);

    @GetEndpoint(
            value = "/list/INACTIVE",
            tagName = "Categories Management",
            description = "List Categories INACTIVE",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getListCategoriesInactive(Pageable pageable);

}
