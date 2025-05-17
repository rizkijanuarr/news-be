package com.kiki.newsbe.controller.v1;

import com.kiki.newsbe.annotations.swagger.*;
import com.kiki.newsbe.controller.advices.BaseController;
import com.kiki.newsbe.response.base.DataResponseParameter;
import com.kiki.newsbe.response.base.ListResponseParameter;
import com.kiki.newsbe.response.base.PageResponseParameter;
import com.kiki.newsbe.response.base.SliceResponseParameter;
import com.kiki.newsbe.response.v1.CategoryResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@BaseController("api/v1/category")
public interface CategoryControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "Categories Management",
            description = "Get list of all Categories",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ListResponseParameter<CategoryResponseV1> getListCategories();

    @PostEndpoint(
            value = "/",
            tagName = "Categories Management",
            description = "Create a new Category",
            group = SwaggerTypeGroup.APPS_WEB,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    DataResponseParameter<CategoryResponseV1> createCategory(
            @RequestPart("name") String name,
            @RequestPart("image") MultipartFile image
    );

    @GetEndpoint(
            value = "/{id}",
            tagName = "Categories Management",
            description = "Details Category",
            group = SwaggerTypeGroup.APPS_WEB
    )
    DataResponseParameter<CategoryResponseV1> detailsCategory(@PathVariable("id") String id);

    @PutEndpoint(
            value = "/{id}",
            tagName = "Categories Management",
            description = "Update Category",
            group = SwaggerTypeGroup.APPS_WEB,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    DataResponseParameter<CategoryResponseV1> updateCategory(
            @PathVariable("id") String id,
            @RequestPart(value = "name") String name,
            @RequestPart(value = "image", required = false) MultipartFile image
    );

    @DeleteEndpoint(
            value = "/{id}",
            tagName = "Categories Management",
            description = "Delete Category",
            group = SwaggerTypeGroup.APPS_WEB
    )
    DataResponseParameter<CategoryResponseV1> deleteCategory(@PathVariable("id") String id);

    @GetEndpoint(
            value = "/list/ACTIVE",
            tagName = "Categories Management",
            description = "List Categories ACTIVE",
            group = SwaggerTypeGroup.APPS_WEB
    )
    PageResponseParameter<CategoryResponseV1> getListCategoriesActive(
            Pageable pageable,
            @RequestParam(value = "string_filter", required = false) String stringFilter
    );

    @GetEndpoint(
            value = "/list/INACTIVE",
            tagName = "Categories Management",
            description = "List Categories INACTIVE",
            group = SwaggerTypeGroup.APPS_WEB
    )
    SliceResponseParameter<CategoryResponseV1> getListCategoriesInactive(Pageable pageable);

}
